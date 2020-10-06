package com.example.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class SeatsController {

    @GetMapping("/smallRoom")
    public String getSmallRoom() {
        return "seatsSmallRoom";
    }

    @GetMapping("/bigRoom")
    public String getBigRoom() {
        return "seatsBigRoom";
    }
}
