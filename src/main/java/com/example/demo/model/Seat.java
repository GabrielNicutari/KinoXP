package com.example.demo.model;

public class Seat {
    private String status;
    private int number;

    public Seat (String status, int number)
    {
        this.status = status;
        this.number = number;
    }

    public Seat ()
    {
    }

    public String getStatus ()
    {
        return status;
    }

    public void setStatus (String status)
    {
        this.status = status;
    }

    public int getNumber ()
    {
        return number;
    }

    public void setNumber (int number)
    {
        this.number = number;
    }

    @Override
    public String toString ()
    {
        return "seat{" + "status='" + status + '\'' + ", number=" + number + '}';
    }
}
