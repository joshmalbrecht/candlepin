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

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.any;
import static org.mockito.Mockito.when;

import org.candlepin.auth.Access;
import org.candlepin.auth.Principal;
import org.candlepin.auth.SubResource;
import org.candlepin.dto.api.server.v1.ContentOverrideDTO;
import org.candlepin.exceptions.BadRequestException;
import org.candlepin.model.ContentOverride;
import org.candlepin.model.Owner;
import org.candlepin.model.ActivationKey;
import org.candlepin.model.ActivationKeyContentOverride;
import org.candlepin.test.DatabaseTestFixture;

import org.jboss.resteasy.core.ResteasyContext;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.NullAndEmptySource;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.junit.jupiter.MockitoSettings;
import org.mockito.quality.Strictness;

import java.util.Arrays;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;



/**
 * ActivationKeyContentOverrideResourceTest
 */
@ExtendWith(MockitoExtension.class)
@MockitoSettings(strictness = Strictness.LENIENT)
@SuppressWarnings("checkstyle:indentation")
public class ActivationKeyContentOverrideResourceTest extends DatabaseTestFixture {

    @Mock
    private Principal principal;

    private ActivationKeyResource resource;

    @BeforeEach
    public void setUp() {
        ResteasyContext.pushContext(Principal.class, principal);

        when(this.principal.canAccess(any(Object.class), any(SubResource.class), any(Access.class)))
            .thenReturn(true);

        this.resource = injector.getInstance(ActivationKeyResource.class);
    }

    private ActivationKey createActivationKey() {
        Owner owner = this.createOwner();
        return this.createActivationKey(owner);
    }

    private List<ActivationKeyContentOverride> createOverrides(ActivationKey key, int offset,
        int count) {

        List<ActivationKeyContentOverride> overrides = new LinkedList<>();

        for (int i = offset; i < offset + count; ++i) {
            ActivationKeyContentOverride akco = new ActivationKeyContentOverride()
                .setKey(key)
                .setContentLabel("content_label-" + i)
                .setName("override_name-" + i)
                .setValue("override_value-" + i);

            overrides.add(this.activationKeyContentOverrideCurator.create(akco));
        }

        return overrides;
    }

    @Test
    public void testGetOverrides() {
        ActivationKey key = this.createActivationKey();

        List<ActivationKeyContentOverride> overrides = this.createOverrides(key, 1, 3);
        List<ContentOverrideDTO> expected = overrides.stream()
            .map(this.modelTranslator.getStreamMapper(ContentOverride.class, ContentOverrideDTO.class))
            .toList();

        List<ContentOverrideDTO> actual = this.resource
            .listActivationKeyContentOverrides(key.getId())
            .toList();

        assertThat(actual)
            .usingRecursiveFieldByFieldElementComparatorIgnoringFields("created", "updated")
            .containsExactlyInAnyOrderElementsOf(expected);
    }

    @Test
    public void testGetOverridesEmptyList() {
        ActivationKey key = this.createActivationKey();

        List<ContentOverrideDTO> actual = this.resource
            .listActivationKeyContentOverrides(key.getId())
            .toList();

        assertTrue(actual.isEmpty());
    }

    @Test
    public void testDeleteOverrideUsingName() {
        ActivationKey key = this.createActivationKey();
        List<ActivationKeyContentOverride> overrides = this.createOverrides(key, 1, 3);

        ActivationKeyContentOverride toDelete = overrides.remove(1);
        ContentOverrideDTO toDeleteDTO = new ContentOverrideDTO()
            .contentLabel(toDelete.getContentLabel())
            .name(toDelete.getName());

        List<ContentOverrideDTO> expected = overrides.stream()
            .map(this.modelTranslator.getStreamMapper(ContentOverride.class, ContentOverrideDTO.class))
            .toList();

        List<ContentOverrideDTO> actual = this.resource
            .deleteActivationKeyContentOverrides(key.getId(), Arrays.asList(toDeleteDTO))
            .toList();

        assertThat(actual)
            .usingRecursiveFieldByFieldElementComparatorIgnoringFields("created", "updated")
            .containsExactlyInAnyOrderElementsOf(expected);
    }

