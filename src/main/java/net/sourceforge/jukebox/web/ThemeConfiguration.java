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

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ThemeResolver;

/**
 * Configure the theme.
 */
@Controller
@RequestMapping("/theme")
public class ThemeConfiguration {
    /**
     * Logger object.
     */
    private static final Logger logger = LoggerFactory.getLogger(ThemeConfiguration.class);

    /**
     * Theme resolver.
     */
    private ThemeResolver themeResolver;

    /**
     * Sets the theme resolver.
     * @param resolver theme resolver
     */
    @Inject
    public final void setThemeResolver(final ThemeResolver resolver) {
        this.themeResolver = resolver;
    }

    /**
     * Sets the theme.
     * @param theme theme name
     * @param request request
     * @param response response
     * @return success
     */
    @RequestMapping(method = RequestMethod.POST)
    public final String setTheme(@RequestParam("theme") final String theme, final HttpServletRequest request, final HttpServletResponse response) {
        logger.info("Changing the theme to " + theme);
        this.themeResolver.setThemeName(request, response, theme);
        return "redirect:/browse";
    }
}
