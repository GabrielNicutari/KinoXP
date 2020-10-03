package com.example.demo.model;

import javax.persistence.Column;
import javax.persistence.Id;
import java.io.Serializable;
import java.util.Objects;

public class MovieHasActorEntityPK implements Serializable {
    private int movieId;
    private int actorId;

    @Column(name = "movie_id")
    @Id
    public int getMovieId() {
        return movieId;
    }

    public void setMovieId(int movieId) {
        this.movieId = movieId;
    }

    @Column(name = "actor_id")
    @Id
    public int getActorId() {
        return actorId;
    }

    public void setActorId(int actorId) {
        this.actorId = actorId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        MovieHasActorEntityPK that = (MovieHasActorEntityPK) o;
        return movieId == that.movieId &&
                actorId == that.actorId;
    }

    @Override
    public int hashCode() {
        return Objects.hash(movieId, actorId);
    }
}