    @Test
    public void testDeleteOverridesUsingContentLabel() {
        ActivationKey key = this.createActivationKey();
        List<ActivationKeyContentOverride> overrides = this.createOverrides(key, 1, 3);

        ActivationKeyContentOverride toDelete = overrides.remove(1);
        ContentOverrideDTO toDeleteDTO = new ContentOverrideDTO()
            .contentLabel(toDelete.getContentLabel());

        List<ContentOverrideDTO> expected = overrides.stream()
            .map(this.modelTranslator.getStreamMapper(ContentOverride.class, ContentOverrideDTO.class))
            .toList();

        List<ContentOverrideDTO> actual = this.resource
            .deleteActivationKeyContentOverrides(key.getId(), Arrays.asList(toDeleteDTO))
            .toList();

        assertThat(actual)
            .usingRecursiveFieldByFieldElementComparatorIgnoringFields("created", "updated")
            .containsExactlyInAnyOrderElementsOf(expected);
    }

    @Test
    public void testDeleteAllOverridesUsingEmptyList() {
        ActivationKey key = this.createActivationKey();
        this.createOverrides(key, 1, 3);

        List<ContentOverrideDTO> actual = this.resource
            .deleteActivationKeyContentOverrides(key.getId(), Collections.emptyList())
            .toList();

        assertTrue(actual.isEmpty());
    }

    @Test
    public void testDeleteAllOverridesUsingEmptyContentLabel() {
        ActivationKey key = this.createActivationKey();
        this.createOverrides(key, 1, 3);

        List<ContentOverrideDTO> actual = this.resource
            .deleteActivationKeyContentOverrides(key.getId(), Collections.emptyList())
            .toList();

        assertTrue(actual.isEmpty());
    }

    @Test
    public void testAddOverride() {
        ActivationKey activationkey = this.createActivationKey();

        ContentOverrideDTO dto1 = new ContentOverrideDTO()
            .contentLabel("test_label")
            .name("override_name")
            .value("override_value");

        List<ContentOverrideDTO> output1 = this.resource
            .addActivationKeyContentOverrides(activationkey.getId(), List.of(dto1))
            .toList();

        assertThat(output1)
            .usingRecursiveFieldByFieldElementComparatorIgnoringFields("created", "updated")
            .containsExactlyInAnyOrder(dto1);

        // Add a second to ensure we don't clobber the first
        ContentOverrideDTO dto2 = new ContentOverrideDTO()
            .contentLabel("test_label-2")
            .name("override_name-2")
            .value("override_value-2");

        List<ContentOverrideDTO> output2 = this.resource
            .addActivationKeyContentOverrides(activationkey.getId(), List.of(dto2))
            .toList();

        assertThat(output2)
            .usingRecursiveFieldByFieldElementComparatorIgnoringFields("created", "updated")
            .containsExactlyInAnyOrder(dto1, dto2);
    }

    @Test
    public void testAddOverrideOverwritesExistingWhenMatched() {
        ActivationKey activationkey = this.createActivationKey();

        ContentOverrideDTO dto1 = new ContentOverrideDTO()
            .contentLabel("test_label")
            .name("override_name")
            .value("override_value");

        List<ContentOverrideDTO> output1 = this.resource
            .addActivationKeyContentOverrides(activationkey.getId(), List.of(dto1))
            .toList();

        assertThat(output1)
            .usingRecursiveFieldByFieldElementComparatorIgnoringFields("created", "updated")
            .containsExactlyInAnyOrder(dto1);

        // Add a "new" override that has the same label and name as the first which should inherit
        // the new value
        ContentOverrideDTO dto2 = new ContentOverrideDTO()
            .contentLabel("test_label")
            .name("override_name")
            .value("override_value-2");

        List<ContentOverrideDTO> output2 = this.resource
            .addActivationKeyContentOverrides(activationkey.getId(), List.of(dto2))
            .toList();

        assertThat(output2)
            .usingRecursiveFieldByFieldElementComparatorIgnoringFields("created", "updated")
            .containsExactlyInAnyOrder(dto2);
    }

