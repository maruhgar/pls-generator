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
package net.sourceforge.jukebox.web;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import static org.testng.Assert.assertEquals;
import net.sourceforge.jukebox.model.Profile;
import net.sourceforge.jukebox.service.ProfileService;

import org.apache.commons.configuration.ConfigurationException;
import org.apache.commons.configuration.PropertiesConfiguration;
import org.springframework.validation.BindingResult;
import org.springframework.validation.DataBinder;
import org.springframework.validation.ObjectError;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

/**
 * Tests the method in {@link ProfileManager}.
 */
public class ProfileManagerTest {

    /**
     * Constant test password.
     */
    private static final String TEST_PASSWORD = "password";
    /**
     * Constant user info.
     */
    private static final String TEST_USER_INFO = "ROLE_USER,enabled";
    /**
     * Constant username.
     */
    private static final String TEST_KEY = "Administrator";
    /**
     * Success view.
     */
    private static final String SUCCESS_VIEW = "redirect:/browse";
    /**
     * Form view.
     */
    private static final String FORM_VIEW = "admin/profile";
    /**
     * Configuration object.
     */
    private PropertiesConfiguration configuration;

    /**
     * Initializes needed variables.
     */
    @BeforeMethod
    public final void setUp() {
        configuration = mock(PropertiesConfiguration.class);
    }

    /**
     * Tests the method to get profile object.
     */
    @Test
    public final void testGetProfile() {
        ProfileManager profileManager = new ProfileManager();
        when(configuration.getString(TEST_KEY)).thenReturn(TEST_PASSWORD + "," + TEST_USER_INFO);
        profileManager.setProfileConfiguration(configuration);
        Profile profile = profileManager.getProfile();
        assertEquals(profile.getOldPassword(), TEST_PASSWORD);
        assertEquals(profile.getUserInfo(), TEST_USER_INFO);
    }

    /**
     * Tests the method to update profile using mock.
     * @throws ConfigurationException configuration exception
     */
    @Test
    public final void testUpdate() throws ConfigurationException {
        ProfileManager profileManager = new ProfileManager();
        ProfileService profileService = mock(ProfileService.class);
        when(profileService.encodePassword(TEST_PASSWORD)).thenReturn(TEST_PASSWORD);
        profileManager.setProfileService(profileService);
        profileManager.setProfileConfiguration(configuration);
        Profile profile = new Profile();
        profile.setNewPassword(TEST_PASSWORD);
        BindingResult result = new DataBinder(profile).getBindingResult();
        String view = profileManager.update(profile, result);
        assertEquals(view, SUCCESS_VIEW);
    }

    /**
     * Tests update functionality with invalid profile.
     * @throws ConfigurationException configuration exception
     */
    @Test
    public final void testUpdateWithValidationError() throws ConfigurationException {
        ProfileManager profileManager = new ProfileManager();
        ProfileService profileService = mock(ProfileService.class);
        when(profileService.encodePassword(TEST_PASSWORD)).thenReturn(TEST_PASSWORD);
        profileManager.setProfileService(profileService);
        profileManager.setProfileConfiguration(configuration);
        Profile profile = new Profile();
        profile.setNewPassword(TEST_PASSWORD);
        BindingResult result = new DataBinder(profile).getBindingResult();
        result.addError(new ObjectError("oldPassword", "Current password is invalid"));
        String view = profileManager.update(profile, result);
        assertEquals(view, FORM_VIEW);
    }
}
