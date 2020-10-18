package com.example.demo.model;

public class Test {
    private int date;
    private String time;

    public Test () {
    }

    public int getDate() {
        return date;
    }

    public void setDate(int date) {
        this.date = date;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    @Override
    public String toString() {
        return "Test{" +
                "date='" + date + '\'' +
                ", time='" + time + '\'' +
                '}';
    }
}
