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
package org.candlepin.model;

import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.GenericGenerator;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;



/**
 * DistributorVersion
 *
 * Used as a capability seed list for consumers. A consumer created with
 * a specific distributor version name will be assigned the list of
 * capabilities related to the distributor version
 *
 */
@Entity
@Table(name = DistributorVersion.DB_TABLE)
public class DistributorVersion extends AbstractHibernateObject {

    /** Name of the table backing this object in the database */
    public static final String DB_TABLE = "cp_dist_version";

    @Id
    @GeneratedValue(generator = "system-uuid")
    @GenericGenerator(name = "system-uuid", strategy = "uuid")
    @Column(length = 32)
    @NotNull
    private String id;

    @Column(nullable = false, unique = true)
    @Size(max = 255)
    @NotNull
    private String name;

    @Column(nullable = false, unique = true, name = "display_name")
    @Size(max = 255)
    @NotNull
    private String displayName;

    @OneToMany(mappedBy = "distributorVersion", targetEntity =
        DistributorVersionCapability.class)
    @Cascade({org.hibernate.annotations.CascadeType.ALL,
        org.hibernate.annotations.CascadeType.DELETE_ORPHAN})
    private Set<DistributorVersionCapability> capabilities;

    public DistributorVersion() {
    }

    public DistributorVersion(String name) {
        this.name = name;
    }

    /**
     * @return the id
     */
    public String getId() {
        return this.id;
    }

    /**
     * @param id the id to set
     */
    public void setId(String id) {
        this.id = id;
    }

    /**
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * @param name the name to set
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * @return the display name
     */
    public String getDisplayName() {
        return displayName;
    }

    /**
     * @param displayName the display name to set
     */
    public void setDisplayName(String displayName) {
        this.displayName = displayName;
    }

    /**
     * @return the capabilities
     */
    public Set<DistributorVersionCapability> getCapabilities() {
        return capabilities;
    }

    /**
     * @param capabilities the capabilities to set
     */
    public void setCapabilities(Set<DistributorVersionCapability> capabilities) {
        if (this.capabilities == null) {
            this.capabilities = new HashSet<>();
        }
        if (!this.capabilities.equals(capabilities)) {
            this.capabilities.clear();
            this.capabilities.addAll(capabilities);
            this.setUpdated(new Date());
            for (DistributorVersionCapability dvc : this.capabilities) {
                dvc.setDistributorVersion(this);
            }
        }
    }
}
