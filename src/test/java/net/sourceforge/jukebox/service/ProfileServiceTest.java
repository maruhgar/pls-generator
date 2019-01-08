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

import javax.inject.Inject;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.testng.annotations.Test;

/**
 * Tests the methods of {@link ProfileService}.
 */
@ContextConfiguration
public class ProfileServiceTest extends AbstractTestNGSpringContextTests {

    /**
     * Constant test user.
     */
    private static final String TEST_USER = "admin";
    /**
     * Constant encoded password.
     */
    private static final String ENCODED_PASSWORD = "$2a$10$POSCjDORsHoRkwx.pdF2vuKSx8ZGJcUkuh7at4m2tIp0R5F9oddga";
    /**
     * Original password of user.
     */
    private static final String ORIGINAL_PASSWORD = "{bcrypt}$2a$10$9Ii6W9UIlBtGeMs27eZPn.pJFRz533tFD4/.BtIUPG7c71tCxxnGi";

    /**
     * Profile service object.
     */
    @Inject
    private ProfileService profileService;

    /**
     * Tests the method to update user context.
     */
    @Test
    public final void testUpdateUserContext() {
        Authentication authentication = new UsernamePasswordAuthenticationToken(TEST_USER, ENCODED_PASSWORD);
        SecurityContextHolder.getContext().setAuthentication(authentication);
        profileService.updateUserContext(TEST_USER);
        Authentication updatedAuthentication = SecurityContextHolder.getContext().getAuthentication();
        assertEquals(updatedAuthentication.getCredentials(), ORIGINAL_PASSWORD);
        SecurityContextHolder.getContext().setAuthentication(null);
    }

    /**
     * Tests the method to update user context.
     */
    @Test
    public final void testUpdateUserContextWithoutAuthentication() {
        profileService.updateUserContext(TEST_USER);
        Authentication updatedAuthentication = SecurityContextHolder.getContext().getAuthentication();
        assertEquals(updatedAuthentication.getCredentials(), ORIGINAL_PASSWORD);
    }

    public static void main(String[] args) {

    	int i = 0;
    	while (i < 10) {
    		String password = "testpassword";
    		BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
    		String hashedPassword = passwordEncoder.encode(password);

    		System.out.println(hashedPassword);
    		i++;
    	}

      }
}
