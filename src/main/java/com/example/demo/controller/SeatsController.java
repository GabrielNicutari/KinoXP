package com.example.demo.controller;

import com.example.demo.service.TicketService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@Controller
public class SeatsController {
    @Autowired
    TicketService ticketService;

    @GetMapping("/selectSeats/{showtimeId}")
    public String getSmallRoom(@PathVariable("showtimeId") int showtimeId, Model model) {
        var ls = ticketService.roomList(showtimeId);
        model.addAttribute("seats", ls);
        return "seats";
    }

    @GetMapping("/bigRoom")
    public String getBigRoom() {
        return "seatsBigRoom";
    }
}
