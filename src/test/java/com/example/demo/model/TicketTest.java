package com.example.demo.model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class TicketTest {

    private Ticket test_ticket=new Ticket(1234,"test@gmail.com",234,345,"TEST_MOVIE","14-05-2020");
    @Test
    void getId() {
        assertEquals(1234,test_ticket.getId());
    }

    @Test
    void setId() {
        test_ticket.setId(567);
        assertEquals(567,test_ticket.getId());
    }

    @Test
    void getEmail() {

        assertEquals("test@gmail.com",test_ticket.getEmail());
    }

    @Test
    void setEmail() {
        test_ticket.setEmail("test2@gmail.com");
        assertEquals("test2@gmail.com",test_ticket.getEmail());
    }

    @Test
    void getSeat() {
        assertEquals(234,test_ticket.getSeat());
    }

    @Test
    void setSeat() {
        test_ticket.setSeat(567);
        assertEquals(567,test_ticket.getSeat());
    }

    @Test
    void getShowtimeId() {
        assertEquals(345,test_ticket.getShowtimeId());
    }

    @Test
    void setShowtimeId() {
        test_ticket.setShowtimeId(587);
        assertEquals(587,test_ticket.getShowtimeId());
    }

    @Test
    void getTitle() {
        assertEquals("TEST_MOVIE",test_ticket.getTitle());
    }

    @Test
    void setTitle() {
        test_ticket.setTitle("TEST_SET_TITLE");
        assertEquals("TEST_SET_TITLE",test_ticket.getTitle());
    }

    @Test
    void getDateTime() {
        assertEquals("14-05-2020",test_ticket.getDateTime());
    }

    @Test
    void setDateTime() {
        test_ticket.setDateTime("23-08-2012");
        assertEquals("23-08-2012",test_ticket.getDateTime());
    }

    @Test
    void testToString() {
        assertEquals("Ticket{id=1234, email='test@gmail.com', seat=234, showtimeId=345}",test_ticket.toString());
    }
}