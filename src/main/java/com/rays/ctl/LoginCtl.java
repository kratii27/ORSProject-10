package com.rays.ctl;

import java.util.HashMap;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.rays.common.BaseCtl;
import com.rays.common.ORSResponse;
import com.rays.common.UserContext;
import com.rays.config.JWTUtil;
import com.rays.dto.UserDTO;
import com.rays.form.ForgetPasswordForm;
import com.rays.form.LoginForm;
import com.rays.form.UserForm;
import com.rays.form.UserRegistrationForm;
import com.rays.service.UserServiceInt;
import com.rays.util.EmailBuilder;
import com.rays.util.EmailMessage;
import com.rays.util.EmailUtility;

/**
 * REST controller for handling authentication-related operations in the ORS application.
 * <p>
 * Extends {@link BaseCtl} parameterized with {@link UserDTO}, {@link UserServiceInt},
 * and {@link UserForm}. All endpoints are accessible under the {@code /Auth} base URL
 * mapping and are publicly permitted without authentication as configured in
 * {@code WebSecurityConfig}.
 * </p>
 * <p>
 * Provides endpoints for user login, registration (sign up), logout,
 * and forgot password functionality.
 * </p>
 *
 * @author Krati Trivedi
 */
@RestController
@RequestMapping(value = "Auth")
public class LoginCtl extends BaseCtl<UserDTO, UserServiceInt, UserForm> {

    /**
     * Utility for generating JWT tokens upon successful authentication.
     */
    @Autowired
    private JWTUtil jwtUtil;

    /**
     * Authenticates a user with the provided login credentials and returns a JWT token.
     * <p>
     * Validates the form first. If credentials are valid, a JWT token is generated
     * and returned along with user details such as login, role, first name, and last name.
     * If authentication fails, an appropriate error message is returned.
     * </p>
     *
     * @param form          the {@link LoginForm} containing login ID and password,
     *                      validated via {@code @Valid}
     * @param bindingResult the result of form validation
     * @return an {@link ORSResponse} containing the JWT token and user details
     *         on success, or an error message on failure
     * @throws Exception if an error occurs during JWT token generation
     */
    @PostMapping("login")
    public ORSResponse login(@RequestBody @Valid LoginForm form, BindingResult bindingResult) throws Exception {

        ORSResponse res = validate(bindingResult);

        if (!res.isSuccess()) {
            return res;
        }

        UserDTO dto = service.authenticate(form.getLogin(), form.getPassword());

        if (dto == null) {
            res.setSuccess(false);
            res.addMessage("Invalid Login ID & Password");
        } else {

            UserContext context = new UserContext(dto);

            String token = jwtUtil.generateToken(dto.getId(), dto.getLogin(), dto.getRoleName());

            res.setSuccess(true);
            res.addData(dto);
            res.addResult("login", dto.getLogin());
            res.addResult("role", dto.getRoleName());
            res.addResult("fname", dto.getFirstName());
            res.addResult("lname", dto.getLastName());
            res.addResult("token", token);
            return res;
        }
        return res;
    }

    /**
     * Registers a new user in the ORS application.
     * <p>
     * Validates the registration form and checks whether the login ID already exists.
     * If the login ID is unique, a new user is created with an {@code Inactive} status
     * and role ID {@code 2}. A registration confirmation email is sent to the user
     * upon successful registration.
     * </p>
     *
     * @param form          the {@link UserRegistrationForm} containing user registration
     *                      details, validated via {@code @Valid}
     * @param bindingResult the result of form validation
     * @return an {@link ORSResponse} indicating success or failure of the registration
     */
    @PostMapping("signUp")
    public ORSResponse signUp(@RequestBody @Valid UserRegistrationForm form, BindingResult bindingResult) {

        ORSResponse res = validate(bindingResult);

        if (!res.isSuccess()) {
            return res;
        }

        UserDTO dto = service.findByLogin(form.getLogin(), userContext);

        if (dto != null) {
            res.setSuccess(false);
            res.addMessage("Login Id already exists");
            return res;
        }

        dto = new UserDTO();
        dto.setFirstName(form.getFirstName());
        dto.setLastName(form.getLastName());
        dto.setLogin(form.getLogin());
        dto.setPassword(form.getPassword());
        dto.setDob(form.getDob());
        dto.setGender(form.getGender());
        dto.setPhoneNo(form.getPhone());

        dto.setStatus("Inactive");
        dto.setRoleId(2L);

        service.register(dto, userContext);

        res.setSuccess(true);
        res.addMessage("User has been registered successfully..!!");

        HashMap<String, String> map = new HashMap<String, String>();
        map.put("login", dto.getLogin());
        map.put("password", dto.getPassword());

        String message = EmailBuilder.getUserRegistrationMessage(map);

        EmailMessage msg = new EmailMessage();

        msg.setTo(dto.getLogin());
        msg.setSubject("Registration is successful for ORSProject-10");
        msg.setMessage(message);
        msg.setMessageType(EmailMessage.HTML_MSG);

        EmailUtility.sendMail(msg);

        return res;
    }

    /**
     * Logs out the currently authenticated user by invalidating the HTTP session.
     *
     * @param session the current {@link HttpSession} to be invalidated
     * @return an {@link ORSResponse} confirming successful logout
     * @throws Exception if an error occurs during session invalidation
     */
    @GetMapping("logout")
    public ORSResponse logout(HttpSession session) throws Exception {

        ORSResponse res = new ORSResponse();

        session.invalidate();

        res.addMessage("Logout successfully..!!");

        return res;
    }

    /**
     * Handles forgot password requests by sending the user's password to their
     * registered email address.
     * <p>
     * Validates the form and looks up the user by login ID. If the user is found,
     * their password is retrieved and sent via an HTML email. If the login ID is
     * not found, an appropriate error message is returned.
     * </p>
     *
     * @param form          the {@link ForgetPasswordForm} containing the user's login ID,
     *                      validated via {@code @Valid}
     * @param bindingResult the result of form validation
     * @return an {@link ORSResponse} indicating whether the password email was sent
     *         successfully or if the login ID was not found
     */
    @PostMapping("forgetPassword")
    public ORSResponse forgetPassword(@RequestBody @Valid ForgetPasswordForm form, BindingResult bindingResult) {

        ORSResponse res = validate(bindingResult);

        if (!res.isSuccess()) {
            return res;
        }

        UserDTO fDto = service.forgotPassword(form.getLogin());

        if (fDto == null) {
            res.setSuccess(false);
            res.addMessage("LoginId / Email not found.");
            return res;
        } else {
            HashMap<String, String> map = new HashMap<String, String>();
            map.put("login", fDto.getLogin());
            map.put("password", fDto.getPassword());
            map.put("firstName", fDto.getFirstName());
            map.put("lastName", fDto.getLastName());

            String message = EmailBuilder.getForgetPasswordMessage(map);

            EmailMessage msg = new EmailMessage();
            msg.setTo(fDto.getLogin());
            msg.setSubject("ORSProject-10 Password Reset");
            msg.setMessage(message);
            msg.setMessageType(EmailMessage.HTML_MSG);

            EmailUtility.sendMail(msg);
            res.setSuccess(true);
            res.addMessage("Hello " + fDto.getFirstName() + " " + fDto.getLastName()
                    + "..! Your password has been sent on your email.");
        }
        return res;
    }
}