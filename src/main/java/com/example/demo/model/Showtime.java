package com.example.demo.model;


import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.sql.Time;
import java.time.LocalDateTime;
import java.util.Objects;
import java.time.*;

@Entity
public class Showtime {
    @Id
    private int id;

    private int movieId;
    private int roomId;
    private LocalDateTime dateTime;

    public Showtime() {
    }
    public Showtime(int id, int movieId, int roomId, LocalDateTime dateTime, int timeTableId) {
        this.id = id;
        this.movieId = movieId;
        this.roomId = roomId;
        this.dateTime = dateTime;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getMovieId() {
        return movieId;
    }

    public void setMovieId(int movieId) {
        this.movieId = movieId;
    }

    public int getRoomId() {
        return roomId;
    }

    public void setRoomId(int roomId) {
        this.roomId = roomId;
    }

    public LocalDateTime getDateTime() {
        return dateTime;
    }

    public void setDateTime(LocalDateTime dateTime) {
        this.dateTime = dateTime;
    }

}
