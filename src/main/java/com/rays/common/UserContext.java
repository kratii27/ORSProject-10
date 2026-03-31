package com.rays.common;

import com.rays.dto.UserDTO;

/**
 * Represents the context of the currently authenticated user in the ORS application.
 * <p>
 * Holds essential user information such as user ID, login ID, name, and role details,
 * which are used throughout the application for authorization, auditing, and
 * personalization purposes.
 * </p>
 *
 * @author Krati Trivedi
 */
public class UserContext {

    /**
     * The unique identifier of the logged-in user. Defaults to {@code 0}.
     */
    private Long userId = 0l;

    /**
     * The login ID (typically email) of the logged-in user. Defaults to {@code "root"}.
     */
    private String loginId = "root";

    /**
     * The full name of the logged-in user. Defaults to {@code null}.
     */
    private String name = null;

    /**
     * The role ID assigned to the logged-in user. Defaults to {@code 1}.
     */
    private Long roleId = 1l;

    /**
     * The role name assigned to the logged-in user. Defaults to {@code "root"}.
     */
    private String roleName = "root";

    /**
     * The {@link UserDTO} instance associated with the logged-in user.
     * Defaults to {@code null}.
     */
    private UserDTO userDTO = null;

    /**
     * Default constructor that creates an empty {@code UserContext}
     * with default field values.
     */
    public UserContext() {
        // TODO Auto-generated constructor stub
    }

    /**
     * Returns the unique identifier of the logged-in user.
     *
     * @return the user ID as a {@link Long}
     */
    public Long getUserId() {
        return userId;
    }

    /**
     * Sets the unique identifier of the logged-in user.
     *
     * @param userId the user ID to set
     */
    public void setUserId(Long userId) {
        this.userId = userId;
    }

    /**
     * Returns the login ID of the logged-in user.
     *
     * @return the login ID as a {@link String}
     */
    public String getLoginId() {
        return loginId;
    }

    /**
     * Sets the login ID of the logged-in user.
     *
     * @param loginId the login ID to set
     */
    public void setLoginId(String loginId) {
        this.loginId = loginId;
    }

    /**
     * Returns the full name of the logged-in user.
     *
     * @return the user's name as a {@link String}, or {@code null} if not set
     */
    public String getName() {
        return name;
    }

    /**
     * Sets the full name of the logged-in user.
     *
     * @param name the name to set
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Returns the role ID assigned to the logged-in user.
     *
     * @return the role ID as a {@link Long}
     */
    public Long getRoleId() {
        return roleId;
    }

    /**
     * Sets the role ID assigned to the logged-in user.
     *
     * @param roleId the role ID to set
     */
    public void setRoleId(Long roleId) {
        this.roleId = roleId;
    }

    /**
     * Returns the role name assigned to the logged-in user.
     *
     * @return the role name as a {@link String}
     */
    public String getRoleName() {
        return roleName;
    }

    /**
     * Sets the role name assigned to the logged-in user.
     *
     * @param roleName the role name to set
     */
    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }

    /**
     * Returns the {@link UserDTO} associated with the logged-in user.
     *
     * @return the {@link UserDTO} instance, or {@code null} if not set
     */
    public UserDTO getUserDTO() {
        return userDTO;
    }

    /**
     * Sets the {@link UserDTO} associated with the logged-in user.
     *
     * @param userDTO the {@link UserDTO} to set
     */
    public void setUserDTO(UserDTO userDTO) {
        this.userDTO = userDTO;
    }

    /**
     * Constructs a {@code UserContext} from the given {@link UserDTO}.
     * <p>
     * Initializes all context fields (userId, loginId, name, roleId, roleName)
     * from the corresponding fields of the provided DTO.
     * </p>
     *
     * @param dto the {@link UserDTO} containing the authenticated user's details
     */
    public UserContext(UserDTO dto) {
        this.userDTO = dto;
        this.userId = dto.getId();
        this.loginId = dto.getLogin();
        this.name = dto.getName();
        this.roleId = dto.getRoleId();
        this.roleName = dto.getRoleName();
    }
}