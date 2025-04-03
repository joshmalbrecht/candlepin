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
package org.candlepin.auth.permissions;

import org.candlepin.auth.Access;
import org.candlepin.auth.SubResource;
import org.candlepin.model.Owner;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.From;
import javax.persistence.criteria.Predicate;



/**
 * Grants access to view an owner's pools, as well as their backing subscriptions.
 */
public class OwnerPoolsPermission extends TypedPermission<Owner> {

    private final Owner owner;

    public OwnerPoolsPermission(Owner owner) {
        this.owner = owner;
        this.access = Access.READ_ONLY;
    }

    @Override
    public Class<Owner> getTargetType() {
        return Owner.class;
    }

    @Override
    public boolean canAccessTarget(Owner target, SubResource subResource, Access required) {
        return target.getKey().equals(owner.getKey()) &&
            (subResource.equals(SubResource.POOLS) ||
                subResource.equals(SubResource.SUBSCRIPTIONS)) &&
                    access.provides(required);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public <T> Predicate getQueryRestriction(Class<T> entityClass, CriteriaBuilder builder, From<?, T> path) {
        return null;
    }

    @Override
    public Owner getOwner() {
        return owner;
    }

}
