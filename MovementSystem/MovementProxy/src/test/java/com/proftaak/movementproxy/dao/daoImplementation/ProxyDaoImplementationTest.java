package com.proftaak.movementproxy.dao.daoImplementation;

import com.proftaak.movementproxy.dao.ProxyDao;
import com.proftaak.movementproxy.models.InvalidData;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.util.List;

import static org.junit.Assert.*;


public class ProxyDaoImplementationTest {

    private ProxyDao dao = new ProxyDaoImplementation();

    @Before
    public void setUp() throws Exception {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("h2");
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
        dao.setEm(em);

        clearDB();

        dao.addInvalidData(new InvalidData("test"));
    }

    @Test
    public void addInvalidData() {
        List<InvalidData> data2 = dao.getAll();
        assertEquals("test", dao.getInvalidData(1).getData());
        dao.addInvalidData(new InvalidData("test2"));
        assertEquals("test2", dao.getInvalidData(2).getData());
    }

    @Test
    public void removeInvalidData() {
        dao.addInvalidData(new InvalidData("test2"));
        assertEquals(2, dao.getAll().size());
        dao.removeInvalidData(dao.getInvalidData(2));
        assertEquals(1, dao.getAll().size());
    }

    @Test
    public void getInvalidData() {
        assertEquals("test", dao.getInvalidData(1).getData());
    }

    @After
    public void tearDown(){
        dao.getEm().getTransaction().rollback();
    }

    private void clearDB(){
        for(InvalidData data : dao.getAll()){
            dao.removeInvalidData(data);
        }
    }
}