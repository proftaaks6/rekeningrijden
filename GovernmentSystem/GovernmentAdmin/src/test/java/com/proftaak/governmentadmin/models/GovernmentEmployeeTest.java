package com.proftaak.governmentadmin.models;

import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

public class GovernmentEmployeeTest {

    GovernmentEmployee employee;

    @Before
    public void setUp() throws Exception {
        Role role = Role.NOTIMPLEMENTEDYET;
        List<Role> roles = new ArrayList<>();
        roles.add(role);
        employee = new GovernmentEmployee("testName", "testPassword", roles);
    }

    @Test
    public void getId() {
        assertEquals(0, employee.getId());
    }

    @Test
    public void getUsername() {
        assertEquals("testName", employee.getUsername());
    }

    @Test
    public void getPassword() {
        assertEquals("testPassword", employee.getPassword());
    }

    @Test
    public void getRoles() {
        assertEquals(Role.NOTIMPLEMENTEDYET, employee.getRoles().get(0));
    }

    @Test
    public void setPassword() {
        assertEquals("testPassword", employee.getPassword());
        employee.setPassword("test");
        assertEquals("test", employee.getPassword());
    }
}