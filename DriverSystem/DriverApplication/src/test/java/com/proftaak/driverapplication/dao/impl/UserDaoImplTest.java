package com.proftaak.driverapplication.dao.impl;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import static org.junit.jupiter.api.Assertions.*;

class UserDaoImplTest {

    private UserDaoImpl dao = new UserDaoImpl();

    @BeforeEach
    void setUp() {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("h2");
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
        dao.setEm(em);

        dao.saveNewUser(1,"test", "test");
    }

    @AfterEach
    void tearDown() {
        dao.getEm().getTransaction().rollback();
    }

    @Test
    void saveNewUser() {
        assertNotEquals(null, dao.saveNewUser(2,"username", "password"));
    }

    @Test
    void getDriverUserById() {
        assertNotEquals(null, dao.getDriverUserById(1));
    }

    @Test
    void getAll() {
        assertNotEquals(null, dao.getAll());
    }

    @Test
    void verifyUser() {
        assertNotEquals(null, dao.verifyUser("test", "test"));
    }
}