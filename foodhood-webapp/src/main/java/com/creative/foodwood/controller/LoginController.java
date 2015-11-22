package com.creative.foodwood.controller;

import java.util.Random;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.creative.foodwood.dao.LoginDao;
import com.creative.foodwood.entity.UserEntity;
import com.creative.foodwood.model.User;

@RestController
@RequestMapping(value = "user")
public class LoginController {
	@Autowired
	private LoginDao loginDao;
	private static final String ACCOUNT_STATUS_ACTIVATED = "ACTIVATED";
	private static final String ACCOUNT_STATUS_IN_ACTIVE = "INACTIVE";

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

	@RequestMapping(method = RequestMethod.POST, value = "/signup")
	public boolean createUser(@RequestBody User newUser) {
		UserEntity existingUser = loginDao.fetchByUsername(newUser.getUsername());
		if (existingUser != null) {
			return false;
		}
		UserEntity newUserEntity = new UserEntity();
		BeanUtils.copyProperties(newUser, newUserEntity);
		newUserEntity.setActivationStatus(ACCOUNT_STATUS_IN_ACTIVE);
		loginDao.save(newUserEntity);
		return true;
	}

	private String createActivationCodeForChef() {
		Random rand = new Random();
		StringBuilder sb = new StringBuilder();
		return "";
	}

	@RequestMapping(method = RequestMethod.POST, value = "validateChef")
	public boolean validateChef(String chefUserName, String chefCode) {
		UserEntity existingChef = loginDao.fetchByUsername(chefUserName);
		if (existingChef != null && chefCode.equals(existingChef.getActivationCode())) {
			existingChef.setActivationStatus(ACCOUNT_STATUS_ACTIVATED);
			loginDao.save(existingChef);
			return true;
		}
		return false;
	}

	public String getActivationCode(String chefUserName) {
		UserEntity existingChef = loginDao.fetchByUsername(chefUserName);
		if (existingChef != null) {
			existingChef.getActivationCode();
		}
		return null;
	}

}
