package com.rays.service;

import com.rays.common.BaseServiceImpl;
import com.rays.common.BaseServiceInt;
import com.rays.common.UserContext;
import com.rays.dao.UserDAOInt;
import com.rays.dto.UserDTO;

/**
 * Service interface for managing user operations.
 * This interface extends BaseServiceInt to define the contract for
 * user-related business logic operations including authentication,
 * registration, and password management.
 * 
 * @author Krati Trivedi
 */
public interface UserServiceInt extends BaseServiceInt<UserDTO> {
	
	/**
	 * Finds a user by their login ID.
	 * 
	 * @param login the login ID to search for
	 * @param userContext the user context for the operation
	 * @return the UserDTO if found, otherwise null
	 */
	public UserDTO findByLogin(String login, UserContext userContext);

	/**
	 * Registers a new user.
	 * 
	 * @param dto the UserDTO containing user registration details
	 * @param userContext the user context for the operation
	 * @return the registered UserDTO with the generated ID
	 */
	public UserDTO register(UserDTO dto, UserContext userContext);

	/**
	 * Authenticates a user with login ID and password.
	 * 
	 * @param login the login ID of the user
	 * @param password the password to validate
	 * @return the authenticated UserDTO if credentials are valid, otherwise null
	 */
	public UserDTO authenticate(String login, String password);

	/**
	 * Handles forgot password functionality.
	 * Retrieves user details by login ID for password recovery.
	 * 
	 * @param login the login ID of the user
	 * @return the UserDTO if found, otherwise null
	 */
	public UserDTO forgotPassword(String login);

	/**
	 * Changes the password for a user after validating the old password.
	 * 
	 * @param login the login ID of the user
	 * @param oldPassword the current password to validate
	 * @param newPassword the new password to set
	 * @param userContext the user context for the operation
	 * @return the updated UserDTO if password change is successful, otherwise null
	 */
	public UserDTO changePassword(String login, String oldPassword, String newPassword, UserContext userContext);

}