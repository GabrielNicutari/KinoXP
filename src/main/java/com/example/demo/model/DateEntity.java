package com.example.demo.model;

import javax.persistence.*;
import java.sql.Date;
import java.util.Collection;
import java.util.Objects;

@Entity
@Table(name = "date", schema = "kinoxpcheetas", catalog = "")
public class DateEntity {
    private int id;
    private Date startDate;
    private Date endDate;
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
    @Column(name = "start_date")
    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    @Basic
    @Column(name = "end_date")
    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        DateEntity that = (DateEntity) o;
        return id == that.id &&
                Objects.equals(startDate, that.startDate) &&
                Objects.equals(endDate, that.endDate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, startDate, endDate);
    }

    @OneToMany(mappedBy = "dateByDateId")
    public Collection<MovieHasDateHasShowtimeHasRoomEntity> getMovieHasDateHasShowtimeHasRoomsById() {
        return movieHasDateHasShowtimeHasRoomsById;
    }

    public void setMovieHasDateHasShowtimeHasRoomsById(Collection<MovieHasDateHasShowtimeHasRoomEntity> movieHasDateHasShowtimeHasRoomsById) {
        this.movieHasDateHasShowtimeHasRoomsById = movieHasDateHasShowtimeHasRoomsById;
    }
}
