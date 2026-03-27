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

@RestController
@RequestMapping(value = "Auth")
public class LoginCtl extends BaseCtl<UserDTO, UserServiceInt, UserForm> {

	@Autowired
	private JWTUtil jwtUtil;

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

	@GetMapping("logout")
	public ORSResponse logout(HttpSession session) throws Exception {

		ORSResponse res = new ORSResponse();

		session.invalidate();

		res.addMessage("Logout successfully..!!");

		return res;
	}

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