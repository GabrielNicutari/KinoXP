package com.example.demo.repository;


import com.example.demo.model.Room;
import com.example.demo.model.Ticket;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

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
    public List<Ticket> getTicketById(int id){
        String query = "SELECT * FROM ticket " +
                "WHERE id = ?" ;
        RowMapper <Ticket> rowMapper = new BeanPropertyRowMapper<>(Ticket.class);
        return jdbcTemplate.query(query, rowMapper, id);

    }


    public List<Ticket> soldTicketsForShowtime(int showtimeId){ //return a list of all the tickets that sold for a showtime
        String query = "SELECT * FROM ticket WHERE " +
                "showtime_id = ?";
        RowMapper <Ticket> rowMapper = new BeanPropertyRowMapper(Ticket.class);
        return jdbcTemplate.query(query,rowMapper,showtimeId);
    }

    public List<Ticket> amountOfTicketsForShowtime(int showtimeId){ //by providing a showtime we will get in return the amount of tickets that have been sold for that showtime
        String query = "SELECT COUNT(id) as id FROM ticket "
                + "WHERE showtime_id = ?";
        RowMapper<Ticket> rm = new BeanPropertyRowMapper<>(Ticket.class);
        return jdbcTemplate.query(query,rm,showtimeId);
    }

    public void bookSingleTicket(Ticket ticket){
        String query = "INSERT INTO ticket (email, seat, showtime_id)" +
                "VALUES (?,?,?)";
        jdbcTemplate.update(query,ticket.getEmail(),ticket.getSeat(),ticket.getShowtimeId());
    }

    public List<Ticket> availableTicketsToShowtime(int showtimeId){ //calculate the remaining seats for a showtime
        String query = "SELECT seats-(SELECT COUNT(id) FROM ticket t2 WHERE showtime_id =?) as id " + //using a sub-query to know how many tickets got sold
                "FROM ticket t " +
                "JOIN showtime s ON  t.showtime_id=s.id " +
                "JOIN room r ON s.room_id = r.id " + //checking how many seats there are in the cinema
                "WHERE showtime_id = ?";
        RowMapper<Ticket> rm = new BeanPropertyRowMapper<>(Ticket.class);
        return jdbcTemplate.query(query, rm, showtimeId, showtimeId);

    }

    public List<Ticket> findTicketByEmail(String email){
        String query = "SELECT t.id, t.email,t.seat,m.title,s.date_time FROM ticket t " +
                "JOIN showtime s ON t.showtime_id = s.id " +
                "JOIN movie m ON s.movie_id = m.id " +
                "WHERE email LIKE ?";
        RowMapper <Ticket> rm = new BeanPropertyRowMapper<>(Ticket.class);
        return jdbcTemplate.query(query,rm,email);
    }

    public void cancelTicket(int id){
        String query = "DELETE FROM ticket WHERE id = ?";
        jdbcTemplate.update(query,id);
    }


    //This method is part of showtimeRepo!
    public List<Room> roomSize(int showtimeId){//used for getting amount of seats in the cinema in a specific showtime
        String query  ="SELECT seats FROM showtime s " +
                "JOIN room r ON s.room_id = r.id " +
                "WHERE s.id = ? ";
        RowMapper<Room> rm = new BeanPropertyRowMapper<>(Room.class);
        return jdbcTemplate.query(query,rm,showtimeId);
    }

}
