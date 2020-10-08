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

    public List<Ticket> soldTicketsForShowtime(int showtimeId){ //return a list of all the tickets that sold for a showtime
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

    public int amountOfTicketsForShowtime(int showtimeId){ //by providing a showtime we will get in return the amount of tickets that have been sold for that showtime
        String query = "SELECT COUNT(id) as id FROM ticket "
                + "WHERE showtime_id = ?";
        RowMapper<Ticket> rm = new BeanPropertyRowMapper<>(Ticket.class);
        return jdbcTemplate.query(query,rm,showtimeId).get(0).getId();
    }

    public void bookSingleTicket(Ticket ticket){
        String query = "INSERT INTO ticket (email, seat, showtime_id)" +
                "VALUES (?,?,?)";
        jdbcTemplate.update(query,ticket.getEmail(),ticket.getSeat(),ticket.getShowtimeId());
    }

    public int availableTicketsToShowtime(int showtimeId){ //calculate the remaining seats for a showtime
        String query = "SELECT seats-(SELECT COUNT(id) FROM ticket t2 WHERE showtime_id =?) as id " + //using a sub-query to know how many tickets got sold
                "FROM ticket t " +
                "JOIN showtime s ON  t.showtime_id=s.id " +
                "JOIN room r ON s.room_id = r.id " + //checking how many seats there are in the cinema 
                "WHERE showtime_id = ?;";
        RowMapper<Ticket> rm = new BeanPropertyRowMapper<>(Ticket.class);
        return jdbcTemplate.query(query, rm, showtimeId, showtimeId).get(0).getId();


    }

}
