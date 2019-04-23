package com.proftaak.usersystem.shared;

import java.util.List;

public class ClientUser
{
	private long id;
	private String name;
	private String password;
	private List<Integer> ownedCarIds;
	private String email;

	public ClientUser() {
	}

	public ClientUser(long id, String name, String password, List<Integer> ownedCarIds, String email) {
		this.id = id;
		this.name = name;
		this.password = password;
		this.ownedCarIds = ownedCarIds;
		this.email = email;
	}

	public long getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public String getPassword() {
		return password;
	}

	public List<Integer> getOwnedCarIds() {
		return ownedCarIds;
	}

	public String getEmail() {
		return email;
	}
}
