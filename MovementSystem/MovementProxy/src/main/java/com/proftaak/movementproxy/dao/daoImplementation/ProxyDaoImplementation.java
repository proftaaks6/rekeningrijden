package com.proftaak.movementproxy.dao.daoImplementation;

import com.proftaak.movementproxy.dao.ProxyDao;
import com.proftaak.movementproxy.models.InvalidData;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Stateless
public class ProxyDaoImplementation implements ProxyDao {

    @PersistenceContext(unitName = "default")
    private EntityManager em;

    @Override
    public boolean addInvalidData(InvalidData data) {
        try{
            em.persist(data);
        }catch (Exception e){
            return false;
        }
        return true;
    }

    @Override
    public boolean removeInvalidData(InvalidData data) {
        try{
            em.remove(data);
        }catch (Exception e){
            return false;
        }
        return true;
    }

    @Override
    public InvalidData getInvalidData(int id) {
        InvalidData data = null;
        try{
            data = em.createNamedQuery("InvalidData.getInvalidData", InvalidData.class).setParameter("id", (long)id).getSingleResult();
        }catch (Exception e){
            return null;
        }
        return data;
    }

    @Override
    public void setEm(EntityManager em) {
        this.em = em;
    }

    @Override
    public EntityManager getEm() {
        return this.em;
    }

    @Override
    public List<InvalidData> getAll() {
        List<InvalidData> data = null;
        try{
            data = em.createNamedQuery("InvalidData.getAll", InvalidData.class).getResultList();
        }catch (Exception e){
            return null;
        }
        return data;
    }
}
