
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

package net.sourceforge.jukebox.model;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;

/**
 * Checks if new password and confirm password match.
 */
public class CurrentPasswordValidator implements ConstraintValidator<ValidCurrentPassword, String> {

    /**
     * Authentication manager object to change authentication info after password update.
     */
    private AuthenticationManager authenticationManager;

    /**
     * @param authenticationManager the authenticationManager to set
     */
    @Autowired
    public final void setAuthenticationManager(final AuthenticationManager authenticationManager) {
        this.authenticationManager = authenticationManager;
    }

    @Override
    public void initialize(final ValidCurrentPassword arg0) {
        // nothing to do.
    }

    @Override
    public final boolean isValid(final String password, final ConstraintValidatorContext context) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (auth == null || auth.getPrincipal() == null) {
            return true;
        }
        try {
            this.authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(auth.getPrincipal(), password));
        } catch (AuthenticationException e) {
            return false;
        }
        return true;
    }

}
