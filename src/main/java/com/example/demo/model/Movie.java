package com.example.demo.model;

import javax.persistence.*;
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
    private String desctiption;


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

    public String getDesctiption() {
        return desctiption;
    }

    public void setDesctiption(String desctiption) {
        this.desctiption = desctiption;
    }

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
                Objects.equals(desctiption, that.desctiption);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, title, ageRequirement, duration, genre, desctiption);
    }

    @Override
    public String toString() {
        return "Movie{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", ageRequirement=" + ageRequirement +
                ", duration=" + duration +
                ", genre='" + genre + '\'' +
                ", desctiption='" + desctiption + '\'' +
                '}';
    }

    public Movie(int id, String title, Integer ageRequirement, int duration, String genre, String desctiption) {
        this.id = id;
        this.title = title;
        this.ageRequirement = ageRequirement;
        this.duration = duration;
        this.genre = genre;
        this.desctiption = desctiption;
    }

    public Movie() {

    }
}
