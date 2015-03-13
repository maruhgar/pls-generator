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

import org.apache.commons.lang.ObjectUtils;

/**
 * Class to holder file/folder properties.
 *
 */
public class ContentModel {

    /**
     * Full path of the media file or folder.
     */
    private String absoluteFileName;

    /**
     * Display name of the file or folder.
     */
    private String displayName;

    /**
     * Flag to indicate if the file/folder was recently updated.
     */
    private boolean recentUpdate;

    /**
     * Get the absolute file/folder name.
     * @return absoluteFileName
     */
    public final String getAbsoluteFileName() {
        return absoluteFileName;
    }

    /**
     * Set the absolute file/folder name.
     * @param fileName file name
     */
    public final void setAbsoluteFileName(final String fileName) {
        this.absoluteFileName = fileName;
    }

    /**
     * Get the display name.
     * @return displayName
     */
    public final String getDisplayName() {
        return displayName;
    }

    /**
     * Set the display name.
     * @param name display name
     */
    public final void setDisplayName(final String name) {
        this.displayName = name;
    }

    /**
     * Check if the file/folder has been recently updated.
     * @return <code>true</code> or <code>false</code>
     */
    public final boolean isRecentUpdate() {
        return recentUpdate;
    }

    /**
     * Set recent update for file/folder.
     * @param update (<code>true</code> or <code>false</code>
     */
    public final void setRecentUpdate(final boolean update) {
        this.recentUpdate = update;
    }

    /**
     * Equals.
     * @param other the other object
     * @return <code>true</code> or <code>false</code>
     */
    public final boolean equals(final Object other) {
        if (other == null || this.getClass() != other.getClass()) {
            return false;
        }
        return ObjectUtils.equals(this.absoluteFileName, ((ContentModel) other).absoluteFileName)
            && ObjectUtils.equals(this.displayName, ((ContentModel) other).displayName)
            && ObjectUtils.equals(this.recentUpdate, ((ContentModel) other).recentUpdate);
    }

    /**
     * Hashcode.
     * @return hashcode
     */
    public final int hashCode() {
        return ObjectUtils.hashCode(this.absoluteFileName) + ObjectUtils.hashCode(this.displayName)
            + ObjectUtils.hashCode(this.recentUpdate);
    }

}


