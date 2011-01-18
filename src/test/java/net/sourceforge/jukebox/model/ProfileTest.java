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

import org.apache.commons.configuration.ConfigurationException;
import org.apache.commons.configuration.PropertiesConfiguration;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;

/**
 * Tests {@link Profile} class.
 */
public class ProfileTest {

    /**
     * Validator object.
     */
    private static Validator validator;

    /**
     * Set up the validator object to be used by the tests.
     */
    @BeforeClass
    public static void setUp() {
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        validator = factory.getValidator();
    }

    /**
     * Tests where all the profile data are valid.
     */
    @Test
    public final void testValidProfile() {
        Profile profile = createProfile("oldPassword", "newPassword", "newPassword");
        Set<ConstraintViolation<Profile>> constraintViolations =
            validator.validate(profile);
        assertEquals(constraintViolations.size(), 0);
    }

    /**
     * Tests the validator with invalid profile data.
     */
    @Test
    public final void testInvalidProfile() {
        Profile profile = createProfile("oldPassword", "newPassword", "confirmPassword");
        Set<ConstraintViolation<Profile>> constraintViolations =
            validator.validate(profile);
        assertEquals(constraintViolations.size(), 1);
    }

    /**
     * Tests the validator with an empty profile.
     */
    @Test
    public final void testEmptyProfile() {
        Profile profile = createProfile("old", "password", "password");
        Set<ConstraintViolation<Profile>> constraintViolations =
            validator.validate(profile);
        assertEquals(constraintViolations.size(), 1);
    }

    /**
     * Tests the validator with <code>null</code> profile data.
     */
    @Test
    public final void testNullProfile() {
        Profile profile = createProfile(null, "password", "password");
        Set<ConstraintViolation<Profile>> constraintViolations =
            validator.validate(profile);
        assertEquals(constraintViolations.size(), 1);
    }

    /**
     * Tests the validator with a valid password.
     * TODO: This needs to be relooked.
     */
    @Test
    public final void testValidCurrentPassword() {
        UsernamePasswordAuthenticationToken auth =
            new UsernamePasswordAuthenticationToken("Administrator", "password");
        SecurityContextHolder.getContext().setAuthentication(auth);
        Profile profile = createProfile("password", "newPassword", "newPassword");
        Set<ConstraintViolation<Profile>> constraintViolations =
            validator.validate(profile);
        assertEquals(constraintViolations.size(), 0);
    }

    /**
     * Tests the <code>load</code> and <code>save</code> methods.
     * @throws IOException IOException
     * @throws ConfigurationException ConfigurationException
     */
    @Test
    public final void testLoad() throws IOException, ConfigurationException {
        Profile profile = new Profile();
        profile.setNewPassword("abcdefghijklmnopqrstuvwxyz");

        File file = File.createTempFile("dummy", "properties");

        file.deleteOnExit();
        PropertiesConfiguration configuration = new PropertiesConfiguration(file);
        configuration.setDelimiterParsingDisabled(true);

        profile.save(configuration);

        Profile savedProfile = new Profile();
        savedProfile.load(configuration);

        assertEquals(profile.getNewPassword(), savedProfile.getOldPassword());
    }

    /**
     * Utility method to create the profile for the tests.
     * @param oldPassword old password
     * @param newPassword new password
     * @param confirmPassword confirm password
     * @return profile object
     */
    private Profile createProfile(final String oldPassword, final String newPassword, final String confirmPassword) {
        Profile profile = new Profile();
        profile.setOldPassword(oldPassword);
        profile.setNewPassword(newPassword);
        profile.setConfirmPassword(confirmPassword);
        return profile;
    }
}
