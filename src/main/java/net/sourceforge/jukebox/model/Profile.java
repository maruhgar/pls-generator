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

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.apache.commons.configuration.ConfigurationException;
import org.apache.commons.configuration.FileConfiguration;
import org.apache.commons.lang.ObjectUtils;

/**
 * Holds profile information.
 */
@PasswordMatch(message = "Password and confirm password must match")
public class Profile {

    /**
     * Constant admin username for the application.
     */
    public static final String ADMIN_USERNAME = "Administrator";
    /**
     * Constant value for size of password.
     */
    private static final int MIN_PASSWORD_SIZE = 6;

    /**
     * Error message for password size violation.
     */
    private static final String PASSWORD_SIZE_ERROR = "Size must be between 6 and 14";
    /**
     * Existing password.
     */
    @NotNull
    @Size(min = MIN_PASSWORD_SIZE, message = PASSWORD_SIZE_ERROR)
    @ValidCurrentPassword(message = "Current password is invalid")
    private String oldPassword;

    /**
     * New password.
     */
    @NotNull
    @Size(min = MIN_PASSWORD_SIZE, message = PASSWORD_SIZE_ERROR)
    private String newPassword;

    /**
     * Re-entered new password.
     */
    @NotNull
    @Size(min = MIN_PASSWORD_SIZE, message = PASSWORD_SIZE_ERROR)
    private String confirmPassword;

    /**
     * Spring security related information.
     */
    private String userInfo;

    /**
     * @return the oldPassword
     */
    public final String getOldPassword() {
        return oldPassword;
    }

    /**
     * @param password the oldPassword to set
     */
    public final void setOldPassword(final String password) {
        this.oldPassword = password;
    }

    /**
     * @return the newPassword
     */
    public final String getNewPassword() {
        return newPassword;
    }

    /**
     * @param password the newPassword to set
     */
    public final void setNewPassword(final String password) {
        this.newPassword = password;
    }

    /**
     * @return the confirmPassword
     */
    public final String getConfirmPassword() {
        return confirmPassword;
    }

    /**
     * @param password the confirmPassword to set
     */
    public final void setConfirmPassword(final String password) {
        this.confirmPassword = password;
    }

    /**
     * @return the userInfo
     */
    public final String getUserInfo() {
        return userInfo;
    }

    /**
     * @param info the userInfo to set
     */
    public final void setUserInfo(final String info) {
        this.userInfo = info;
    }


    /**
     * Load the contents of the configuration file.
     * @param configuration Configuration file
     */
    public final void load(final FileConfiguration configuration) {
        String value = configuration.getString(ADMIN_USERNAME);
        int passwordLength = value.indexOf(',');
        this.oldPassword = value.substring(0, passwordLength);
        this.userInfo = value.substring(passwordLength + 1);
    }

    /**
     * Save the contents to configuration file.
     * @param configuration Configuration file
     * @throws ConfigurationException Configuration Exception
     */
    public final void save(final FileConfiguration configuration) throws ConfigurationException {
        String value = this.newPassword + "," + this.userInfo;
        configuration.setProperty(ADMIN_USERNAME, value);
        configuration.save();
    }

    /* (non-Javadoc)
     * @see java.lang.Object#hashCode()
     */
    @Override
    public final int hashCode() {
        return ObjectUtils.hashCode(this.oldPassword)
        + ObjectUtils.hashCode(this.newPassword)
        + ObjectUtils.hashCode(this.confirmPassword)
        + ObjectUtils.hashCode(this.userInfo);
    }

    /* (non-Javadoc)
     * @see java.lang.Object#equals(java.lang.Object)
     */
    @Override
    public final boolean equals(final Object other) {
        if (other == null || this.getClass() != other.getClass()) {
            return false;
        }
        return ObjectUtils.equals(this.oldPassword, ((Profile) other).oldPassword)
        && ObjectUtils.equals(this.newPassword, ((Profile) other).newPassword)
        && ObjectUtils.equals(this.confirmPassword, ((Profile) other).confirmPassword)
        && ObjectUtils.equals(this.userInfo, ((Profile) other).userInfo);
    }

}
