package com.example.demo.controller;

import com.example.demo.model.Showtime;
import com.example.demo.service.ShowtimeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.time.LocalDate;
import java.util.List;

public class ForGabriel {

    @Autowired
    ShowtimeService showtimeService;

    @GetMapping("specificDate/{movieId}")
    public String specificDate(@PathVariable("movieId") int movieId, String dateSelected, Model model){
        if(dateSelected != null){
            // Code for dateSelected.
            if(showtimeService.isDate(dateSelected)){
                LocalDate ld = LocalDate.parse(dateSelected);
                List<Showtime> showtimeList = showtimeService.fetchShowtimesWithDateAndMovieId(ld, movieId);
                model.addAttribute("date", "isDate");
                model.addAttribute("day1", showtimeList);
                System.out.println("Gotten from showtime");
            }
        }
        return "specificDate";
    }
}
