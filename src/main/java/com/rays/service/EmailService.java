package com.rays.service;

import java.util.HashMap;

import com.rays.util.EmailBuilder;
import com.rays.util.EmailMessage;
import com.rays.util.EmailUtility;

public class EmailService {

	public static void sendUserRegistrationMail(String login, String password) {
		HashMap<String, String> map = new HashMap<String, String>();
		map.put("login", login);
		map.put("password", password);

		String message = EmailBuilder.getUserRegistrationMessage(map);

		EmailMessage msg = new EmailMessage();

		msg.setTo(login);
		msg.setSubject("Registration is successful for ORSProject-10");
		msg.setMessage(message);
		msg.setMessageType(EmailMessage.HTML_MSG);

		EmailUtility.sendMail(msg);
	}

	public static void sendForgotPasswordMail(String login, String password, String firstName, String lastName) {

		HashMap<String, String> map = new HashMap<String, String>();
		map.put("login", login);
		map.put("password", password);
		map.put("firstName", firstName);
		map.put("lastName", lastName);
		String message = EmailBuilder.getForgetPasswordMessage(map);

		EmailMessage msg = new EmailMessage();
		msg.setTo(login);
		msg.setSubject("ORSProject-10 Password Reset");
		msg.setMessage(message);
		msg.setMessageType(EmailMessage.HTML_MSG);

		EmailUtility.sendMail(msg);

	}
	
	public static void sendChangePasswordMail(String login, String password, String firstName, String lastName) {
		HashMap<String, String> map = new HashMap<String, String>();
        map.put("login", login);
        map.put("password", password);
        map.put("firstName", firstName);
        map.put("lastName", lastName);

        String message = EmailBuilder.getChangePasswordMessage(map);

        EmailMessage msg = new EmailMessage();
        msg.setTo(login);
        msg.setSubject("ORSProject-10 Password has been changed Successfully.");
        msg.setMessage(message);
        msg.setMessageType(EmailMessage.HTML_MSG);

        EmailUtility.sendMail(msg);
	}

}
