package com.proftaak.movementproxy.models;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class InvalidDataTest {

    InvalidData invalidData;

    @Before
    public void setup(){
        invalidData = new InvalidData();
        invalidData.setData("test");
    }

    @Test
    public void getData() {
        assertEquals("test", invalidData.getData());
    }

    @Test
    public void setData() {
        assertEquals("test", invalidData.getData());
        invalidData.setData("test2");
        assertEquals("test2", invalidData.getData());
    }

    @Test
    public void getId() {
        assertEquals(0, invalidData.getId());
    }
}