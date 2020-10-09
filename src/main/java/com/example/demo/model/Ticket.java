package com.example.demo.model;


import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Ticket {
    @Id
    private int id;
    private String email;
    private int seat;
    private int showtimeId;

    public Ticket () {
    }

    public Ticket (int id, String email, int seat, int showtimeId)
    {
        this.id = id;
        this.email = email;
        this.seat = seat;
        this.showtimeId = showtimeId;
    }

    public int getId ()
    {
        return id;
    }

    public void setId (int id)
    {
        this.id = id;
    }

    public String getEmail ()
    {
        return email;
    }

    public void setEmail (String email)
    {
        this.email = email;
    }

    public int getSeat ()
    {
        return seat;
    }

    public void setSeat (int seat)
    {
        this.seat = seat;
    }

    public int getShowtimeId ()
    {
        return showtimeId;
    }

    public void setShowtimeId (int showtimeId)
    {
        this.showtimeId = showtimeId;
    }

    @Override
    public String toString ()
    {
        return "Ticket{" + "id=" + id + ", email='" + email + '\'' + ", seat=" + seat + ", showtimeId=" + showtimeId + '}';
    }
}
