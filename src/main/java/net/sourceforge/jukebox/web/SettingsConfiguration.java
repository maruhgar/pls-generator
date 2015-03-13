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

import java.io.ByteArrayOutputStream;
import java.io.PrintWriter;

import javax.inject.Inject;
import javax.validation.Valid;

import net.sourceforge.jukebox.model.Settings;

import org.apache.commons.configuration.ConfigurationException;
import org.apache.commons.configuration.FileConfiguration;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

/**
 * Configure the application settings.
 */
@Controller
@RequestMapping("/admin/configure")
public class SettingsConfiguration {

    /**
     * Logger object.
     */
    private static final Logger logger = LoggerFactory.getLogger(SettingsConfiguration.class);

    /**
     * Application configuration.
     */
    private FileConfiguration configuration;

    /**
     * Set the application configuration.
     * @param configuration Configuration
     */
    @Inject
    public final void setConfiguration(final FileConfiguration configuration) {
        this.configuration = configuration;
    }

    /**
     * Get the current application configuration settings.
     * @return Settings
     */
    @RequestMapping(method = RequestMethod.GET)
    public final Settings getSettings() {

        logger.info("Accessing the settings");
        Settings settings = new Settings();
        settings.load(this.configuration);
        return settings;
    }

    /**
     * Updates application configuration.
     * @param settings Settings data
     * @param result Validation result
     * @return Success view
     * @throws ConfigurationException Configuration exception
     */
    @RequestMapping(method = RequestMethod.POST)
    public final String update(@Valid final Settings settings, final BindingResult result) throws ConfigurationException {

        logger.info("Updating the settings");
        if (result.hasErrors()) {
            logger.warn("Validation errors updating the settings");
            return "admin/configure";
        }
        settings.save(configuration);
        return "redirect:/browse";
    }

    /**
     * Handles exceptions.
     * @param e Exception
     * @return Error view
     */
    @ExceptionHandler(Exception.class)
    public final ModelAndView handleErrors(final Exception e) {
        logger.error("Encountered error", e);
        ModelAndView mav = new ModelAndView();
        mav.setViewName("error");
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        e.printStackTrace(new PrintWriter(baos, true));
        String stackTrace = baos.toString();
        mav.addObject("errorStackTrace", stackTrace);
        return mav;
    }

}
