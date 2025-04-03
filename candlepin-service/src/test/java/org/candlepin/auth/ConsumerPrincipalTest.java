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
package org.candlepin.auth;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import org.candlepin.model.Consumer;
import org.candlepin.model.ConsumerType;
import org.candlepin.model.Entitlement;
import org.candlepin.model.Owner;
import org.candlepin.model.Pool;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;


public class ConsumerPrincipalTest {

    private ConsumerPrincipal principal;
    private Consumer consumer;
    private Owner o;

    @BeforeEach
    public void init() {
        o = mock(Owner.class);
        when(o.getKey()).thenReturn("donaldduck");
        when(o.getId()).thenReturn("dd-owner-id");

        consumer = mock(Consumer.class);
        when(consumer.getUuid()).thenReturn("consumer-uuid");
        when(consumer.getOwnerId()).thenReturn("donaldduck");

        principal = new ConsumerPrincipal(consumer, o);
    }

    @Test
    public void noFullAccess() {
        assertFalse(new ConsumerPrincipal(consumer, o).hasFullAccess());
    }

    @Test
    public void nullAccess() {
        assertFalse(new ConsumerPrincipal(consumer, o).canAccess(null, null, null));
        assertFalse(new ConsumerPrincipal(consumer, o).canAccessAll(null, null, null));
    }

    @Test
    public void type() {
        assertEquals("consumer", principal.getType());
    }

    @Test
    public void name() {
        when(consumer.getUuid()).thenReturn("ae5ba-some-name");
        assertEquals("ae5ba-some-name", principal.getPrincipalName());
    }

    @Test
    public void equalsNull() {
        assertNotEquals(null, principal);
    }

    @Test
    public void equalsOtherObject() {
        assertNotEquals(principal, new Object());
    }

    @Test
    public void equalsAnotherConsumerPrincipal() {
        // create a new one with same consumer
        ConsumerPrincipal cp = new ConsumerPrincipal(consumer, o);
        assertEquals(principal, cp);
    }

    @Test
    public void equalsDifferentConsumer() {
        ConsumerType ctype = new ConsumerType(ConsumerType.ConsumerTypeEnum.SYSTEM);
        ctype.setId("test-ctype");

        Owner newOwner = new Owner()
            .setId("o1-id")
            .setKey("o1")
            .setDisplayName("o1");

        Consumer c = new Consumer()
            .setName("Test Consumer")
            .setUsername("test-consumer")
            .setOwner(newOwner)
            .setType(ctype);

        ConsumerPrincipal cp = new ConsumerPrincipal(c, o);
        assertNotEquals(principal, cp);
    }

    @Test
    public void getConsumer() {
        assertEquals(principal.getConsumer(), consumer);
    }

    @Test
    public void accessToConsumer() {
        Consumer c = mock(Consumer.class);
        when(c.getUuid()).thenReturn("consumer-uuid");
        assertTrue(principal.canAccess(consumer, SubResource.NONE, Access.ALL));
    }

    @Test
    public void accessToConsumerEntitlement() {
        Consumer c = mock(Consumer.class);
        when(c.getUuid()).thenReturn("consumer-uuid");
        Entitlement e = mock(Entitlement.class);
        when(e.getConsumer()).thenReturn(c);

        assertTrue(principal.canAccess(e, SubResource.NONE, Access.ALL));
    }

    @Test
    public void accessToMultipleConsumerEntitlements() {
        Consumer c = mock(Consumer.class);
        when(c.getUuid()).thenReturn("consumer-uuid");
        List<Entitlement> entitlements = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            Entitlement e = mock(Entitlement.class);
            when(e.getConsumer()).thenReturn(c);
            entitlements.add(e);
        }

        assertTrue(principal.canAccessAll(entitlements, SubResource.NONE, Access.ALL));
    }

    @Test
    public void denyAccessToOtherConsumerEntitlements() {
        Consumer c = mock(Consumer.class);
        Consumer c0 = mock(Consumer.class);
        when(c.getUuid()).thenReturn("consumer-uuid");
        when(c0.getUuid()).thenReturn("consumer-0-uuid");
        List<Entitlement> entitlements = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            Entitlement e = mock(Entitlement.class);
            if (i == 3) {
                when(e.getConsumer()).thenReturn(c0);
            }
            else {
                when(e.getConsumer()).thenReturn(c);
            }
            entitlements.add(e);
        }

        assertFalse(principal.canAccessAll(entitlements, SubResource.NONE, Access.ALL));
    }

    @Test
    public void accessToPools() {
        Pool p = mock(Pool.class);
        when(p.getOwner()).thenReturn(o);

        assertTrue(principal.canAccess(p, SubResource.ENTITLEMENTS, Access.CREATE));
    }

    @Test
    public void accessToBindToPool() {
        Pool p = mock(Pool.class);
        when(p.getOwner()).thenReturn(o);

        assertTrue(principal.canAccess(p, SubResource.ENTITLEMENTS, Access.CREATE));
        assertFalse(principal.canAccess(p, SubResource.ENTITLEMENTS, Access.READ_ONLY));
    }

    @Test
    public void noAccessToListEntitlementsInPool() {
        Pool p = mock(Pool.class);
        when(p.getOwner()).thenReturn(o);

        assertFalse(principal.canAccess(p, SubResource.ENTITLEMENTS, Access.READ_ONLY));
    }
}
