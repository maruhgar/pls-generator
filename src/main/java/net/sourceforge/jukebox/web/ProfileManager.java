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

import javax.inject.Inject;
import javax.validation.Valid;

import net.sourceforge.jukebox.model.Profile;
import net.sourceforge.jukebox.service.ProfileService;

import org.apache.commons.configuration.ConfigurationException;
import org.apache.commons.configuration.FileConfiguration;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * Manages profile password.
 */
@Controller
@RequestMapping("/admin/profile")
public class ProfileManager {
    /**
     * Logger object.
     */
    private static final Logger logger = LoggerFactory.getLogger("ProfileManager");

    /**
     * Profile configuration.
     */
    private FileConfiguration profileConfiguration;

    /**
     * Profile service.
     */
    private ProfileService profileService;

    /**
     * Set the application configuration.
     * @param profileConfiguration Configuration
     */
    @Inject
    public final void setProfileConfiguration(final FileConfiguration profileConfiguration) {
        this.profileConfiguration = profileConfiguration;
    }

    /**
     * @param profileService the profileService to set
     */
    @Inject
    public final void setProfileService(final ProfileService profileService) {
        this.profileService = profileService;
    }

    /**
     * Get the current application configuration settings.
     * @return Settings
     */
    @RequestMapping(method = RequestMethod.GET)
    public final Profile getProfile() {
        logger.info("Retrieving profile");
        Profile settings = new Profile();
        settings.load(this.profileConfiguration);
        return settings;
    }

    /**
     * Updates the profile.
     * @param profile profile data
     * @param result validation result
     * @return success view
     * @throws ConfigurationException any error updating the profile
     */
    @RequestMapping(method = RequestMethod.POST)
    public final String update(@Valid final Profile profile, final BindingResult result) throws ConfigurationException {

        logger.info("Updating profile");
        if (result.hasErrors()) {
            logger.warn("Validation errors in profile");
            return "admin/profile";
        }
        profile.setNewPassword(this.profileService.encodePassword(profile.getNewPassword()));
        profile.save(this.profileConfiguration);
        this.profileService.updateUserContext(Profile.ADMIN_USERNAME);
        return "redirect:/browse";
    }
}
