package com.creative.foodhood.controller;

import java.util.UUID;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.creative.foodhood.dao.LoginDao;
import com.creative.foodhood.email.GmailSender;
import com.creative.foodhood.email.MessageContent;
import com.creative.foodhood.entity.UserEntity;
import com.creative.foodhood.model.User;

@RestController
@RequestMapping(value = "/user")
public class LoginController {
	@Autowired
	private LoginDao loginDao;
	private static final String ACCOUNT_STATUS_ACTIVATED = "ACTIVATED";
	private static final String ACCOUNT_STATUS_IN_ACTIVE = "INACTIVE";

	@RequestMapping(method = RequestMethod.GET, value = "/healthcheck")
	public String verifyHealth() {
		return "all good bro";
	}
	
	@RequestMapping(method = RequestMethod.POST, value = "/login")
	public User loginCustomer(String username, String password) {
		UserEntity existingUser = loginDao.fetchByUsername(username);
		if (existingUser == null || !existingUser.getPassword().equals(password)) {
			return null;
		}
		existingUser.setPassword(null);
		User currentUser = new User();
		BeanUtils.copyProperties(existingUser, currentUser);
		return currentUser;

	}
	
	@RequestMapping(method = RequestMethod.POST, value = "/forgotPassword")
	public boolean forgotPassword(String email) {
		UserEntity existingUser = loginDao.fetchByUsername(email);
		if (existingUser == null) {
			return false;
		}
		String newpwd = UUID.randomUUID().toString();
		if (newpwd.length()>10) {
			newpwd = newpwd.substring(0,8);
		}
		MessageContent messageContent = new MessageContent();
		messageContent.setSubject("Password Reset" + " FoodHood");
		messageContent.setBody("Your password has been reset to "+newpwd+".Please login and change your password");
		GmailSender sender = new GmailSender("FoodHood.noreply@gmail.com", "FoodHoodRocks");
		try {
			sender.sendMail(messageContent, email);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}
		return true;
	}

	@RequestMapping(method = RequestMethod.POST, value = "/signup")
	public boolean createUser(@RequestBody User newUser) {
		UserEntity existingUser = loginDao.fetchByUsername(newUser.getUsername());
		if (existingUser != null) {
			return false;
		}
		
		UserEntity newUserEntity = new UserEntity();
		BeanUtils.copyProperties(newUser, newUserEntity);
		if("CHEF".equalsIgnoreCase(newUser.getUserrolename())){
			newUserEntity.setActivationcode(createActivationCodeForChef());
		}
		newUserEntity.setActivationStatus(ACCOUNT_STATUS_IN_ACTIVE);
		loginDao.save(newUserEntity);
		return true;
	}

	private String createActivationCodeForChef() {
		String randomId = UUID.randomUUID().toString();
		if(randomId .length() > 8){
			return randomId.substring(0, 8);
		}
		return randomId;
	}

	@RequestMapping(method = RequestMethod.POST, value = "validateChef")
	public boolean validateChef(String chefUserName, String chefCode) {
		UserEntity existingChef = loginDao.fetchByUsername(chefUserName);
		if (existingChef != null && chefCode.equals(existingChef.getActivationcode())) {
			existingChef.setActivationStatus(ACCOUNT_STATUS_ACTIVATED);
			loginDao.save(existingChef);
			return true;
		}
		return false;
	}

	public String getActivationCode(String chefUserName) {
		UserEntity existingChef = loginDao.fetchByUsername(chefUserName);
		if (existingChef != null) {
			existingChef.getActivationcode();
		}
		return null;
	}

}
