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

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertNotNull;
import static org.testng.Assert.assertTrue;
import static org.testng.Assert.fail;

import java.io.File;
import java.io.IOException;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import net.sourceforge.jukebox.model.ContentModel;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

/**
 * Tests {@link ContentProvider}.
 * Assumes there are no media files in the user's temp folder.
 * Otherwise, the tests are going to fail.
 */
public class ContentProviderTest {

    /**
     * Test folder.
     */
    private static final String TEST_FOLDER = "folder";
    /**
     * Root folder.
     */
    private static final String ROOT_FOLDER = System.getProperty("java.io.tmpdir") + File.separator + TEST_FOLDER;
    /**
     * Subfolder.
     */
    private static final String SUB_FOLDER = "subFolder";
    /**
     * Test filename.
     */
    private String media1;
    /**
     * Media file.
     */
    private String media2;

    /**
     * Set up the contents for the test.
     * @throws IOException IOException
     */
    @BeforeClass
    public final void setUp() throws IOException {
        File contentFolder = new File(ROOT_FOLDER);
        if (!contentFolder.mkdir()) {
            fail("Set up failed");
        }
        contentFolder.deleteOnExit();
        File mediaFile = File.createTempFile("dummy", ".mp3", contentFolder);
        mediaFile.deleteOnExit();
        media1 = mediaFile.getName();
        File subfolder = new File(ROOT_FOLDER, SUB_FOLDER);
        if (!subfolder.mkdir()) {
            fail("Set up failed");
        }
        subfolder.deleteOnExit();
        mediaFile = File.createTempFile("dummy1", ".mp3", subfolder);
        media2 = mediaFile.getName();
        mediaFile.deleteOnExit();
    }

    /**
     * Tests the method to get the content.
     *
     */
    @Test
    public final void testGetContent() {
        ContentProvider provider = new ContentProvider();
        provider.setRootFolder(ROOT_FOLDER);
        provider.setModifiedDays(1);
        Map<String, List<ContentModel>> contents = provider.getContent(null);
        assertNotNull(contents);
        List<ContentModel> files = contents.get("files");
        assertNotNull(files);
        assertEquals(files.size(), 1);
        ContentModel model = files.get(0);
        assertEquals(model.getDisplayName(), media1);
        assertEquals(model.isRecentUpdate(), true);
        List<ContentModel> folders = contents.get("dir");
        assertNotNull(folders);
        assertTrue(folders.size() >= 1);
    }

    /**
     * Tests the method to get the content of subfolder.
     */
    @Test
    public final void testGetContentInSubfolder() {
        ContentProvider provider = new ContentProvider();
        provider.setRootFolder(ROOT_FOLDER);
        provider.setModifiedDays(1);
        Map<String, List<ContentModel>> contents = provider.getContent(File.separator + SUB_FOLDER);
        assertNotNull(contents);
        List<ContentModel> files = contents.get("files");
        assertNotNull(files);
        assertEquals(files.size(), 1);
        ContentModel model = files.get(0);
        assertEquals(model.getDisplayName(), media2);
        assertEquals(model.isRecentUpdate(), true);
        List<ContentModel> folders = contents.get("dir");
        assertNotNull(folders);
        assertEquals(folders.size(), 0);
    }

    /**
     * Tests the method to get all the filenames.
     */
    @Test
    public final void testGetAllFileNames() {
        ContentProvider provider = new ContentProvider();
        provider.setRootFolder(ROOT_FOLDER);
        provider.setModifiedDays(1);
        Collection<String> mediaFiles = provider.getAllFileNames(File.separator + SUB_FOLDER);
        assertEquals(mediaFiles.size(), 1);
        Iterator<String> iterator = mediaFiles.iterator();
        while (iterator.hasNext()) {
            String mediaFile = iterator.next();
            assertTrue(mediaFile.indexOf(media2) >= 0);
        }
    }
}
