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
    private String title; //use for showing tickets on the website
    private String dateTime; //use for showing tickets on the website

    public Ticket () {
    }

    public Ticket (int id, String email, int seat, int showtimeId)
    {
        this.id = id;
        this.email = email;
        this.seat = seat;
        this.showtimeId = showtimeId;
    }

    public Ticket (int id, String email, int seat, int showtimeId, String title, String dateTime)
    {
        this.id = id;
        this.email = email;
        this.seat = seat;
        this.showtimeId = showtimeId;
        this.title = title;
        this.dateTime = dateTime;
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

    public String getTitle ()
    {
        return title;
    }

    public void setTitle (String title)
    {
        this.title = title;
    }

    public String getDateTime ()
    {
        return dateTime;
    }

    public void setDateTime (String dateTime)
    {
        this.dateTime = dateTime;
    }

    @Override
    public String toString ()
    {
        return "Ticket{" + "id=" + id + ", email='" + email + '\'' + ", seat=" + seat + ", showtimeId=" + showtimeId + '}';
    }
}
