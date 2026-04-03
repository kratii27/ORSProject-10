package com.rays.ctl;

import java.io.OutputStream;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.rays.common.BaseCtl;
import com.rays.common.DropdownList;
import com.rays.common.ORSResponse;
import com.rays.common.UserContext;
import com.rays.dto.AttachmentDTO;
import com.rays.dto.RoleDTO;
import com.rays.dto.UserDTO;
import com.rays.form.ChangePasswordForm;
import com.rays.form.MyProfileForm;
import com.rays.form.UserForm;
import com.rays.service.AttachmentServiceInt;
import com.rays.service.RoleServiceInt;
import com.rays.service.UserServiceInt;


/**
 * REST controller for managing User-related operations in the ORS application.
 * <p>
 * Extends {@link BaseCtl} to inherit common CRUD and search endpoints,
 * parameterized with {@link UserDTO}, {@link UserServiceInt}, and {@link UserForm}.
 * All endpoints are accessible under the {@code /User} base URL mapping.
 * </p>
 * <p>
 * Provides additional endpoints for preloading role dropdown data, updating
 * user profiles, changing passwords, and uploading or downloading profile pictures.
 * </p>
 *
 * @author Krati Trivedi
 */
@RestController
@RequestMapping(value = "User")
public class UserCtl extends BaseCtl<UserDTO, UserServiceInt, UserForm> {

    /**
     * Service for retrieving Role data used to populate the role dropdown.
     */
    @Autowired
    RoleServiceInt roleService;

    /**
     * Service for handling file attachment operations such as profile picture
     * upload and download.
     */
    @Autowired
    AttachmentServiceInt attachmentService;

    /**
     * Preloads dropdown data required for the User form.
     * <p>
     * Retrieves and returns a list of all available roles to be used
     * as dropdown options on the client side.
     * </p>
     *
     * @return an {@link ORSResponse} containing the role dropdown list
     *         under the key {@code roleList}
     */
    @GetMapping(value = "preload")
    public ORSResponse preload() {
        ORSResponse res = new ORSResponse(true);

        RoleDTO dto = new RoleDTO();
        List<DropdownList> list = roleService.search(dto, userContext);
        res.addResult("roleList", list);

        return res;
    }

    /**
     * Updates the profile information of the currently authenticated user.
     * <p>
     * Validates the form first, then retrieves the existing user record and
     * updates personal details such as first name, last name, date of birth,
     * phone number, and gender.
     * </p>
     *
     * @param form          the {@link MyProfileForm} containing updated profile
     *                      details, validated via {@code @Valid}
     * @param bindingResult the result of form validation
     * @return an {@link ORSResponse} indicating success or failure of the
     *         profile update operation
     */
    @PostMapping("myProfile")
    public ORSResponse myProfile(@RequestBody @Valid MyProfileForm form, BindingResult bindingResult) {

        ORSResponse res = validate(bindingResult);

        if (!res.isSuccess()) {
            return res;
        }

        UserDTO dto = service.findByLogin(userContext.getLoginId(), userContext);
        dto.setFirstName(form.getFirstName());
        dto.setLastName(form.getLastName());
        dto.setDob(form.getDob());
        dto.setPhoneNo(form.getPhoneNo());
        dto.setGender(form.getGender());

        service.update(dto, userContext);

        res.setSuccess(true);
        res.addMessage("Your Profile updated successfully..!!");

        return res;
    }

    /**
     * Changes the password of the currently authenticated user.
     * <p>
     * Validates the form first, then delegates to the service to verify the old
     * password and apply the new password. On success, a confirmation email is
     * sent to the user's registered email address. If the old password is incorrect,
     * an appropriate error message is returned.
     * </p>
     *
     * @param form          the {@link ChangePasswordForm} containing the login,
     *                      old password, and new password, validated via {@code @Valid}
     * @param bindingResult the result of form validation
     * @return an {@link ORSResponse} indicating whether the password was changed
     *         successfully or if the old password was invalid
     */
    @PostMapping("changePassword")
    public ORSResponse changePassword(@RequestBody @Valid ChangePasswordForm form, BindingResult bindingResult) {

        ORSResponse res = validate(bindingResult);

        if (!res.isSuccess()) {
            return res;
        }

        UserDTO changedDto = service.changePassword(form.getLogin(), form.getOldPassword(), form.getNewPassword(),
                userContext);

        if (changedDto == null) {
            res.setSuccess(false);
            res.addMessage("Invalid old password");
            return res;
        }

        res.setSuccess(true);
        res.addMessage("Password has been changed");

        return res;
    }

    /**
     * Uploads a profile picture for the specified user.
     * <p>
     * Accepts a multipart file and saves it as an {@link AttachmentDTO}.
     * If the user already has a profile picture, the existing attachment is
     * updated; otherwise a new one is created and the user's image ID is set.
     * </p>
     *
     * @param userId the ID of the user whose profile picture is being uploaded
     * @param file   the {@link MultipartFile} containing the image to upload
     * @param req    the {@link HttpServletRequest} for the current request
     * @return an {@link ORSResponse} containing the saved image ID under
     *         the key {@code imageId}
     */
    @PostMapping("/profilePic/{userId}")
    public ORSResponse uploadPic(@PathVariable Long userId, @RequestParam("file") MultipartFile file,
            HttpServletRequest req) {

        AttachmentDTO attachmentDto = new AttachmentDTO(file);

        attachmentDto.setDescription("profile pic");

        attachmentDto.setUserId(userId);

        UserDTO userDto = service.findByPk(userId, userContext);

        if (userDto.getImageId() != null && userDto.getImageId() > 0) {

            attachmentDto.setId(userDto.getImageId());

        }

        Long imageId = attachmentService.save(attachmentDto, userContext);

        if (userDto.getImageId() == null) {

            userDto.setImageId(imageId);

            service.update(userDto, userContext);
        }

        ORSResponse res = new ORSResponse();

        res.addResult("imageId", imageId);
        res.setSuccess(true);

        return res;
    }

    /**
     * Downloads and streams the profile picture of the specified user.
     * <p>
     * Retrieves the user's associated attachment and writes the image bytes
     * directly to the HTTP response output stream with the appropriate content type.
     * If no profile picture is found, an error message is written to the response.
     * </p>
     *
     * @param userId   the ID of the user whose profile picture is to be downloaded
     * @param response the {@link HttpServletResponse} used to stream the image data
     */
    @GetMapping("/profilePic/{userId}")
    public void downloadPic(@PathVariable Long userId, HttpServletResponse response) {

        try {

            UserDTO userDto = service.findByPk(userId, userContext);

            AttachmentDTO attachmentDTO = null;

            if (userDto != null) {
                attachmentDTO = attachmentService.findByPk(userDto.getImageId(), userContext);
            }

            if (attachmentDTO != null) {
                response.setContentType(attachmentDTO.getType());
                OutputStream out = response.getOutputStream();
                out.write(attachmentDTO.getDoc());
                out.close();
            } else {
                response.getWriter().write("ERROR: File not found");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}