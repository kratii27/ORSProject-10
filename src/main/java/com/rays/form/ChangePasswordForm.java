package com.rays.form;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

import com.rays.common.BaseForm;

/**
 * Form class for handling change password requests in the ORS application.
 * <p>
 * Extends {@link BaseForm} and carries the user's login ID, old password,
 * and new password. Validation constraints are applied to ensure both
 * password fields are non-empty and within the accepted length range.
 * </p>
 *
 * @author Krati Trivedi
 */
public class ChangePasswordForm extends BaseForm {

    /**
     * The user's current (old) password.
     * Must not be empty and must be between 2 and 10 characters.
     */
    @NotEmpty(message = "Old Password is required")
    @Size(min = 2, max = 10)
    private String oldPassword;

    /**
     * The user's new password to replace the old one.
     * Must not be empty and must be between 2 and 10 characters.
     */
    @NotEmpty(message = "New Password is required")
    @Size(min = 2, max = 10)
    private String newPassword;

    /**
     * The login ID (typically email) of the user requesting the password change.
     */
    private String login;

    /**
     * Returns the user's current (old) password.
     *
     * @return the old password as a {@link String}
     */
    public String getOldPassword() {
        return oldPassword;
    }

    /**
     * Sets the user's current (old) password.
     *
     * @param oldPassword the old password to set
     */
    public void setOldPassword(String oldPassword) {
        this.oldPassword = oldPassword;
    }

    /**
     * Returns the user's new password.
     *
     * @return the new password as a {@link String}
     */
    public String getNewPassword() {
        return newPassword;
    }

    /**
     * Sets the user's new password.
     *
     * @param newPassword the new password to set
     */
    public void setNewPassword(String newPassword) {
        this.newPassword = newPassword;
    }

    /**
     * Returns the login ID of the user requesting the password change.
     *
     * @return the login ID as a {@link String}
     */
    public String getLogin() {
        return login;
    }

    /**
     * Sets the login ID of the user requesting the password change.
     *
     * @param login the login ID to set
     */
    public void setLoginId(String login) {
        this.login = login;
    }
}