package com.proftaak.movementproxy.dao;

import com.proftaak.movementproxy.models.InvalidData;

import javax.persistence.EntityManager;
import java.util.List;

public interface ProxyDao {
    boolean addInvalidData(InvalidData data);
    boolean removeInvalidData(InvalidData data);
    InvalidData getInvalidData(int id);
    void setEm(EntityManager em);
    EntityManager getEm();
    List<InvalidData> getAll();
}
