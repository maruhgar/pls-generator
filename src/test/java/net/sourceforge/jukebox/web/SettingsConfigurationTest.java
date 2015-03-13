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
import net.sourceforge.jukebox.model.Settings;

import org.apache.commons.configuration.ConfigurationException;
import org.apache.commons.configuration.PropertiesConfiguration;
import org.springframework.test.web.ModelAndViewAssert;
import org.springframework.validation.BindingResult;
import org.springframework.validation.DataBinder;
import org.springframework.validation.ObjectError;
import org.springframework.web.servlet.ModelAndView;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

/**
 * Tests {@link SettingsConfiguration}.
 */
public class SettingsConfigurationTest {

    /**
     * Constant value for modified days.
     */
    private static final String MODIFIED_DAYS = "modified.days";
    /**
     * Success view.
     */
    private static final String SUCCESS_VIEW = "redirect:/browse";
    /**
     * Form view.
     */
    private static final String FORM_VIEW = "admin/configure";
    /**
     * Default modified days.
     */
    private static final int DEFAULT_MODIFIED_DAYS = 7;
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
     * Tests the method to get the contents.
     */
    @Test
    public final void testGetSettings() {

        SettingsConfiguration settingsConfiguration = new SettingsConfiguration();
        when(configuration.getInt(MODIFIED_DAYS, DEFAULT_MODIFIED_DAYS)).thenReturn(DEFAULT_MODIFIED_DAYS);
        settingsConfiguration.setConfiguration(configuration);
        Settings settings = settingsConfiguration.getSettings();

        assertEquals(settings.getModifiedDays(), DEFAULT_MODIFIED_DAYS);
    }

    /**
     * Tests the update method.
     * @throws ConfigurationException configuration exception
     */
    @Test
    public final void testUpdateSettings() throws ConfigurationException {
        SettingsConfiguration settingsConfiguration = new SettingsConfiguration();
        settingsConfiguration.setConfiguration(configuration);
        Settings settings = new Settings();
        BindingResult result = new DataBinder(settings).getBindingResult();
        String view = settingsConfiguration.update(settings, result);
        assertEquals(view, SUCCESS_VIEW);

        result.addError(new ObjectError("settings", "Validation error"));
        view = settingsConfiguration.update(settings, result);
        assertEquals(view, FORM_VIEW);
    }

    /**
     * Tests exception handling.
     */
    @Test
    public final void testExceptionHandling() {
        IllegalArgumentException e = new IllegalArgumentException();

        SettingsConfiguration settingsConfiguration = new SettingsConfiguration();
        ModelAndView modelAndView = settingsConfiguration.handleErrors(e);

        ModelAndViewAssert.assertViewName(modelAndView, "error");
        ModelAndViewAssert.assertModelAttributeAvailable(modelAndView, "errorStackTrace");

    }

}
