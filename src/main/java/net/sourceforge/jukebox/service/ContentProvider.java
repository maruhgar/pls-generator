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

package net.sourceforge.jukebox.service;

import java.io.File;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import net.sourceforge.jukebox.model.ContentModel;

/**
 * Gets the contents.
 */
public class ContentProvider {

    /**
     * Logger object.
     */
    private static final Logger logger = LoggerFactory.getLogger("ContentProvider");

    /**
     * Constant used in calculating modified days.
     */
    private static final long MILLISECONDS_PER_DAY = 24 * 3600 * 1000;

    /**
     * Root folder.
     */
    private String rootFolder;

    /**
     * Modified days.
     */
    private int modifiedDays;

    /**
     * The root folder holding media.
     * @param folder folder name
     */
    public final void setRootFolder(final String folder) {
        this.rootFolder = folder;
    }

    /**
     * Number of days since modification.
     * @param days days since modification
     */
    public final void setModifiedDays(final int days) {
        this.modifiedDays = days;
    }

    /**
     * Gets the content from the media folder.
     * @param folder folder holding the media
     * @return list of files and folders
     */
    public Map<String, List<ContentModel>> getContent(final String folder) {

        if (logger.isInfoEnabled()) {
            logger.info("Get content for " + folder);
        }

        int rootFolderLength = this.rootFolder.length();

        List<ContentModel> folders = new ArrayList<ContentModel>();
        List<ContentModel> files = new ArrayList<ContentModel>();

        String parentFolder;
        if (folder == null) {
            parentFolder = this.rootFolder;
        } else {
            parentFolder = this.rootFolder + folder;
        }

        if (logger.isInfoEnabled()) {
            logger.info("Parent folder is " + parentFolder);
        }

        File parent = new File(parentFolder);

        File[] folderContents = parent.listFiles();

        for (File item : folderContents) {
            if (item.isFile() && !item.getName().endsWith(".mp3")) {
                continue;
            }
            ContentModel model = new ContentModel();
            model.setDisplayName(item.getName());
            model.setAbsoluteFileName(item.getAbsolutePath().substring(rootFolderLength));
            model.setRecentUpdate(getUpdateStatus(item));

            if (item.isDirectory()) {
                folders.add(model);
            } else {
                files.add(model);
            }
        }

        Map<String, List<ContentModel>> contents = new HashMap<String, List<ContentModel>>();
        contents.put("dir", folders);
        contents.put("files", files);

        return contents;
    }

    /**
     * Check if the status of file is less than modified days.
     * @param item File or folder
     * @return <code>true</code> or <code>false</code>
     */
    private boolean getUpdateStatus(final File item) {

        boolean updateStatus = false;

        long now = System.currentTimeMillis();
        long modifiedTime = item.lastModified();

        if (now - modifiedTime < this.modifiedDays * MILLISECONDS_PER_DAY) {
            updateStatus = true;
        }

        return updateStatus;
    }

    /**
     * Get all the media files in the specified folder.
     * @param folder Folder name
     * @return Collection of media files
     */
    public Collection<String> getAllFileNames(final String folder) {

        if (logger.isInfoEnabled()) {
            logger.info("Get all media files in " + folder);
        }

        Collection<String> files = new ArrayList<String>();

        int len = rootFolder.length();

        File file = new File(rootFolder + folder);

        File[] listFile = file.listFiles();

        String fName;
        for (int i = 0; i < listFile.length; i++) {
            if (listFile[i].isDirectory()) {
                        getAllFileNames(listFile[i].getAbsolutePath().substring(len));
            }
            fName = listFile[i].getName();
            if (!listFile[i].isDirectory() && fName.endsWith(".mp3")) {

                files.add(listFile[i].getAbsolutePath().substring(len));
            }
        }

        return files;
    }
}
