package com.example.demo.model;

import javax.persistence.Column;
import javax.persistence.Id;
import java.io.Serializable;
import java.util.Objects;

public class MovieHasDateHasShowtimeHasRoomEntityPK implements Serializable {
    private int movieId;
    private int dateId;
    private int showtimeId;
    private int roomId;

    @Column(name = "movie_id")
    @Id
    public int getMovieId() {
        return movieId;
    }

    public void setMovieId(int movieId) {
        this.movieId = movieId;
    }

    @Column(name = "date_id")
    @Id
    public int getDateId() {
        return dateId;
    }

    public void setDateId(int dateId) {
        this.dateId = dateId;
    }

    @Column(name = "showtime_id")
    @Id
    public int getShowtimeId() {
        return showtimeId;
    }

    public void setShowtimeId(int showtimeId) {
        this.showtimeId = showtimeId;
    }

    @Column(name = "room_id")
    @Id
    public int getRoomId() {
        return roomId;
    }

    public void setRoomId(int roomId) {
        this.roomId = roomId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        MovieHasDateHasShowtimeHasRoomEntityPK that = (MovieHasDateHasShowtimeHasRoomEntityPK) o;
        return movieId == that.movieId &&
                dateId == that.dateId &&
                showtimeId == that.showtimeId &&
                roomId == that.roomId;
    }

    @Override
    public int hashCode() {
        return Objects.hash(movieId, dateId, showtimeId, roomId);
    }
}
