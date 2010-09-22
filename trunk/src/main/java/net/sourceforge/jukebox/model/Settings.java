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

import javax.validation.constraints.Size;

import org.apache.commons.configuration.ConfigurationException;
import org.apache.commons.configuration.FileConfiguration;

/**
 * Application Settings.
 */
public class Settings {

    /**
     * Key for content folder.
     */
    private static final String CONTENT_FOLDER = "content.dir";

    /**
     * Key for player url.
     */
    private static final String PLAYER_URL = "host.name";

    /**
     * Key for modified days.
     */
    private static final String MODIFIED_DAYS = "modified.days";

    /**
     * Default value for modified days.
     */
    private static final int DEFAULT_MODIFIED_DAYS = 7;

    /**
     * Folder holding the media contents.
     */
    @Size(min = 1)
    private String contentFolder;

    /**
     * Url to shoutcast server.
     */
    private String playerUrl;

    /**
     * Number of days since specified media was updated.
     */
    private int modifiedDays;

    /**
     * Get the value of the media folder.
     * @return content folder.
     */
    public final String getContentFolder() {
        return contentFolder;
    }

    /**
     * Set the content folder.
     * @param folder content folder
     */
    public final void setContentFolder(final String folder) {
        this.contentFolder = folder;
    }

    /**
     * Get the player url.
     * @return Player url
     */
    public final String getPlayerUrl() {
        return playerUrl;
    }

    /**
     * Set the player url.
     * @param url Player url
     */
    public final void setPlayerUrl(final String url) {
        this.playerUrl = url;
    }

    /**
     * Get the modified days.
     * @return modified days
     */
    public final int getModifiedDays() {
        return modifiedDays;
    }

    /**
     * Set the modified days.
     * @param days modified days
     */
    public final void setModifiedDays(final int days) {
        this.modifiedDays = days;
    }

    /**
     * Load the contents of the configuration file.
     * @param configuration Configuration file
     */
    public final void load(final FileConfiguration configuration) {
        this.contentFolder = configuration.getString(CONTENT_FOLDER);
        this.playerUrl = configuration.getString(PLAYER_URL);
        this.modifiedDays = configuration.getInt(MODIFIED_DAYS, DEFAULT_MODIFIED_DAYS);
    }

    /**
     * Save the contents to configuration file.
     * @param configuration Configuration file
     * @throws ConfigurationException Configuration Exception
     */
    public final void save(final FileConfiguration configuration) throws ConfigurationException {
        configuration.setProperty(CONTENT_FOLDER, this.contentFolder);
        configuration.setProperty(PLAYER_URL, this.playerUrl);
        configuration.setProperty(MODIFIED_DAYS, this.modifiedDays);
        configuration.save();
    }
}
