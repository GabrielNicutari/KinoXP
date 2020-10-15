package com.example.demo.model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class RoomTest {

    private Room test_room=new Room(1245,324,"ROOM_TEST");
    @Test
    void getId() {
        assertEquals(1245,test_room.getId());
    }

    @Test
    void setId() {
        test_room.setId(2345);
        assertEquals(2345,test_room.getId());
    }

    @Test
    void getSeats() {
        assertEquals(324,test_room.getSeats());
    }

    @Test
    void setSeats() {
        test_room.setSeats(234);
        assertEquals(234,test_room.getSeats());
    }

    @Test
    void getName() {
        assertEquals("ROOM_TEST",test_room.getName());
    }

    @Test
    void setName() {
        test_room.setName("SET_NAME_TEST");
        assertEquals("SET_NAME_TEST",test_room.getName());
    }

    @Test
    void testEquals() {
        Room testEquals_room= new Room(1245,324,"ROOM_TEST");
        assert(test_room.equals(testEquals_room));
    }

    @Test
    void testHashCode() {
        int hash=test_room.hashCode();
        assertEquals(hash,test_room.hashCode());
    }
}