package com.example.demo.repository;

import com.example.demo.model.Actor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;


import java.util.List;
;
@Repository
public class ActorRepo {
    @Autowired
     JdbcTemplate jdbcTemplate;
    public List<Actor> fetchAll(){
        String query = "SELECT * FROM actor;";
        RowMapper<Actor> rm = new BeanPropertyRowMapper<>(Actor.class);
        List<Actor> ls = jdbcTemplate.query(query,rm);
        return ls;
    }
    public Actor findByName(String name)
    {
        String query = "SELECT * FROM actor WHERE name= ?;";
        RowMapper<Actor> rm = new BeanPropertyRowMapper<>(Actor.class);
        return jdbcTemplate.queryForObject(query,rm,name);
    }
}
