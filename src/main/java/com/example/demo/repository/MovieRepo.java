package com.example.demo.repository;

import com.example.demo.model.Actor;
import com.example.demo.model.Movie;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.util.Arrays;
import java.util.List;

@Repository
public class MovieRepo {

    @Autowired
    JdbcTemplate template;
    public List<Movie> fetchAll(){
        String query = "SELECT m.id, m.title, m.image FROM movie m";
        RowMapper<Movie> rm = new BeanPropertyRowMapper<>(Movie.class);
        return template.query(query, rm);
    }

    public List<Movie> fetchAllThisWeek() {
        String query = "SELECT DISTINCT m.id, m.title, m.image FROM movie m, showtime s WHERE s.movie_id = m.id " +
                "AND s.date_time BETWEEN CURRENT_DATE() AND CURRENT_DATE()+6";
        RowMapper<Movie> rm = new BeanPropertyRowMapper<>(Movie.class);
        return template.query(query, rm);
    }

    public Movie findById(int id) {
        String query = "SELECT m.* FROM movie m WHERE m.id = ?";
        RowMapper<Movie> rowMapper = new BeanPropertyRowMapper<>(Movie.class);
        return template.queryForObject(query, rowMapper, id);
    }

    public void add(Movie m) {
        String query = "INSERT INTO movie (title, age_requirement, duration, genre, description, image, cover)" +
                "VALUES (?, ?, ?, ?, ?, ?, ?)";
        template.update(query, m.getTitle(), m.getAgeRequirement(), m.getDuration(), m.getGenre(), m.getDescription(), m.getImage(), m.getCover());

        splitActors(m.getActors(), getMovieId(m.getTitle()));
    }

    private int getMovieId(String title) {
        return template.queryForObject("SELECT id FROM movie WHERE title = '" + title + "'", int.class);
    }

    public void splitActors(String actors, int movieId) {
        String[] actorList = actors.split(",");
        for (String actor: actorList) {
            actor = actor.trim();
            boolean exists = checkActorTable(actor);
            if(!exists) {
                updateTables(actor, movieId); //actor & movie_has_actor
            } else {
                updateTable(actor, movieId); //only movie_has_actor
            }
        }
    }

    private void updateTable(String actor, int movieId) {
        //get the id of the corresponding row
        int actorId = template.queryForObject("SELECT id FROM actor WHERE name = '" + actor + "'", int.class);
        System.out.println("table:" + movieId + " " + actorId + " " + actor);

        boolean exists = checkMovieHasActorTable(actorId, movieId);
        System.out.println("Exists: " + exists);
        if(!exists) {
            String query3 = "INSERT INTO movie_has_actor (movie_id, actor_id) VALUES (?, ?)";
            template.update(query3, movieId, actorId);
        }
    }

    private boolean checkMovieHasActorTable(int actorId, int movieId) {
        return template.queryForObject("SELECT EXISTS(SELECT movie_id, actor_id FROM movie_has_actor WHERE movie_id = " + movieId + " AND actor_id = "
                + actorId + ")", Boolean.class);
    }

    private void updateTables(String actor, int movieId) {
        String query1 = "INSERT INTO actor (name) VALUES (?)";
        template.update(query1, actor);

        //get the id of the inserted row
        int actorId = template.queryForObject("SELECT id FROM actor WHERE name = '" + actor + "'", int.class);
        System.out.println("tables:" + movieId + " " + actorId + " " + actor);

        String query3 = "INSERT INTO movie_has_actor (movie_id, actor_id) VALUES (?, ?)";
        template.update(query3, movieId, actorId);
    }

    private Boolean checkActorTable(String actor) {
        return template.queryForObject("SELECT EXISTS(SELECT name FROM actor WHERE name = \"" + actor + "\")", Boolean.class);
    }

    public void update(Movie m, int id) {
        splitActors(m.getActors(), getMovieId(m.getTitle()));

        String query = "UPDATE movie SET title = ?, age_requirement = ?, duration = ?, genre = ?, description = ?, image = ?, cover = ? WHERE id= ?";
        template.update(query, m.getTitle(), m.getAgeRequirement(), m.getDuration(), m.getGenre(), m.getDescription(), m.getImage(), m.getCover(), id);
    }

    public Boolean delete(int id) {
        String query = "DELETE FROM movie WHERE id = ?";
        return template.update(query, id) < 0;
    }

    public List<Actor> fetchActorsByMovieId(int movieId) {
        String query = "SELECT a.id, name FROM actor a, movie_has_actor mha WHERE mha.actor_id = a.id AND mha.movie_id = " + movieId;
        RowMapper<Actor> rm = new BeanPropertyRowMapper<>(Actor.class);
        return template.query(query, rm);
    }


}
