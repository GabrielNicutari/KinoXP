package com.example.demo.model;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "movie_has_date_has_showtime_has_room", schema = "kinoxpcheetas", catalog = "")
@IdClass(MovieHasDateHasShowtimeHasRoomEntityPK.class)
public class MovieHasDateHasShowtimeHasRoomEntity {
    private int movieId;
    private int dateId;
    private int showtimeId;
    private int roomId;
    private MovieEntity movieByMovieId;
    private DateEntity dateByDateId;
    private ShowtimeEntity showtimeByShowtimeId;
    private RoomEntity roomByRoomId;

    @Id
    @Column(name = "movie_id")
    public int getMovieId() {
        return movieId;
    }

    public void setMovieId(int movieId) {
        this.movieId = movieId;
    }

    @Id
    @Column(name = "date_id")
    public int getDateId() {
        return dateId;
    }

    public void setDateId(int dateId) {
        this.dateId = dateId;
    }

    @Id
    @Column(name = "showtime_id")
    public int getShowtimeId() {
        return showtimeId;
    }

    public void setShowtimeId(int showtimeId) {
        this.showtimeId = showtimeId;
    }

    @Id
    @Column(name = "room_id")
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
        MovieHasDateHasShowtimeHasRoomEntity that = (MovieHasDateHasShowtimeHasRoomEntity) o;
        return movieId == that.movieId &&
                dateId == that.dateId &&
                showtimeId == that.showtimeId &&
                roomId == that.roomId;
    }

    @Override
    public int hashCode() {
        return Objects.hash(movieId, dateId, showtimeId, roomId);
    }

    @ManyToOne
    @JoinColumn(name = "movie_id", referencedColumnName = "id", nullable = false)
    public MovieEntity getMovieByMovieId() {
        return movieByMovieId;
    }

    public void setMovieByMovieId(MovieEntity movieByMovieId) {
        this.movieByMovieId = movieByMovieId;
    }

    @ManyToOne
    @JoinColumn(name = "date_id", referencedColumnName = "id", nullable = false)
    public DateEntity getDateByDateId() {
        return dateByDateId;
    }

    public void setDateByDateId(DateEntity dateByDateId) {
        this.dateByDateId = dateByDateId;
    }

    @ManyToOne
    @JoinColumn(name = "showtime_id", referencedColumnName = "id", nullable = false)
    public ShowtimeEntity getShowtimeByShowtimeId() {
        return showtimeByShowtimeId;
    }

    public void setShowtimeByShowtimeId(ShowtimeEntity showtimeByShowtimeId) {
        this.showtimeByShowtimeId = showtimeByShowtimeId;
    }

    @ManyToOne
    @JoinColumn(name = "room_id", referencedColumnName = "id", nullable = false)
    public RoomEntity getRoomByRoomId() {
        return roomByRoomId;
    }

    public void setRoomByRoomId(RoomEntity roomByRoomId) {
        this.roomByRoomId = roomByRoomId;
    }
}
