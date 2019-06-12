package com.proftaak.usersystem.dao.impl;

import com.proftaak.usersystem.dao.UserVehicleDao;
import com.proftaak.usersystem.models.UserVehicle;
import com.proftaak.usersystem.models.Vehicle;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Stateless
public class UserVehicleDaoImpl implements UserVehicleDao
{
	@PersistenceContext
	private EntityManager em;

	@Override
	public UserVehicle getActiveForVehicle(String chassis)
	{
		try {
			return em.createNamedQuery("UserVehicle.getActiveForVehicle", UserVehicle.class).setParameter("chassis", chassis).getSingleResult();
		}catch (Exception e){
			return null;
		}
	}

	@Override
	public UserVehicle editUserVehicle(UserVehicle userVehicle)
	{
		try {
			return em.merge(userVehicle);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
}
