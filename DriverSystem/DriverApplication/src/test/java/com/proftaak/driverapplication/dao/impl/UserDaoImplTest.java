package com.proftaak.driverapplication.dao.impl;


import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import static org.junit.Assert.assertNotEquals;


public class UserDaoImplTest {

    private UserDaoImpl dao = new UserDaoImpl();

    @Before
    public void setUp() {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("h2");
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
        dao.setEm(em);

        dao.saveNewUser(1,"test", "test");
    }

    @After
    public void tearDown() {
        dao.getEm().getTransaction().rollback();
    }

    @Test
    public void saveNewUser() {
        assertNotEquals(null, dao.saveNewUser(2,"username", "password"));
    }

    @Test
    public void getDriverUserById() {
        assertNotEquals(null, dao.getDriverUserById(1));
    }

    @Test
    public void getAll() {
        assertNotEquals(null, dao.getAll());
    }

    @Test
    public void verifyUser() {
        assertNotEquals(null, dao.verifyUser("test", "test"));
    }
}