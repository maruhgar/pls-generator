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
import org.springframework.security.authentication.dao.SaltSource;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
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
    private static final String TEST_USER = "Administrator";
    /**
     * Constant test password string.
     */
    private static final String TEST_PASSWORD = "testpassword";
    /**
     * Constant encoded password.
     */
    private static final String ENCODED_PASSWORD = "92be23767a5884077447a10fc31b637867a78eb1415b026d408114df147ae3e7";
    /**
     * Constant encoded password without salt.
     */
    private static final String ENCODED_WITHOUT_SALT = "9f735e0df9a1ddc702bf0a1a7b83033f9f7153a00c29de82cedadc9957289b05";
    /**
     * Original password of user.
     */
    private static final String ORIGINAL_PASSWORD = "c870f0ff431973e34eb667c00e2f0e63dad16e4ed618c965e5786a2c34711c57";

    /**
     * Profile service object.
     */
    @Inject
    private ProfileService profileService;

    /**
     * Tests the method to encode password.
     */
    @Test
    public final void testEncodePassword() {
        assertEquals(profileService.encodePassword(TEST_PASSWORD), ENCODED_PASSWORD);
    }

    /**
     * Tests the method to encode the password passing no salt.
     */
    @Test
    public final void testEncodePasswordWithNullSalt() {
        SaltSource saltSource = profileService.getSaltSource();
        profileService.setSaltSource(null);
        assertEquals(profileService.encodePassword(TEST_PASSWORD), ENCODED_WITHOUT_SALT);
        profileService.setSaltSource(saltSource);
    }

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
}
