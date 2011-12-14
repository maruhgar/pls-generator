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

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import net.sourceforge.jukebox.model.ContentModel;
import net.sourceforge.jukebox.service.ContentProvider;

import org.springframework.test.web.ModelAndViewAssert;
import org.springframework.web.servlet.ModelAndView;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

/**
 * Tests {@link MediaBrowser}.
 */
public class MediaBrowserTest {

    /**
     * Constant for folder name.
     */
    private static final String FOLDER = "dummyFolder";

    /**
     * Content provider object.
     */
    private ContentProvider contentProvider;

    /**
     * Initializes needed variables.
     */
    @BeforeMethod
    public final void setUp() {
        contentProvider = mock(ContentProvider.class);
    }

    /**
     * Tests the method to get the contents.
     */
    @Test
    public final void testListContents() {
        String folder = null;

        when(contentProvider.getContent(anyString())).thenReturn(getDummyContent());

        MediaBrowser mediaBrowser = new MediaBrowser();
        mediaBrowser.setContentProvider(contentProvider);

        // Parameter is null
        ModelAndView modelAndView = mediaBrowser.listContents(folder);
        ModelAndViewAssert.assertModelAttributeAvailable(modelAndView, "dir");
        ModelAndViewAssert.assertModelAttributeAvailable(modelAndView, "files");

        folder = FOLDER;

        // Folder name passed as parameter
        modelAndView = mediaBrowser.listContents(folder);
        ModelAndViewAssert.assertModelAttributeAvailable(modelAndView, "dir");
        ModelAndViewAssert.assertModelAttributeAvailable(modelAndView, "files");

    }

    /**
     * Tests exception handling.
     */
    @Test
    public final void testExceptionHandling() {
        IllegalArgumentException e = new IllegalArgumentException();

        MediaBrowser mediaBrowser = new MediaBrowser();
        mediaBrowser.setContentProvider(contentProvider);

        ModelAndView modelAndView = mediaBrowser.handleErrors(e);

        ModelAndViewAssert.assertViewName(modelAndView, "error");
        ModelAndViewAssert.assertModelAttributeAvailable(modelAndView, "errorStackTrace");

    }

    /**
     * Get the dummy content for the test.
     * @return map containing the dummy content.
     */
    private Map<String, List<ContentModel>> getDummyContent() {
        Map<String, List<ContentModel>> content = new HashMap<String, List<ContentModel>>();

        List<ContentModel> files = new ArrayList<ContentModel>();
        files.add(createContentModel("dummy media 1", "/tmp/folder/dummy media", false));
        files.add(createContentModel("dummy media 2", "/tmp/folder1/dummy media 2", true));
        content.put("files", files);

        List<ContentModel> folders = new ArrayList<ContentModel>();
        folders.add(createContentModel("dummyFolder", "/tmp/dummyFolder", false));
        folders.add(createContentModel("dummyFolder2", "/tmp/dummyFolder2", true));
        content.put("dir", folders);

        return content;
    }

    /**
     * Create a content model.
     * @param displayName Display Name
     * @param path Media path
     * @param recent Whether recent or not
     * @return content model
     */
    private ContentModel createContentModel(final String displayName, final String path, final boolean recent) {
        ContentModel model = new ContentModel();
        model.setDisplayName(displayName);
        model.setAbsoluteFileName(path);
        model.setRecentUpdate(recent);
        return model;
    }
}
