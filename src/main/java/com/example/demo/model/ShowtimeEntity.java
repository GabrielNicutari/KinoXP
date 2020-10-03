package com.example.demo.model;

import javax.persistence.*;
import java.sql.Time;
import java.util.Collection;
import java.util.Objects;

@Entity
@Table(name = "showtime", schema = "kinoxpcheetas", catalog = "")
public class ShowtimeEntity {
    private int id;
    private Time startTime;
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
    @Column(name = "start_time")
    public Time getStartTime() {
        return startTime;
    }

    public void setStartTime(Time startTime) {
        this.startTime = startTime;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ShowtimeEntity that = (ShowtimeEntity) o;
        return id == that.id &&
                Objects.equals(startTime, that.startTime);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, startTime);
    }

    @OneToMany(mappedBy = "showtimeByShowtimeId")
    public Collection<MovieHasDateHasShowtimeHasRoomEntity> getMovieHasDateHasShowtimeHasRoomsById() {
        return movieHasDateHasShowtimeHasRoomsById;
    }

    public void setMovieHasDateHasShowtimeHasRoomsById(Collection<MovieHasDateHasShowtimeHasRoomEntity> movieHasDateHasShowtimeHasRoomsById) {
        this.movieHasDateHasShowtimeHasRoomsById = movieHasDateHasShowtimeHasRoomsById;
    }
}
