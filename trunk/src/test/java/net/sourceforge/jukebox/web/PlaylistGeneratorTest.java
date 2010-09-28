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

import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import static org.testng.Assert.assertNotNull;
import static org.testng.Assert.assertTrue;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import net.sourceforge.jukebox.service.ContentProvider;

import org.springframework.context.support.StaticApplicationContext;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

/**
 * Tests {@link PlaylistGenerator}.
 */
public class PlaylistGeneratorTest {

    /**
     * Constant files to be passed for test.
     */
    private static final String[] FILES = { "file1", "file2" };
    /**
     * Constant folder names to be passed for test.
     */
    private static final String[] FOLDERS = { "folder1", "folder2" };
    /**
     * Constant host name.
     */
    private static final String HOSTNAME = "localhost";
    /**
     * Content provider object.
     */
    private ContentProvider contentProvider;
    /**
     * Application context.
     */
    private StaticApplicationContext applicationContext;

    /**
     * Initializes needed variables.
     */
    @BeforeMethod
    public final void setUp() {
        contentProvider = mock(ContentProvider.class);
        applicationContext = new StaticApplicationContext();
        applicationContext.refresh();
        applicationContext.addMessage("host.name", Locale.ENGLISH, HOSTNAME);
    }

    /**
     * Tests the method to list the contents.
     * @throws Exception Exception
     */
    @Test
    public final void testListContents() throws Exception {

        when(contentProvider.getAllFileNames(anyString())).thenReturn(getDummyContent());

        PlaylistGenerator playlistGenerator = new PlaylistGenerator();
        playlistGenerator.setApplicationContext(applicationContext);
        playlistGenerator.setContentProvider(contentProvider);

        ResponseEntity<byte[]> response = playlistGenerator.listContents(FILES, FOLDERS);

        HttpHeaders headers = response.getHeaders();
        assertNotNull(headers);
        assertTrue(headers.getContentType().toString().indexOf("audio/x-scpls") >= 0);
        assertTrue(headers.get("Content-Disposition").get(0).indexOf("filename=playlist.pls") >= 0);

        byte[] body = response.getBody();
        assertNotNull(body);

        String playlist = new String(body);
        assertTrue(playlist.indexOf("[Playlist]") >= 0);

    }

    /**
     * Get the dummy list of media files.
     * @return list of media files
     */
    private List<String> getDummyContent() {

        List<String> files = new ArrayList<String>();
        files.add("media1.mp3");
        files.add("media2.mp3");
        files.add("media3.mp3");
        files.add("media4.mp3");

        return files;
    }
}
