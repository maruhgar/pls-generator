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

import org.apache.commons.configuration.ConfigurationException;
import org.apache.commons.configuration.PropertiesConfiguration;
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
        PropertiesConfiguration configuration = new PropertiesConfiguration(file);
        settings.save(configuration);

        Settings savedSettings = new Settings();
        savedSettings.load(configuration);

        assertEquals(settings, savedSettings);
    }
}
