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
    //split DateTime as Strings
    private String date;
    private String time;

    private LocalDateTime dateTime;

    public Showtime() {
    }
    public Showtime(int id, int movieId, int roomId, LocalDateTime dateTime, String date, String time) {
        this.id = id;
        this.movieId = movieId;
        this.roomId = roomId;
        this.dateTime = dateTime;
        this.date = date;
        this.time = time;
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

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }


}
