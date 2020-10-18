package com.example.demo.controller;

import com.example.demo.model.Movie;
import com.example.demo.model.Ticket;
import com.example.demo.service.TicketService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
@Controller
public class SeatsController {
    @Autowired
    TicketService ticketService;

    @GetMapping("/selectSeats/{showtimeId}")
    public String getSmallRoom(@PathVariable("showtimeId") int showtimeId, Model model) {
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
        return null;
    }
    @GetMapping("/bigRoom")
    public String getBigRoom() {
        return "seatsBigRoom";
    }

}