    @Test
    public void testAddOverrideFailsValidationWithNoParent() {
        ContentOverrideDTO dto = new ContentOverrideDTO()
            .contentLabel("test_label")
            .name("override_name")
            .value("override_value");

        assertThrows(BadRequestException.class,
            () -> this.resource.addActivationKeyContentOverrides(null, List.of(dto)));
    }

    @ParameterizedTest
    @NullAndEmptySource
    public void testAddOverrideFailsValidationWithNullOrEmptyLabel(String label) {
        ActivationKey activationkey = this.createActivationKey();

        ContentOverrideDTO dto = new ContentOverrideDTO()
            .contentLabel(label)
            .name("override_name")
            .value("override_value");

        assertThrows(BadRequestException.class,
            () -> this.resource.addActivationKeyContentOverrides(activationkey.getId(), List.of(dto)));
    }

    @Test
    public void testAddOverrideFailsValidationWithLongLabel() {
        ActivationKey activationkey = this.createActivationKey();

        String longString = "a".repeat(ContentOverride.MAX_NAME_AND_LABEL_LENGTH + 1);

        ContentOverrideDTO dto = new ContentOverrideDTO()
            .contentLabel(longString)
            .name("override_name")
            .value("override_value");

        assertThrows(BadRequestException.class,
            () -> this.resource.addActivationKeyContentOverrides(activationkey.getId(), List.of(dto)));
    }

    @ParameterizedTest
    @NullAndEmptySource
    public void testAddOverrideFailsValidationWithNullOrEmptyName(String name) {
        ActivationKey activationkey = this.createActivationKey();

        ContentOverrideDTO dto = new ContentOverrideDTO()
            .contentLabel("content_label")
            .name(name)
            .value("override_value");

        assertThrows(BadRequestException.class,
            () -> this.resource.addActivationKeyContentOverrides(activationkey.getId(), List.of(dto)));
    }

    @Test
    public void testAddOverrideFailsValidationWithLongName() {
        ActivationKey activationkey = this.createActivationKey();

        String longString = "a".repeat(ContentOverride.MAX_NAME_AND_LABEL_LENGTH + 1);

        ContentOverrideDTO dto = new ContentOverrideDTO()
            .contentLabel("content_label")
            .name(longString)
            .value("override_value");

        assertThrows(BadRequestException.class,
            () -> this.resource.addActivationKeyContentOverrides(activationkey.getId(), List.of(dto)));
    }

    @ParameterizedTest
    @NullAndEmptySource
    public void testAddOverrideFailsValidationWithNullOrEmptyValue(String value) {
        ActivationKey activationkey = this.createActivationKey();

        ContentOverrideDTO dto = new ContentOverrideDTO()
            .contentLabel("content_label")
            .name("override_name")
            .value(value);

        assertThrows(BadRequestException.class,
            () -> this.resource.addActivationKeyContentOverrides(activationkey.getId(), List.of(dto)));
    }

    @Test
    public void testAddOverrideFailsValidationWithLongValue() {
        ActivationKey activationkey = this.createActivationKey();

        String longString = "a".repeat(ContentOverride.MAX_VALUE_LENGTH + 1);

        ContentOverrideDTO dto = new ContentOverrideDTO()
            .contentLabel("content_label")
            .name("override_name")
            .value(longString);

        assertThrows(BadRequestException.class,
            () -> this.resource.addActivationKeyContentOverrides(activationkey.getId(), List.of(dto)));
    }
}
