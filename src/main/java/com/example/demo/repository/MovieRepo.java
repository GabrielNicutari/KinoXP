package com.example.demo.repository;

import com.example.demo.model.Movie;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class MovieRepo {

    @Autowired
    JdbcTemplate template;
    public List<Movie> fetchAll(){
        String query = "SELECT m.id, m.title, m.image  FROM movie m";
        RowMapper<Movie> rm = new BeanPropertyRowMapper<>(Movie.class);
        return template.query(query, rm);
    }

    public Movie findById(int id) {
        String query = "SELECT m.* FROM movie m WHERE m.id = ?";
        RowMapper<Movie> rowMapper = new BeanPropertyRowMapper<>(Movie.class);
        return template.queryForObject(query, rowMapper, id);
    }

    public void add(Movie m) {
        String query = "INSERT INTO movie (title, age_requirement, duration, genre, description, image)" +
                "VALUES (?, ?, ?, ?, ?, ?)";
        template.update(query, m.getTitle(), m.getAgeRequirement(), m.getDuration(), m.getGenre(), m.getDescription(), m.getImage());
    }
}
