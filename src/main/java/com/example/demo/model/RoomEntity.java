package com.example.demo.model;

import javax.persistence.*;
import java.util.Collection;
import java.util.Objects;

@Entity
@Table(name = "room", schema = "kinoxpcheetas", catalog = "")
public class RoomEntity {
    private int id;
    private int seats;
    private String name;
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
    @Column(name = "seats")
    public int getSeats() {
        return seats;
    }

    public void setSeats(int seats) {
        this.seats = seats;
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
        RoomEntity that = (RoomEntity) o;
        return id == that.id &&
                seats == that.seats &&
                Objects.equals(name, that.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, seats, name);
    }

    @OneToMany(mappedBy = "roomByRoomId")
    public Collection<MovieHasDateHasShowtimeHasRoomEntity> getMovieHasDateHasShowtimeHasRoomsById() {
        return movieHasDateHasShowtimeHasRoomsById;
    }

    public void setMovieHasDateHasShowtimeHasRoomsById(Collection<MovieHasDateHasShowtimeHasRoomEntity> movieHasDateHasShowtimeHasRoomsById) {
        this.movieHasDateHasShowtimeHasRoomsById = movieHasDateHasShowtimeHasRoomsById;
    }
}
