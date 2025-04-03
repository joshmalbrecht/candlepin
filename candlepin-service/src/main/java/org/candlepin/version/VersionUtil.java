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
package org.candlepin.version;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

/**
 * VersionUtil
 */
public class VersionUtil {
    private static Logger log = LoggerFactory.getLogger(VersionUtil.class);

    public static final String VERSION_HEADER = "X-Version";

    private VersionUtil() {
        // Quiet checkstyle
    }

    public static Map<String, String> getVersionMap() {
        Map<String, String> map = new HashMap<>();

        map.put("version", "Unknown");
        map.put("release", "Unknown");

        Properties props = new Properties();

        try (InputStream in = VersionUtil.class.getClassLoader().getResourceAsStream("version.properties")) {
            props.load(in);
            if (props.containsKey("version")) {
                map.put("version", props.getProperty("version"));
            }
            if (props.containsKey("release")) {
                map.put("release", props.getProperty("release"));
            }
        }
        catch (IOException e) {
            log.error("Can not load version.properties", e);
        }

        return map;
    }

    public static String getVersionString() {
        return getVersionMap().get("version");
    }

    private static int getMajorVersion(String version) {
        String [] tokens = version.split("\\.");
        return Integer.parseInt(tokens[0]);
    }

    public static boolean getRulesVersionCompatibility(String oldVersion, String newVersion) {
        // if the import version is older than our version, the
        // rules are not compatible
        RpmVersionComparator rpmcmp = new RpmVersionComparator();

        if (rpmcmp.compare(oldVersion, newVersion) == 1) {
            return false;
        }

        // If the incoming rules major version is greater than what we understand,
        // the rules are not compatible:
        if (getMajorVersion(newVersion) != getMajorVersion(oldVersion)) {
            return false;
        }

        return true;
    }
}
