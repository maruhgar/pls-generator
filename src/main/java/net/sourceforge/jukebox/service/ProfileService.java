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

import javax.inject.Inject;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

/**
 * Handles operations related to user profile.
 */
@Service
public class ProfileService {

    /**
     * Password encoder class.
     */
    private PasswordEncoder passwordEncoder;

    /**
     * User details service.
     */
    private UserDetailsService userDetailsService;

    /**
     * @param encoder the passwordEncoder to set
     */
    @Inject
    public final void setPasswordEncoder(final PasswordEncoder encoder) {
        this.passwordEncoder = encoder;
    }

    /**
     * @param userDetails the userDetailsService to set
     */
    @Inject
    public final void setUserDetailsService(final UserDetailsService userDetails) {
        this.userDetailsService = userDetails;
    }

    /**
     * Encodes a gives password using the specified encoder and salt.
     * @param password Clear text password to be encoded.
     * @return Encoded password.
     */
    public String encodePassword(final String password) {
        return this.passwordEncoder.encode(password);
    }

    /**
     * Update the user context in the session after password change.
     * @param user username
     */
    public void updateUserContext(final String user) {
        Authentication currentAuth = SecurityContextHolder.getContext().getAuthentication();

        UserDetails userDetails = userDetailsService.loadUserByUsername(user);

        UsernamePasswordAuthenticationToken newAuth = new UsernamePasswordAuthenticationToken(userDetails.getUsername(), userDetails.getPassword(), userDetails.getAuthorities());

        if (currentAuth != null) {
            newAuth.setDetails(currentAuth.getDetails());
        }
        SecurityContextHolder.getContext().setAuthentication(newAuth);
    }
}
