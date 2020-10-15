package com.example.demo.model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ActorTest {

   private Actor temp_actor= new Actor(1234,"TEST_NAME");
    @Test
    void getId() {
        assertEquals(1234,temp_actor.getId());
    }

    @Test
    void setId() {
        temp_actor.setId(1456);
        assertEquals(1456,temp_actor.getId());
    }

    @Test
    void getName() {
        assertEquals("TEST_NAME",temp_actor.getName());
    }

    @Test
    void setName() {
        temp_actor.setName("SET_NAME_TEST");
        assertEquals("SET_NAME_TEST",temp_actor.getName());
    }

    @Test
    void testEquals() {
        Actor equals_test= new Actor(1234,"TEST_NAME");
        assert(temp_actor.equals(equals_test));
    }

    @Test
    void testHashCode() {
    assertEquals(1635881799,temp_actor.hashCode());
    }

    @Test
    void testToString() {
        assertEquals("Actor{id="+1234+", name='TEST_NAME'}",temp_actor.toString());
    }
}