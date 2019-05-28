package com.proftaak.driverapplication.converters;

import com.proftaak.driverapplication.models.LoginAttempt;
import com.proftaak.driverapplication.service.DriverApplicationService;

import javax.ejb.Stateless;
import javax.inject.Inject;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.TimeZone;

@Stateless
public class LoginAttemptConverter
{
	@Inject
	private DriverApplicationService driverApplicationService;

	public LoginAttempt toEntity(com.proftaak.driverapplication.shared.LoginAttempt loginAttempt) {
		return driverApplicationService.getUserStatisticById(loginAttempt.getId());
	}

	public com.proftaak.driverapplication.shared.LoginAttempt toShared(LoginAttempt loginAttempt) {
		TimeZone tz = TimeZone.getTimeZone("UTC");
		DateFormat df = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm'Z'");
		df.setTimeZone(tz);
		String isoString = df.format(loginAttempt.getDate());
		return new com.proftaak.driverapplication.shared.LoginAttempt(loginAttempt.getId(), loginAttempt.getUserId(), isoString, loginAttempt.isSuccess());
	}

	public List<com.proftaak.driverapplication.shared.LoginAttempt> toShared(List<LoginAttempt> loginAttempts) {
		List<com.proftaak.driverapplication.shared.LoginAttempt> shared = new ArrayList<>();
		for (LoginAttempt loginAttempt : loginAttempts)
		{
			shared.add(toShared(loginAttempt));
		}
		return shared;
	}
}
