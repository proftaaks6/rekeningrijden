package com.proftaak.driverapplication.security;

import com.proftaak.usersystem.shared.ClientUser;
import java.security.Principal;
import javax.ws.rs.core.SecurityContext;

public class RestSecurityContext implements SecurityContext
{
	private static final String AUTHENTICATION_SCHEME = "Bearer";

	private ClientUser user;

	public RestSecurityContext(ClientUser user) {
		this.user = user;
	}

	public ClientUser getUser() {
		return this.user;
	}

	@Override
	public Principal getUserPrincipal() {
		return () -> user.getEmail();
	}

	@Override
	public boolean isUserInRole(String s) {
		return true;
	}

	@Override
	public boolean isSecure() {
		return true;
	}

	@Override
	public String getAuthenticationScheme() {
		return AUTHENTICATION_SCHEME;
	}
}
