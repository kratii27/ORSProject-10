package com.rays.service;

import com.rays.common.BaseServiceImpl;
import com.rays.common.BaseServiceInt;
import com.rays.common.UserContext;
import com.rays.dao.UserDAOInt;
import com.rays.dto.UserDTO;

public interface UserServiceInt extends BaseServiceInt<UserDTO> {
	
	public UserDTO findByLogin(String login, UserContext userContext);

	public UserDTO register(UserDTO dto, UserContext userContext);

	public UserDTO authenticate(String login, String password);

	public UserDTO forgotPassword(String login);

	public UserDTO changePassword(String login, String oldPassword, String newPassword, UserContext userContext);

}
