package com.rays.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.rays.dto.UserDTO;

/**
 * Service class for loading user details during JWT authentication.
 * This class implements Spring Security's UserDetailsService to load
 * user-specific data and provide password encoding functionality.
 * 
 * @author Krati Trivedi
 */
@Service
public class JWTUserDetailsService implements UserDetailsService {

	/**
	 * Creates and returns a PasswordEncoder bean for password encryption.
	 * Uses BCryptPasswordEncoder as the encoding strategy.
	 * 
	 * @return the PasswordEncoder instance
	 */
	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

	@Autowired
	UserServiceInt userService;

	/**
	 * Loads user details by username (login) for authentication.
	 * Retrieves user information from the database and builds a Spring Security
	 * UserDetails object with the user's credentials and role.
	 * 
	 * @param username the login ID of the user
	 * @return the UserDetails object containing user authentication information
	 * @throws UsernameNotFoundException if no user is found with the given username
	 */
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

		UserDTO user = userService.findByLogin(username, null);

		if (user == null) {
			System.out.println("user found nuulllll");
			throw new UsernameNotFoundException("User not found with username : " + username);
		}

		return User.builder().username(user.getLogin()).password(passwordEncoder().encode(user.getPassword()))
				.roles("USER").build();
	}
}