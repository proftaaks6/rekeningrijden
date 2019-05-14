package com.proftaak.driverapplication.service;

import com.proftaak.driverapplication.dao.UserDao;
import com.proftaak.driverapplication.models.DriverUser;
import javax.ejb.Stateless;
import javax.inject.Inject;

@Stateless
public class DriverApplicationService
{
	@Inject
	private UserDao userDao;

	public DriverUser getById(long id) {
		return userDao.getDriverUserById(id);
	}

	public DriverUser saveNewUser(long id) {
		// Default password for now, in future a new user receives an email to reset his/her password.
		return userDao.saveNewUser(id, "WELKOM123");
	}
}
