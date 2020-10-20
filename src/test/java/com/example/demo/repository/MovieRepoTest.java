package com.example.demo.repository;

import com.example.demo.model.Actor;
import com.example.demo.model.Movie;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.jdbc.DataJdbcTest;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.sql.Date;
import java.sql.Time;
import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.InstanceOfAssertFactories.ITERABLE;
import static org.junit.jupiter.api.Assertions.*;
@ExtendWith(SpringExtension.class)
@SpringBootTest
@AutoConfigureMockMvc
class MovieRepoTest {

    @Autowired
    MovieRepo movieRepo;

    @Test
    void fetchAll() {
        List<Movie> movies=movieRepo.fetchAll();
        assertThat(movies).isNotEmpty();
    }

    @Test
    void findById() {
        assertThat(movieRepo.findById(2)).isNotNull();
        assertThat(movieRepo.findById(2)).isExactlyInstanceOf(Movie.class);
    }

    @Test
    void delete() {
        assertThat(movieRepo.delete(25)).isFalse();
    }

    @Test
    void fetchActorsByMovieId() {
      assertThat(movieRepo.fetchActorsByMovieId(4)).isNotEmpty();
      assertThat(movieRepo.fetchActorsByMovieId(4)).isInstanceOf(ArrayList.class);

    }
}