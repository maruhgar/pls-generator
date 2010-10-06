/*
 * Copyright 2010 Raghuram
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package net.sourceforge.jukebox.model;

import static org.testng.Assert.assertEquals;

import java.io.File;
import java.io.IOException;
import java.util.Set;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;

import org.apache.commons.configuration.ConfigurationException;
import org.apache.commons.configuration.PropertiesConfiguration;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

/**
 * Tests {@link Settings}.
 */
public class SettingsTest {

    /**
     * Constat modified days.
     */
    private static final int MODIFIED_DAYS = 10;

    /**
     * Validator object.
     */
    private Validator validator;

    /**
     * Initialize the validator.
     */
    @BeforeClass
    public void SetUp() {
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        validator = factory.getValidator();
    }
    /**
     * Tests the <code>load</code> and <code>save</code> methods.
     * @throws IOException IOException
     * @throws ConfigurationException ConfigurationException
     */
    @Test
    public final void testLoad() throws IOException, ConfigurationException {
        Settings settings = new Settings();
        settings.setContentFolder("/var/media");
        settings.setPlayerUrl("http://localhost/play");
        settings.setModifiedDays(MODIFIED_DAYS);

        File file = File.createTempFile("dummy", "properties");
        file.deleteOnExit();
        PropertiesConfiguration configuration = new PropertiesConfiguration(file);
        settings.save(configuration);

        Settings savedSettings = new Settings();
        savedSettings.load(configuration);

        assertEquals(settings, savedSettings);
    }

    /**
     * Tests the validator when content folder is null.
     */
    @Test
    public void testContentFolderIsNull() {
        Settings settings = new Settings();
        settings.setPlayerUrl("http://localhost/play");
        settings.setModifiedDays(0);
        Set<ConstraintViolation<Settings>> constraintViolations = validator.validate(settings);
        assertEquals(constraintViolations.size(), 1);
        assertEquals(constraintViolations.iterator().next().getMessage(), "may not be null");
    }

    /**
     * Tests the validator when content folder is empty
     */
    @Test
    public void testContentFolderIsEmpty() {
        Settings settings = new Settings();
        settings.setContentFolder("");
        settings.setPlayerUrl("http://localhost/play");
        settings.setModifiedDays(0);
        Set<ConstraintViolation<Settings>> constraintViolations = validator.validate(settings);
        assertEquals(constraintViolations.size(), 1);
        assertEquals(constraintViolations.iterator().next().getMessage(), "Cannot be empty");
    }

    /**
     * Tests the validator when modified days is negative.
     */
    @Test
    public void testModifiedDaysIsNegative() {
        Settings settings = new Settings();
        settings.setContentFolder("/var/lib/media");
        settings.setPlayerUrl("http://localhost/play");
        settings.setModifiedDays(-1);
        Set<ConstraintViolation<Settings>> constraintViolations = validator.validate(settings);
        assertEquals(constraintViolations.size(), 1);
        assertEquals(constraintViolations.iterator().next().getMessage(), "must be greater than or equal to 0");
    }
}
