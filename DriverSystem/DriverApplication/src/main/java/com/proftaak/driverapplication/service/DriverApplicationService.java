package com.proftaak.driverapplication.service;

import com.proftaak.driverapplication.dao.LoginAttemptDao;
import com.proftaak.driverapplication.dao.UserDao;
import com.proftaak.driverapplication.models.DriverUser;
import com.proftaak.driverapplication.models.LoginAttempt;
import com.proftaak.driverapplication.utility.AuthenticationUtils;

import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;
import java.util.Date;
import java.util.List;
import javax.ejb.Stateless;
import javax.inject.Inject;

@Stateless
public class DriverApplicationService
{
	@Inject
	private UserDao userDao;
	@Inject
	private LoginAttemptDao loginAttemptDao;

	public DriverUser getUserById(long id) {
		return userDao.getDriverUserById(id);
	}

	public DriverUser saveNewUser(String username, String password) throws UnsupportedEncodingException, NoSuchAlgorithmException {
		return userDao.saveNewUser(username, AuthenticationUtils.encodeSHA256(password));
	}

	public List<LoginAttempt> getUserStatistics(long userId) {
		return loginAttemptDao.getByUserId(userId);
	}

	public LoginAttempt getUserStatisticById(long id) {
		return loginAttemptDao.getById(id);
	}

	public DriverUser validateUser(String username, String password) throws UnsupportedEncodingException, NoSuchAlgorithmException {
		DriverUser user = userDao.verifyUser(username, AuthenticationUtils.encodeSHA256(password));
		if (user != null) {
			addLoginAttempt(new LoginAttempt(user.getId(), new Date(), true));
			return user;
		} else {
			addLoginAttempt(new LoginAttempt(new Date(), false));
		}

		return null;
	}

	public LoginAttempt addLoginAttempt(LoginAttempt loginAttempt) {

		return loginAttemptDao.add(loginAttempt);
	}
}
