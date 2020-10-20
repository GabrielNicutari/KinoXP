package com.example.demo.model;

import org.junit.jupiter.api.Test;

import java.sql.Date;
import java.sql.Time;
import java.time.Instant;

import static org.junit.jupiter.api.Assertions.*;

class MovieTest {
    Date test_date= new Date(2000,2,3);
    Time test_time= new Time(2000);

    Movie test_movie= new Movie(1234,"TEST MOVIE",18,600,"TEST GENRE","TEST DESC","MOVIE NAME","TEST TYPE",test_date,test_date,test_time);
    @Test
    void getId() {
        assertEquals(0,test_movie.getId());
    }

    @Test
    void setId() {
        test_movie.setId(2345);
        assertEquals(2345,test_movie.getId());
    }

    @Test
    void getTitle() {
        assertEquals("TEST MOVIE",test_movie.getTitle());
    }

    @Test
    void setTitle() {
        test_movie.setTitle("TEST SET TITLE");
        assertEquals("TEST SET TITLE",test_movie.getTitle());

    }

    @Test
    void getAgeRequirement() {
        assertEquals(18,test_movie.getAgeRequirement());

    }

    @Test
    void setAgeRequirement() {
        test_movie.setAgeRequirement(17);
        assertEquals(17,test_movie.getAgeRequirement());

    }

    @Test
    void getDuration() {

        assertEquals(600,test_movie.getDuration());

    }

    @Test
    void setDuration() {
        test_movie.setDuration(700);
        assertEquals(700,test_movie.getDuration());

    }

    @Test
    void getGenre() {
        assertEquals("TEST GENRE",test_movie.getGenre());

    }

    @Test
    void setGenre() {
        test_movie.setGenre("TEST SET GENRE");
        assertEquals("TEST SET GENRE",test_movie.getGenre());

    }

    @Test
    void getDescription() {
        assertEquals("TEST DESC",test_movie.getDescription());

    }

    @Test
    void setDescription() {
        test_movie.setDescription("SET TEST DESC");
        assertEquals("SET TEST DESC",test_movie.getDescription());

    }

    @Test
    void testEquals() {
        Movie equals_test=new Movie(1234,"TEST MOVIE",18,600,"TEST GENRE","TEST DESC","MOVIE NAME","TEST TYPE",test_date,test_date,test_time);
        assert(test_movie.equals(equals_test));

    }

    @Test
    void testHashCode() {
        assertEquals(-1116349709,test_movie.hashCode());

    }

    @Test
    void testToString() {
        assertEquals("Movie{id=0, title='TEST MOVIE', ageRequirement=18, duration=600, genre='TEST GENRE', description='TEST DESC', actor='null'}",test_movie.toString());

    }
}