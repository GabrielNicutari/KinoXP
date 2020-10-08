package com.example.demo.repository;


import com.example.demo.model.Ticket;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class TicketsRepo {

    @Autowired
    JdbcTemplate jdbcTemplate;
    public List<Ticket> fetchAll(){
        String query = "SELECT * FROM ticket";
        RowMapper <Ticket> rowMapper = new BeanPropertyRowMapper<>(Ticket.class);
        return jdbcTemplate.query(query, rowMapper);
    }

    public List<Ticket> soldTicketsForShowtime(int showtimeId){
        String query = "SELECT * FROM ticket WHERE " +
                "showtime_id = ?";
        RowMapper <Ticket> rowMapper = new BeanPropertyRowMapper(Ticket.class);
        return jdbcTemplate.query(query,rowMapper,showtimeId);
    }

    public List<Integer> unavailableSeatsForShowtime(int showtimeId){ //return all the unavailable seats for a specific showtime
        List<Ticket> ls = soldTicketsForShowtime(showtimeId);
        List <Integer> unavailableSeats = new ArrayList<>();
        for ( Ticket ticket: ls){
            unavailableSeats.add(ticket.getSeat());
        }
        return unavailableSeats;
    }

    public void bookTickets(List<Ticket> tickets){
        String query = "INSERT INTO ticket (email, seat, showtime_id)" +
                "VALUES (?,?,?)";
        for (int i = 0; i<tickets.size();i++){
            jdbcTemplate.update(query,tickets.get(i).getEmail(),tickets.get(i).getSeat(),tickets.get(i).getShowtimeId());
        }
    }

}
