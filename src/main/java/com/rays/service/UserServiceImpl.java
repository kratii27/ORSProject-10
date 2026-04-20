package com.rays.service;

import java.sql.Timestamp;
import java.util.Date;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.rays.common.BaseServiceImpl;
import com.rays.common.UserContext;
import com.rays.dao.UserDAOInt;
import com.rays.dto.UserDTO;

/**
 * Service implementation class for managing user operations.
 * This class provides the business logic implementation for user-related
 * operations including authentication, registration, password management,
 * and user lookup. Extends BaseServiceImpl and implements UserServiceInt.
 * 
 * @author Krati Trivedi
 */
@Service
@Transactional
public class UserServiceImpl extends BaseServiceImpl<UserDTO, UserDAOInt> implements UserServiceInt {

	/**
	 * Finds a user by their login ID.
	 * 
	 * @param login the login ID to search for
	 * @param userContext the user context for the operation
	 * @return the UserDTO if found, otherwise null
	 */
	@Transactional(readOnly = true)
	public UserDTO findByLogin(String login, UserContext userContext) {
		return dao.findByUniqueKey("login", login, userContext);
	}

	/**
	 * Registers a new user.
	 * 
	 * @param dto the UserDTO containing user registration details
	 * @param userContext the user context for the operation
	 * @return the registered UserDTO with the generated ID
	 */
	@Override
	public UserDTO register(UserDTO dto, UserContext userContext) {
		Long id = save(dto, userContext);
		EmailService.sendUserRegistrationMail(dto.getLogin(), dto.getPassword());
		dto.setId(id);
		return dto;
	}

	/**
	 * Authenticates a user with login ID and password.
	 * If authentication is successful, updates last login timestamp and
	 * resets unsuccessful login attempts. If authentication fails,
	 * increments the unsuccessful login attempt counter.
	 * 
	 * @param login the login ID of the user
	 * @param password the password to validate
	 * @return the authenticated UserDTO if credentials are valid, otherwise null
	 */
	@Override
	public UserDTO authenticate(String login, String password) {
		UserDTO dto = findByLogin(login, null);
		if (dto != null) {
			UserContext userContext = new UserContext(dto);
			if (password.equals(dto.getPassword())) {
				dto.setLastLogin(new Timestamp((new Date()).getTime()));
				dto.setUnsucessfullLoginAttempt(0);
				update(dto, userContext);
				return dto;
			} else {
				dto.setUnsucessfullLoginAttempt(1 + dto.getUnsucessfullLoginAttempt());
				update(dto, userContext);
			}
		}
		return null;
	}

	/**
	 * Handles forgot password functionality.
	 * Retrieves user details by login ID for password recovery.
	 * 
	 * @param login the login ID of the user
	 * @return the UserDTO if found, otherwise null
	 */
	@Override
	public UserDTO forgotPassword(String login) {
		UserDTO dto = findByLogin(login, null);
		if (dto == null) {
			return null;
		}
		EmailService.sendForgotPasswordMail(dto.getLogin(), dto.getPassword(), dto.getFirstName(), dto.getLastName());
		return dto;
	}

	/**
	 * Changes the password for a user after validating the old password.
	 * 
	 * @param login the login ID of the user
	 * @param oldPassword the current password to validate
	 * @param newPassword the new password to set
	 * @param userContext the user context for the operation
	 * @return the updated UserDTO if password change is successful, otherwise null
	 */	
	@Override
	public UserDTO changePassword(String login, String oldPassword, String newPassword, UserContext userContext) {
		UserDTO dto = findByLogin(login, userContext);
		if (dto != null && oldPassword.equals(dto.getPassword())) {
			dto.setPassword(newPassword);
			update(dto, userContext);
			EmailService.sendChangePasswordMail(dto.getLogin(), dto.getPassword(), dto.getFirstName(), dto.getLastName());
			return dto;
		} else {
			return null;
		}
	}

}