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

import net.sourceforge.jukebox.model.ContentModel;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

/**
 * Gets the contents.
 */
@Service
public class ContentProvider {

    /**
     * Logger object.
     */
    private static final Logger logger = LoggerFactory.getLogger("ContentProvider");

    /**
     * Constant used in calculating modified days.
     */
    private static final long MILLISECONDS_PER_DAY = 24L * 3600 * 1000;

    /**
     * Root folder.
     */
    @Value("${content.dir:/var/cache/tomcat5/content}")
    private String rootFolder;

    /**
     * Modified days.
     */
    @Value("${modified.days:7}")
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

        logger.info("Get content for {}", folder);

        int rootFolderLength = new File(rootFolder).getAbsolutePath().length();

        List<ContentModel> folders = new ArrayList<ContentModel>();
        List<ContentModel> files = new ArrayList<ContentModel>();

        String parentFolder;
        if (folder != null && folder.isEmpty()) {
            parentFolder = this.rootFolder;
        } else {
            parentFolder = this.rootFolder + File.separator + folder;
        }


        logger.info("Parent folder is {}", parentFolder);

        File parent = new File(parentFolder);

        File[] folderContents = parent.listFiles();

        for (File item : folderContents) {
            if (item.isFile() && !item.getName().endsWith(".mp3")) {
                continue;
            }
            ContentModel model = new ContentModel();
            model.setDisplayName(item.getName());
            String folderPath = item.getAbsolutePath().substring(rootFolderLength + 1);
            model.setAbsoluteFileName(folderPath.replace("\\", "/"));
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


        logger.info("Get all media files in {}", folder);

        Collection<String> files = new ArrayList<String>();

        int len = rootFolder.length();

        File file = new File(rootFolder + File.separator + folder);

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
