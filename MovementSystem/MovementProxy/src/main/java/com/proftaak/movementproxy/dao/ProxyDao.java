package com.proftaak.movementproxy.dao;

import com.proftaak.movementproxy.models.InvalidData;

public interface ProxyDao {
    boolean addInvalidData(InvalidData data);
    boolean removeInvalidData(InvalidData data);
    InvalidData getInvalidData(int id);
}
