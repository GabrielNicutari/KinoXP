package com.example.demo.model;

import javax.persistence.*;
import java.sql.Date;
import java.sql.Time;
import java.time.Clock;
import java.util.Collection;
import java.util.Objects;

@Entity
public class Movie {
    @Id
    private int id;
    private String title;
    private Integer ageRequirement;
    private int duration;
    private String genre;
    private String description;
    private String image;

    //fields from actor
    private String actors; //there's a list inside

    //fields from room
//    private String type;
//
//    //fields from date
//    private java.sql.Date startDate;
//    private java.sql.Date endDate;
//
//    //fields from showtime
//    private Time startTime;

    public Movie() {}

    public Movie(int id, String title, Integer ageRequirement, int duration, String genre, String description,
                 String name, String type, Date startDate, Date endDate, Time startTime) {
        this.id = id;
        this.title = title;
        this.ageRequirement = ageRequirement;
        this.duration = duration;
        this.genre = genre;
        this.description = description;
        this.actors = actors;
//        this.type = type;
//        this.startDate = startDate;
//        this.endDate = endDate;
//        this.startTime = startTime;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Integer getAgeRequirement() {
        return ageRequirement;
    }

    public void setAgeRequirement(Integer ageRequirement) {
        this.ageRequirement = ageRequirement;
    }

    public int getDuration() {
        return duration;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getActors() {
        return actors;
    }

    public void setActors(String actors) {
        this.actors = actors;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    //    public String getType() {
//        return type;
//    }
//
//    public void setType(String type) {
//        this.type = type;
//    }
//
//    public java.sql.Date getStartDate() {
//        return startDate;
//    }
//
//    public void setStartDate(java.sql.Date startDate) {
//        this.startDate = startDate;
//    }
//
//    public java.sql.Date getEndDate() {
//        return endDate;
//    }
//
//    public void setEndDate(Date endDate) {
//        this.endDate = endDate;
//    }
//
//    public Time getStartTime() {
//        return startTime;
//    }
//
//    public void setStartTime(Time startTime) {
//        this.startTime = startTime;
//    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Movie that = (Movie) o;
        return id == that.id &&
                duration == that.duration &&
                Objects.equals(title, that.title) &&
                Objects.equals(ageRequirement, that.ageRequirement) &&
                Objects.equals(genre, that.genre) &&
                Objects.equals(description, that.description);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, title, ageRequirement, duration, genre, description);
    }

    @Override
    public String toString() {
        return "Movie{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", ageRequirement=" + ageRequirement +
                ", duration=" + duration +
                ", genre='" + genre + '\'' +
                ", description='" + description + '\'' +
                ", actor='" + actors + '\'' +
//                ", room='" + type + '\'' +
//                ", startDate=" + startDate +
//                ", endDate=" + endDate +
//                ", showtime=" + startTime +
                '}';
    }
}
