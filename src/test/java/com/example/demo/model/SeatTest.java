package com.example.demo.model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class SeatTest {

    private Seat test_seat=new Seat("SEAT_TEST",123);
    @Test
    void getStatus() {
        assertEquals("SEAT_TEST",test_seat.getStatus());
    }

    @Test
    void setStatus() {
        test_seat.setStatus("TEST_SET_STATUS");
        assertEquals("TEST_SET_STATUS",test_seat.getStatus());
    }

    @Test
    void getNumber() {
        assertEquals(123,test_seat.getNumber());
    }

    @Test
    void setNumber() {
        test_seat.setNumber(234);
        assertEquals(234,test_seat.getNumber());
    }

    @Test
    void testToString() {
        assertEquals("seat{status='SEAT_TEST', number="+123+"}",test_seat.toString());
    }
}