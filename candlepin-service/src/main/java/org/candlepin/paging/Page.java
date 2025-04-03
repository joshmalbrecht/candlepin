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
package org.candlepin.paging;



/**
 * Represents a page of data.
 *
 * @param <T> The type of data or results this page holds.
 */
public class Page<T> {
    private T pageData;
    private Integer maxRecords;
    private PageRequest pageRequest;

    public T getPageData() {
        return pageData;
    }

    public Page<T> setPageData(T pageData) {
        this.pageData = pageData;
        return this;
    }

    public Integer getMaxRecords() {
        return maxRecords;
    }

    public Page<T> setMaxRecords(Integer maxRecords) {
        this.maxRecords = maxRecords;
        return this;
    }

    public PageRequest getPageRequest() {
        return pageRequest;
    }

    public Page<T> setPageRequest(PageRequest pageRequest) {
        this.pageRequest = pageRequest;
        return this;
    }

}
