package com.example.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
public class SeatsController {

    @GetMapping("/selectSeats/{showtimeId}")
    public String getSmallRoom(@PathVariable("showtimeId") int showtimeId) {

        return "seats";
    }

    @GetMapping("/bigRoom")
    public String getBigRoom() {
        return "seatsBigRoom";
    }
}
