package com.example.demo.model;

import javax.persistence.*;
import java.util.Collection;
import java.util.Objects;

@Entity
@Table(name = "movie", schema = "kinoxpcheetas", catalog = "")
public class MovieEntity {
    private int id;
    private String title;
    private Integer ageRequirement;
    private int duration;
    private String genre;
    private String desctiption;
    private Collection<MovieHasActorEntity> movieHasActorsById;
    private Collection<MovieHasDateHasShowtimeHasRoomEntity> movieHasDateHasShowtimeHasRoomsById;

    @Id
    @Column(name = "id")
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Basic
    @Column(name = "title")
    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    @Basic
    @Column(name = "age_requirement")
    public Integer getAgeRequirement() {
        return ageRequirement;
    }

    public void setAgeRequirement(Integer ageRequirement) {
        this.ageRequirement = ageRequirement;
    }

    @Basic
    @Column(name = "duration")
    public int getDuration() {
        return duration;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }

    @Basic
    @Column(name = "genre")
    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    @Basic
    @Column(name = "desctiption")
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
        MovieEntity that = (MovieEntity) o;
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

    @OneToMany(mappedBy = "movieByMovieId")
    public Collection<MovieHasActorEntity> getMovieHasActorsById() {
        return movieHasActorsById;
    }

    public void setMovieHasActorsById(Collection<MovieHasActorEntity> movieHasActorsById) {
        this.movieHasActorsById = movieHasActorsById;
    }

    @OneToMany(mappedBy = "movieByMovieId")
    public Collection<MovieHasDateHasShowtimeHasRoomEntity> getMovieHasDateHasShowtimeHasRoomsById() {
        return movieHasDateHasShowtimeHasRoomsById;
    }

    public void setMovieHasDateHasShowtimeHasRoomsById(Collection<MovieHasDateHasShowtimeHasRoomEntity> movieHasDateHasShowtimeHasRoomsById) {
        this.movieHasDateHasShowtimeHasRoomsById = movieHasDateHasShowtimeHasRoomsById;
    }
}
