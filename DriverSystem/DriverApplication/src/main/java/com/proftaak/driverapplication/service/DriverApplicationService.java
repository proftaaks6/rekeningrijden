package com.proftaak.driverapplication.service;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.proftaak.driverapplication.dao.LoginAttemptDao;
import com.proftaak.driverapplication.dao.UserDao;
import com.proftaak.driverapplication.models.DriverUser;
import com.proftaak.driverapplication.models.LoginAttempt;
import com.proftaak.driverapplication.utility.AuthenticationUtils;
import com.proftaak.driverapplication.utility.RestCommuncationHelper;
import com.proftaak.invoicesystem.shared.Invoice;
import com.proftaak.usersystem.shared.ClientUser;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.SecurityContext;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Stateless
public class DriverApplicationService
{
	@Inject
	private UserDao userDao;
	@Inject
	private LoginAttemptDao loginAttemptDao;

	@Context
	private SecurityContext securityContext;

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

	public List<Invoice> getUserInvoices() throws IOException {
		String username = securityContext.getUserPrincipal().getName();
		ClientUser user;
		String env = System.getenv("environment");
		// get car for user
		if(env != null && env.equals("production")) {
			user = new ObjectMapper().readValue(RestCommuncationHelper.getRequest("http://usersystem:8080/deploy/v1/usersystem/users/username/" + username), ClientUser.class);
		} else {
			user = new ObjectMapper().readValue(RestCommuncationHelper.getRequest("http://localhost:8080/UserSystem/v1/usersystem/username/" + username), ClientUser.class);
		}

		StringBuilder sb = new StringBuilder();

		for (String chassis : user.getOwnedVehiclesChassis()) {
			sb.append(chassis + ",");

		}

		if (sb.equals("")) {
			if(env != null && env.equals("production")) {
				return new ObjectMapper().readValue(RestCommuncationHelper.getRequest("http://invoicesystem:8080/deploy/v1/invoicesystem/vehicle/" + sb.toString())
						, new TypeReference<List<Invoice>>(){});
			} else {
				return new ObjectMapper().readValue(RestCommuncationHelper.getRequest("http://localhost:8080/InvoiceSystem/v1/invoicesystem/vehicle/" + sb.toString())
						, new TypeReference<List<Invoice>>(){});
			}
		} else {
			return new ArrayList<>();
		}


	}
}
