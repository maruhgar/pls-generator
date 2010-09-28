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

import java.util.Collection;
import java.util.Locale;

import net.sourceforge.jukebox.service.ContentProvider;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;


/**
 * Sends the playlist based on files/folders selected.
 */
@Controller
@RequestMapping("/generate")
public class PlaylistGenerator {

    /**
     * Logger object.
     */
    private static final Logger logger = LoggerFactory.getLogger("PlaylistGenerator");

    /**
     * Content provider.
     */
    private ContentProvider contentProvider;

    /**
     * Application context.
     */
    private ApplicationContext applicationContext;

    /**
     * Sets the content provider service.
     * @param provider Content Provider
     */
    @Autowired
    public final void setContentProvider(final ContentProvider provider) {
        this.contentProvider = provider;
    }

    /**
     * Sets the application context.
     * @param context Application Context
     */
    @Autowired
    public final void setApplicationContext(final ApplicationContext context) {
        this.applicationContext = context;
    }

    /**
     * Generates the play list.
     * @param files List of media files
     * @param folders List of folders
     * @return Playlist file
     * @throws Exception Exception
     */
    @RequestMapping(method = RequestMethod.POST)
    public final ResponseEntity<byte[]> listContents(@RequestParam(value = "mp3List", required = false) final String[] files,
            @RequestParam(value = "dirList", required = false) final String[] folders) throws Exception {
        if (logger.isInfoEnabled()) {
            logger.info("Generating playlist");
        }
        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-type", "audio/x-scpls");
        headers.add("Content-Disposition", "filename=playlist.pls");
        return new ResponseEntity<byte[]>(getPlaylist(files, folders), headers, HttpStatus.OK);
    }

    /**
     * Generates the play list.
     * @param files List of media files
     * @param folders List of folders
     * @return Play list contents
     */
    private byte[] getPlaylist(final String[] files, final String[] folders) {
        StringBuilder stringBuilder = new StringBuilder();
        int count = 0;

        stringBuilder.append("[Playlist]\n");

        if (folders != null) {
            for (int i = 0; i < folders.length; i++) {
                Collection<String> folderContents = contentProvider.getAllFileNames(folders[i]);
                for (String fileName : folderContents) {
                    count++;
                    stringBuilder.append(getPlaylistForFile(fileName, count));
                }
            }
        }

        if (files != null) {
            for (int i = 0; i < files.length; i++) {
                count++;
                stringBuilder.append(getPlaylistForFile(files[i], count));
            }
        }

        stringBuilder.append("NumberOfEntries=" + count + "\n");
        stringBuilder.append("Version=2" + "\n");
        return stringBuilder.toString().getBytes();
    }

    /**
     * Generates a play list for a media file.
     * @param fileName Media file
     * @param count Count of the media
     * @return Play list string
     */
    private String getPlaylistForFile(final String fileName, final int count) {
        String playerUrl = this.applicationContext.getMessage("host.name", null, Locale.ENGLISH);
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("File"
                + count
                + "="
                + playerUrl
                + fileName.replace('\\', '/').replaceAll(" ", "%20")
                + "\n");
        stringBuilder.append("Title" + count + "=" + fileName + "\n");
        stringBuilder.append("Length" + count + "=-1" + "\n");
        return stringBuilder.toString();
    }
}
