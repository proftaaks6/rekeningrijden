package com.proftaak.usersystem.shared;

import java.util.List;

public class ClientUser
{
	private long id;
	private String name;
	private String address;
	private String residence;
	private List<Integer> ownedVehicleIds;
	private String email;

	public ClientUser() {
	}

	public ClientUser(long id, String name, String address, String residence, List<Integer> ownedCarIds, String email) {
		this.id = id;
		this.name = name;
		this.address = address;
		this.residence = residence;
		this.ownedVehicleIds = ownedCarIds;
		this.email = email;
	}

	public long getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public List<Integer> getOwnedVehicleIds() {
		return ownedVehicleIds;
	}

	public String getEmail() {
		return email;
	}

	public String getAddress() {
		return address;
	}

	public String getResidence() {
		return residence;
	}
}
