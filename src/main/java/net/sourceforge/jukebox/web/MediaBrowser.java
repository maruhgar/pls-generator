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

import java.io.ByteArrayOutputStream;
import java.io.PrintWriter;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;

import net.sourceforge.jukebox.model.ContentModel;
import net.sourceforge.jukebox.service.ContentProvider;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.util.AntPathMatcher;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.HandlerMapping;
import org.springframework.web.servlet.ModelAndView;

/**
 * Controller to browse the media folder.
 */
@Controller
public class MediaBrowser {

    /**
     * Logger object.
     */
    private static final Logger logger = LoggerFactory.getLogger("MediaBrowser");

    /**
     * Content provider.
     */
    private ContentProvider contentProvider;

    /**
     * Sets the content provider service.
     * @param provider Content Provider
     */
    @Inject
    public final void setContentProvider(final ContentProvider provider) {
        this.contentProvider = provider;
    }

    /**
     * List the contents of the media folder.
     * @param request Servlet request
     * @return success view
     */
    @RequestMapping(method = RequestMethod.GET, value="/browse/**")
	public final ModelAndView listContents(HttpServletRequest request) {


        String pattern = (String)
            request.getAttribute(HandlerMapping.BEST_MATCHING_PATTERN_ATTRIBUTE);

        String folder = new AntPathMatcher().extractPathWithinPattern(pattern,
            request.getServletPath());
        logger.info("Listing contents of {}", folder);
        ModelAndView mav = new ModelAndView();
        Map<String, List<ContentModel>> contents = this.contentProvider.getContent(folder);
        mav.setViewName("browse");
        mav.addObject("dir", contents.get("dir"));
        mav.addObject("files", contents.get("files"));
        return mav;
    }

    /**
     * Handles error scenarios.
     * @param e Exception
     * @return Error view
     */
    @ExceptionHandler(Exception.class)
    public final ModelAndView handleErrors(final Exception e) {
        logger.error("Encountered error", e);
        ModelAndView mav = new ModelAndView();
        mav.setViewName("error");
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        e.printStackTrace(new PrintWriter(baos, true));
        String stackTrace = baos.toString();
        mav.addObject("errorStackTrace", stackTrace);
        return mav;
    }
}
