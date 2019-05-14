package com.proftaak.driverapplication.dao.impl;

import com.proftaak.driverapplication.dao.LoginAttemptDao;
import com.proftaak.driverapplication.models.LoginAttempt;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

public class LoginAttemptDaoImpl implements LoginAttemptDao
{
	@PersistenceContext
	private EntityManager em;

	@Override
	public LoginAttempt getById(long id)
	{
		try {
			return em.createNamedQuery("LoginAttempt.getById", LoginAttempt.class).setParameter("id", id).getSingleResult();
		}catch (Exception e){
			return null;
		}
	}

	@Override
	public List<LoginAttempt> getByUserId(long userId)
	{
		try {
			return em.createNamedQuery("LoginAttempt.getUserById", LoginAttempt.class).setParameter("userId", userId).getResultList();
		}catch (Exception e){
			return null;
		}
	}

	@Override
	public List<LoginAttempt> getAll()
	{
		try {
			return em.createNamedQuery("LoginAttempt.getAll", LoginAttempt.class).getResultList();
		} catch (Exception e) {
			return null;
		}
	}
}
