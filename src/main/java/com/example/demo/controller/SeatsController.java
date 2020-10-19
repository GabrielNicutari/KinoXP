package com.example.demo.controller;

import com.example.demo.model.Movie;
import com.example.demo.model.Showtime;
import com.example.demo.model.Test;
import com.example.demo.model.Ticket;
import com.example.demo.service.ShowtimeService;
import com.example.demo.service.TicketService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

@Controller
public class SeatsController {
    @Autowired
    TicketService ticketService;

    @Autowired
    ShowtimeService showtimeService;

    @GetMapping("/selectSeats/{showtimeId}")
    public String getSmallRoom(@PathVariable("showtimeId") int showtimeId, Model model) {
        var ls = ticketService.roomList(showtimeId);
        model.addAttribute("showtimeId",showtimeId);
        model.addAttribute("seats", ls);
        return "seats";
    }

    @PostMapping("/selectSeats")
    public String getRoom(@ModelAttribute Showtime showtime, Model model) {
        int movieId = showtime.getMovieId();
        System.out.println(movieId);

        //merge date and time into dateTime
        LocalDate date = LocalDate.parse(showtime.getDate());
        LocalTime time = LocalTime.parse(showtime.getTime());
        LocalDateTime dateTime = LocalDateTime.of(date, time);

        System.out.println(dateTime);

        showtime.setDateTime(dateTime);

        //find the showtimeId based on the above
        int showtimeId = showtimeService.findIdBasedOnFields(movieId, dateTime);
        System.out.println(showtimeId);

        var ls = ticketService.roomList(showtimeId);
        model.addAttribute("showtimeId",showtimeId);
        model.addAttribute("seats", ls);
        return "seats";
    }

    @PostMapping("/bookSeats")
    public String bookingTickets(@ModelAttribute Movie movie){
        System.out.println(movie);
        var seats=movie.getDescription().split(" ");//breaking down the requested seats to array
        for (String seat: seats){
            ticketService.bookSingleTicket(new Ticket(movie.getTitle(), Integer.parseInt(seat),movie.getId()));
        }
        return "redirect:/movies";
    }
    @GetMapping("/bigRoom")
    public String getBigRoom() {
        return "seatsBigRoom";
    }

}
