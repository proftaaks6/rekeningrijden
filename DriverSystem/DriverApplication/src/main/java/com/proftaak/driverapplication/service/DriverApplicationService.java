package com.proftaak.driverapplication.service;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import com.proftaak.driverapplication.dao.LoginAttemptDao;
import com.proftaak.driverapplication.dao.UserDao;
import com.proftaak.driverapplication.models.DriverUser;
import com.proftaak.driverapplication.models.LoginAttempt;
import com.proftaak.driverapplication.utility.AuthenticationUtils;
import com.proftaak.driverapplication.utility.RestCommuncationHelper;
import com.proftaak.invoicesystem.shared.Invoice;
import com.proftaak.usersystem.shared.ClientUser;

import com.proftaak.usersystem.shared.SimpleUserVehicle;
import com.proftaak.usersystem.shared.UserVehicle;
import java.util.Arrays;
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

	private String env = System.getenv("environment");

	public DriverUser getUserById(long id) {
		return userDao.getDriverUserById(id);
	}

	public ClientUser getClientUserByName(String username) {
		try
		{
			if(env != null && env.equals("production")) {
				return new ObjectMapper().readValue(com.proftaak.resthelpers.RestCommuncationHelper.getResponseString("http://usersystem:8080/deploy/v1/usersystem/userId/" + username, "GET"), ClientUser.class);
			} else {
				return new ObjectMapper().readValue(com.proftaak.resthelpers.RestCommuncationHelper.getResponseString("http://localhost:8080/UserSystem/v1/usersystem/userId/" + username, "GET"), ClientUser.class);
			}
		} catch (IOException e)
		{
			return null;
		}
	}

	public DriverUser saveNewUser(long userId, String username, String password) throws UnsupportedEncodingException, NoSuchAlgorithmException {
		return userDao.saveNewUser(userId, username, AuthenticationUtils.encodeSHA256(password));
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

	public Invoice[] getUserInvoices(String username) throws IOException {
		ClientUser user = this.getClientUserByName(username);
		String unparsedVehicleIds = getUnparsedVehicleIds(user);

		if (!unparsedVehicleIds.equals("")) {
			if(env != null && env.equals("production")) {
				return new Gson().fromJson(RestCommuncationHelper.getRequest("http://invoicesystem:8080/deploy/v1/invoicesystem/user/"+user.getId()+"/vehicles/" + unparsedVehicleIds)
						, Invoice[].class);
			} else {
				return new Gson().fromJson(RestCommuncationHelper.getRequest("http://localhost:8080/InvoiceSystem/v1/invoicesystem/user/"+user.getId()+"/vehicles/" + unparsedVehicleIds)
						, Invoice[].class);
			}
		} else {
			return new Invoice[0];
		}
	}

	public Invoice getInvoiceById(long invoiceId, String username) throws IOException {
		List<Invoice> userInvoices = Arrays.asList(this.getUserInvoices(username));
		return userInvoices.stream().filter(x -> x.getId() == invoiceId).findFirst().orElseGet(null);
	}

	private String getUnparsedVehicleIds(ClientUser user) {

		StringBuilder sb = new StringBuilder();

		for (String chassis : user.getOwnedVehiclesChassis()) {
			sb.append(chassis + ",");
		}
		sb.deleteCharAt(sb.lastIndexOf(","));

		return sb.toString();
	}

	public SimpleUserVehicle[] getUserVehicles(String username) throws IOException {
		ClientUser user = this.getClientUserByName(username);

		Date from = new Date(0);
		Date to = new Date();
		if(env != null && env.equals("production")) {
			return new Gson().fromJson(RestCommuncationHelper.getRequest("http://usersystem:8080/deploy/v1/usersystem/users/"+user.getId()+"/vehicles/from/"+from.getTime()+"/to/"+to.getTime())
					, SimpleUserVehicle[].class);
		} else {
			return new Gson().fromJson(RestCommuncationHelper.getRequest("http://localhost:8080/UserSystem/v1/usersystem/users/"+user.getId()+"/vehicles/from/"+from.getTime()+"/to/"+to.getTime())
					, SimpleUserVehicle[].class);
		}
	}

	public boolean payInvoice(long invoiceId, String username) throws IOException {
		Invoice invoice = getInvoiceById(invoiceId, username);
		if (invoice == null) return false;
		try
		{
			if (env != null && env.equals("production"))
			{
				RestCommuncationHelper.getRequest("http://invoicesystem:8080/deploy/v1/invoicesystem/markAsPaid/" + invoiceId);
			} else
			{
				RestCommuncationHelper.getRequest("http://localhost:8080/InvoiceSystem/v1/invoicesystem/markAsPaid/" + invoiceId);
			}
		} catch (Exception e) {
			return false;
		}

		return true;
	}
}
