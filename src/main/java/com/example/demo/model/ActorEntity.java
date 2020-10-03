package com.example.demo.model;

import javax.persistence.*;
import java.util.Collection;
import java.util.Objects;

@Entity
@Table(name = "actor", schema = "kinoxpcheetas", catalog = "")
public class ActorEntity {
    private int id;
    private String name;
    private Collection<MovieHasActorEntity> movieHasActorsById;

    @Id
    @Column(name = "id")
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Basic
    @Column(name = "name")
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ActorEntity that = (ActorEntity) o;
        return id == that.id &&
                Objects.equals(name, that.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name);
    }

    @OneToMany(mappedBy = "actorByActorId")
    public Collection<MovieHasActorEntity> getMovieHasActorsById() {
        return movieHasActorsById;
    }

    public void setMovieHasActorsById(Collection<MovieHasActorEntity> movieHasActorsById) {
        this.movieHasActorsById = movieHasActorsById;
    }
}
