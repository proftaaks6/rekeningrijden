package com.proftaak.driverapplication.dao.impl;

import com.proftaak.driverapplication.models.LoginAttempt;
import org.junit.After;
import org.junit.Before;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import java.util.Date;

import static org.junit.jupiter.api.Assertions.*;

class LoginAttemptDaoImplTest {

    private LoginAttemptDaoImpl dao = new LoginAttemptDaoImpl();

    @Before
    void setUp() {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("h2");
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();

        dao.setEm(em);


    }

    @After
    public void tearDown() {
        dao.getEm().getTransaction().rollback();
    }

    @Test
    void getById() {
        assertNotEquals(null, dao.getById(1));
    }

    @Test
    void getByUserId() {
        assertNotEquals(null, dao.getByUserId(1));
    }

    @Test
    void getAll() {
        assertNotEquals(null, dao.getAll());
    }

    @Test
    void add() {
        assertNotEquals(null, dao.add(new LoginAttempt(1, new Date(), true)));
    }
}