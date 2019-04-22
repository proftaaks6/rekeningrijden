package com.proftaak.usersystem.converters;

import com.proftaak.usersystem.models.ClientUser;
import javax.ejb.Stateless;

@Stateless
public class ClientUserConverter
{
	public ClientUser toEntity(com.proftaak.usersystem.shared.ClientUser user) {
		return new ClientUser(user.getId(), user.getName(), user.getPassword(), user.getOwnedCarIds(), user.getEmail());
	}

	public com.proftaak.usersystem.shared.ClientUser toShared(ClientUser user) {
		return new com.proftaak.usersystem.shared.ClientUser(user.getId(), user.getName(), user.getPassword(), user.getOwnedCarIds(), user.getEmail());
	}
}
