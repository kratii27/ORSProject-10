package com.rays.exception;

import java.net.ConnectException;

public class DatabaseException extends ConnectException {
	
	public DatabaseException(String message) {
		super(message);
	}

}
