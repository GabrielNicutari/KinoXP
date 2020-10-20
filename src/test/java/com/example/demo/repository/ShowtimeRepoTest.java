package com.example.demo.repository;

import com.example.demo.model.Showtime;
import org.junit.Before;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.sql.Date;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.InstanceOfAssertFactories.LOCAL_DATE;
import static org.junit.jupiter.api.Assertions.*;
@ExtendWith(SpringExtension.class)
@SpringBootTest
@AutoConfigureMockMvc
class ShowtimeRepoTest {

    @Autowired
    private  ShowtimeRepo showtimeRepo;
    @Before
    void insertTestShowtime() {
        LocalDateTime localDateTime= LocalDateTime.now();
        Showtime showtime = new Showtime(2, 1, 1, localDateTime, "19-05-2020","09:14");
        showtimeRepo.addShowtime(showtime);
    }
    @Test
    void fetchAllShowtimes() {
    assertThat(showtimeRepo.fetchAllShowtimes()).isNotEmpty();
    }

    @Test
    void fetchAllWithMovieId() {
        assertThat(showtimeRepo.fetchAllWithMovieId(2)).isNotEmpty();
        assertThat(showtimeRepo.fetchAllWithMovieId(2)).isInstanceOf(List.class);
    }

    @Test
    void fetchShowtimeWithId() {
        assertThat(showtimeRepo.fetchShowtimeWithId(1)).isNotNull();
        assertThat(showtimeRepo.fetchShowtimeWithId(1)).isInstanceOf(Showtime.class);
    }

    @Test
    void fetchAllInWeekWithMovieId() {
        assertThat(showtimeRepo.fetchAllInWeekWithMovieId(1)).isNotEmpty();
        assertThat(showtimeRepo.fetchAllInWeekWithMovieId(1)).isInstanceOf(List.class);
    }


    @Test
    void addShowtime() {
        assertThat(showtimeRepo.fetchShowtimeWithId(2)).isNotNull();
        assertThat(showtimeRepo.fetchShowtimeWithId(2)).isInstanceOf(Showtime.class);
    }

}