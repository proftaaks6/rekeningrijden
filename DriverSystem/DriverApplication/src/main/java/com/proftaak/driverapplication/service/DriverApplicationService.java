package com.proftaak.driverapplication.service;

import com.proftaak.driverapplication.dao.LoginAttemptDao;
import com.proftaak.driverapplication.dao.UserDao;
import com.proftaak.driverapplication.models.DriverUser;
import com.proftaak.driverapplication.models.LoginAttempt;
import com.proftaak.driverapplication.utility.AuthenticationUtils;

import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;
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

	public DriverUser saveNewUser(String username, String password) {
		return userDao.saveNewUser(username, password);
	}

	public List<LoginAttempt> getUserStatistics(long userId) {
		return loginAttemptDao.getByUserId(userId);
	}

	public LoginAttempt getUserStatisticById(long id) {
		return loginAttemptDao.getById(id);
	}

	public DriverUser validateUser(String username, String password) throws UnsupportedEncodingException, NoSuchAlgorithmException {
		return userDao.verifyUser(username, AuthenticationUtils.encodeSHA256(password));
	}

	public LoginAttempt addLoginAttempt(LoginAttempt loginAttempt) {
		return loginAttemptDao.add(loginAttempt);
	}
}
