package com.proftaak.governmentadmin.dao.implementations.database;

import com.proftaak.governmentadmin.models.GovernmentEmployee;
import com.proftaak.governmentadmin.models.Role;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

public class UserDaoDatabaseImplTest {

    private UserDaoDatabaseImpl dao = new UserDaoDatabaseImpl();

    @Before
    public void setUp() throws Exception {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("h2");
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
        dao.setEm(em);

        clearDB();

        List<Role> roles = new ArrayList<>();
        roles.add(Role.NOTIMPLEMENTEDYET);
        dao.addUser(new GovernmentEmployee("testUser", "testPassword", roles));
    }

    @Test
    public void addUser() {
        assertEquals(1, dao.getAll().size());
        List<Role> roles = new ArrayList<>();
        roles.add(Role.NOTIMPLEMENTEDYET);
        dao.addUser(new GovernmentEmployee("testUser2", "testPassword2", roles));
        assertEquals(2, dao.getAll().size());
        assertEquals("testUser2", dao.findByUsername("testUser2").getUsername());
    }

    @Test
    public void removeUser() {
        assertEquals(1, dao.getAll().size());
        dao.removeUser(dao.findByUsername("testUser"));
        assertEquals(0, dao.getAll().size());
    }

    @Test
    public void updateUser() {
        assertEquals("testUser", dao.findByUsername("testUser").getUsername());
        GovernmentEmployee user = dao.findByUsername("testUser");
        user.setPassword("testPassword2");
        dao.updateUser(user);
        assertEquals("testPassword2", dao.findByUsername("testUser").getPassword());
    }

    @Test
    public void findByUsername() {
        assertEquals("testUser", dao.findByUsername("testUser").getUsername());
    }

    @Test
    public void getAll() {
        assertEquals(1, dao.getAll().size());
        List<Role> roles = new ArrayList<>();
        roles.add(Role.NOTIMPLEMENTEDYET);
        dao.addUser(new GovernmentEmployee("testUser2", "testPassword2", roles));
        assertEquals(2, dao.getAll().size());
        assertEquals("testUser2", dao.findByUsername("testUser2").getUsername());
    }

    @Test
    public void validateUser() {
        assertEquals("testUser", dao.validateUser("testUser", "testPassword").getUsername());
    }

    @After
    public void tearDown(){
        dao.getEm().getTransaction().rollback();
    }

    private void clearDB(){
        for(GovernmentEmployee user : dao.getAll()){
            dao.removeUser(user);
        }
    }
}