/*
 * Copyright (c) 2009 - 2023 Red Hat, Inc.
 *
 * This software is licensed to you under the GNU General Public License,
 * version 2 (GPLv2). There is NO WARRANTY for this software, express or
 * implied, including the implied warranties of MERCHANTABILITY or FITNESS
 * FOR A PARTICULAR PURPOSE. You should have received a copy of GPLv2
 * along with this software; if not, see
 * http://www.gnu.org/licenses/old-licenses/gpl-2.0.txt.
 *
 * Red Hat trademarks are not licensed under GPLv2. No permission is
 * granted to use or replicate Red Hat trademarks that are incorporated
 * in this software or its documentation.
 */
package org.candlepin.resource;

/**
 * RootResource
 */

import org.candlepin.auth.SecurityHole;
import org.candlepin.config.ConfigProperties;
import org.candlepin.config.Configuration;
import org.candlepin.dto.api.server.v1.Link;
import org.candlepin.resource.server.v1.RootApi;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.annotation.Annotation;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Set;

import javax.inject.Inject;
import javax.ws.rs.Path;

/**
 * A root resource, responsible for returning client a struct of links to the
 * various resources Candlepin exposes. This list will be filtered based on the
 * permissions of the caller.
 */
public class RootResource implements RootApi {

    @Retention(RetentionPolicy.RUNTIME)
    @Target(ElementType.METHOD)
    public @interface LinkedResource {
        // Intentionally left empty
    }


    private static final Logger log = LoggerFactory.getLogger(RootResource.class);
    public static final Map<Object, String> RESOURCE_CLASSES;
    private Configuration config;
    private static Iterable<Link> links = null;

    static {
        RESOURCE_CLASSES = new HashMap<>();

        addResource(AdminResource.class);
        addResource(UserResource.class);
        addResource(CertificateSerialResource.class);
        addResource(CdnResource.class);
        addResource(ConsumerResource.class);
        addResource(ConsumerTypeResource.class);
        addResource(ContentResource.class);
        addResource(CrlResource.class);
        addResource(EntitlementResource.class);
        addResource(JobResource.class);
        addResource(OwnerResource.class);
        addResource(PoolResource.class);
        addResource(ProductResource.class);
        addResource(RulesResource.class);
        addResource(StatusResource.class);
        addResource(SubscriptionResource.class);
        addResource(ActivationKeyResource.class);
        addResource(RoleResource.class);
        addResource(HypervisorResource.class);
        addResource(EnvironmentResource.class);
        addResource(RootResource.class);
        addResource(DistributorVersionResource.class);
        addResource(DeletedConsumerResource.class);
    }

    private static void addResource(Class resource) {
        if (resource == null) {
            throw new IllegalArgumentException("resource is null");
        }

        RESOURCE_CLASSES.put(resource, null);

        for (Method method : resource.getDeclaredMethods()) {
            if (method.isAnnotationPresent(LinkedResource.class)) {
                RESOURCE_CLASSES.put(method, null);
            }
        }
    }

    @Inject
    public RootResource(Configuration config) {
        this.config = config;
    }

    protected Iterable<Link> createLinks() {
        // Hidden resources will be omitted from the supported list we send to
        // the clients:
        Set<String> hideResources = Set.of(config.getString(ConfigProperties.HIDDEN_RESOURCES).split(" "));
        Map<String, Link> links = new HashMap<>();

        for (Map.Entry<Object, String> entry : RESOURCE_CLASSES.entrySet()) {
            Link link = this.resourceLink(entry.getKey(), entry.getValue());

            if (hideResources.contains(link.getRel())) {
                log.debug("Hiding supported resource: {}", link.getRel());
                continue;
            }

            links.put(link.getRel(), link);
        }

        return links.values();
    }

    protected String generateRel(String href) {
        int index = href.lastIndexOf("/");
        if (index == -1) {
            return href;
        }
        return href.substring(index + 1);
    }

    private <T extends Annotation> T findClassAnnotation(Class<?> cls, Class<T> annotation) {
        T result = cls.getAnnotation(annotation);

        if (result == null) {
            List<Class> parents = new LinkedList<>(List.of(cls.getInterfaces()));
            parents.add(cls.getSuperclass());

            // Note: this doesn't traverse the tree in order of closest declaration. With a sufficiently
            // complex graph, we could end up finding the annotation on a parent declaration before finding
            // it on a closer declaration. For our purposes, this is still likely acceptable, but if we have
            // wonky class/interface graphs later, this could become a problem.

            for (Class parent : parents) {
                result = this.findClassAnnotation(parent, annotation);

                if (result != null) {
                    break;
                }
            }
        }

        return result;
    }

    private <T extends Annotation> T findMethodAnnotation(Class<?> cls, Method method, Class<T> annotation) {
        T result = method.getAnnotation(annotation);

        if (result == null) {
            List<Class> parents = new LinkedList<>(List.of(cls.getInterfaces()));
            parents.add(cls.getSuperclass());

            // Note: this doesn't traverse the tree in order of closest declaration. With a sufficiently
            // complex graph, we could end up finding the annotation on a parent declaration before finding
            // it on a closer declaration. For our purposes, this is still likely acceptable, but if we have
            // wonky class/interface graphs later, this could become a problem.

            for (Class parent : parents) {
                try {
                    method = parent.getDeclaredMethod(method.getName(), method.getParameterTypes());
                    result = this.findMethodAnnotation(parent, method, annotation);
                }
                catch (NoSuchMethodException e) {
                    // Class/interface doesn't declare this method; skip it
                }

                if (result != null) {
                    break;
                }
            }
        }

        return result;
    }

    protected Link methodLink(String rel, Method m) {
        Path resource = this.findClassAnnotation(m.getDeclaringClass(), Path.class);
        Path method = this.findMethodAnnotation(m.getDeclaringClass(), m, Path.class);

        String href = resource.value() + "/" + method.value();
        // Remove doubled slashes and trailing slash
        href = href.replaceAll("/+", "/").replaceAll("/$", "");

        if (rel == null) {
            rel = generateRel(href);
        }

        return new Link().rel(rel).href(href);
    }

    protected Link classLink(String rel, Class clazz) {
        Path path = (Path) clazz.getAnnotation(Path.class);

        if (path == null) {
            path = (Path) Arrays.stream(clazz.getInterfaces())
                .map(c -> c.getAnnotation(Path.class))
                .filter(Objects::nonNull)
                .findFirst()
                .orElse(null);
        }

        String href = path.value();

        if (rel == null) {
            rel = generateRel(href);
        }

        return new Link().rel(rel).href(href);
    }

    protected Link resourceLink(Object resource, String rel) {
        if (resource instanceof Method) {
            return methodLink(rel, (Method) resource);
        }
        return classLink(rel, (Class) resource);
    }

    @SecurityHole(noAuth = true, anon = true)
    public Iterable<Link> getRootResources() {
        // Create the links when requested. Although
        // this is not thread safe, doing this 2 or 3 times
        // will not hurt anything as it will result in a little
        // bit more garbage
        if (links == null) {
            links = createLinks();
        }

        return links;
    }
}
