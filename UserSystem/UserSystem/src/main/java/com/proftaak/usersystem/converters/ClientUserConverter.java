package com.proftaak.usersystem.converters;

import com.proftaak.usersystem.models.ClientUser;
import com.proftaak.usersystem.service.UserService;

import javax.ejb.Stateless;
import javax.inject.Inject;

@Stateless
public class ClientUserConverter
{
	@Inject
	private UserService userService;

	public ClientUser toEntity(com.proftaak.usersystem.shared.ClientUser user) {
		return userService.getClientUserByName(user.getName());
	}

	public com.proftaak.usersystem.shared.ClientUser toShared(ClientUser user) {
		return new com.proftaak.usersystem.shared.ClientUser(user.getId(), user.getName(), user.getAddress(), user.getResidence(), user.getOwnedCarIdList(), user.getEmail());
	}
}
