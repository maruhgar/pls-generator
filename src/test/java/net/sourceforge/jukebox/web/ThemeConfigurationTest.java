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

import static org.mockito.Mockito.mock;
import static org.testng.Assert.assertEquals;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.web.servlet.ThemeResolver;
import org.testng.annotations.Test;
/**
 * Tests {@link ThemeConfiguration}.
 */
public class ThemeConfigurationTest {

    /**
     * Default theme.
     */
    private static final String DEFAULT_THEME = "default";
    /**
     * Success view.
     */
    private static final String SUCCESS_VIEW = "redirect:/browse";

    /**
     * Tests the method to set the theme.
     */
    @Test
    public final void testSetTheme() {
        ThemeResolver resolver = mock(ThemeResolver.class);
        ThemeConfiguration configuration = new ThemeConfiguration();
        HttpServletRequest request = new MockHttpServletRequest();
        HttpServletResponse response = new MockHttpServletResponse();
        configuration.setThemeResolver(resolver);
        String result = configuration.setTheme(DEFAULT_THEME, request, response);
        assertEquals(result, SUCCESS_VIEW);

    }
}
