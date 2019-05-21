package com.proftaak.driverapplication.service;

import com.proftaak.driverapplication.dao.LoginAttemptDao;
import com.proftaak.driverapplication.dao.UserDao;
import com.proftaak.driverapplication.models.DriverUser;
import com.proftaak.driverapplication.models.LoginAttempt;
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

	public DriverUser saveNewUser(long id) {
		// Default password for now, in future a new user receives an email to reset his/her password.
		return userDao.saveNewUser(id, "WELKOM123");
	}

	public List<LoginAttempt> getUserStatistics(long userId) {
		return loginAttemptDao.getByUserId(userId);
	}

	public LoginAttempt getUserStatisticById(long id) {
		return loginAttemptDao.getById(id);
	}

	public LoginAttempt addLoginAttempt(LoginAttempt loginAttempt) {
		return loginAttemptDao.add(loginAttempt);
	}
}
