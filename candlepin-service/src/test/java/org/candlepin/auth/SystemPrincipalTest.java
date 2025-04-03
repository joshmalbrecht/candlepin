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
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.mock;

import org.candlepin.model.Entitlement;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;


public class SystemPrincipalTest {

    private SystemPrincipal principal;

    @BeforeEach
    public void init() {
        principal = new SystemPrincipal();
    }

    @Test
    public void fullAccess() {
        assertTrue(principal.hasFullAccess());
    }

    @Test
    public void canAccessEntities() {
        Entitlement e = mock(Entitlement.class);
        assertTrue(principal.canAccess(e, SubResource.NONE, Access.ALL));
    }

    @Test
    public void type() {
        assertEquals("system", principal.getType());
    }

    @Test
    public void name() {
        assertEquals("System", principal.getPrincipalName());
    }
}
