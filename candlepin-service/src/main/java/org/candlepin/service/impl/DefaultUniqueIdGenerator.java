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
package org.candlepin.service.impl;

import org.candlepin.service.UniqueIdGenerator;

import java.util.Date;



/**
 * DefaultUniqueIdGenerator
 */
public class DefaultUniqueIdGenerator implements UniqueIdGenerator {

    private static Object lock = new Object();
    private static long idCount = 0;

    /* (non-Javadoc)
     * @see org.candlepin.service.UniqueIdGenerator#generateId()
     */
    @Override
    public String generateId() {
        synchronized (lock) {
            Date now = new Date();
            long id = now.getTime() + ++idCount;

            return String.valueOf(id);
        }
    }
}
