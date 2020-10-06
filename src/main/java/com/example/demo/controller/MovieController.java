package com.example.demo.controller;

import com.example.demo.model.Movie;
import com.example.demo.service.MovieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class MovieController {

    @Autowired
    private MovieService movieService;

    @GetMapping("/movies")
    public String fetchAll(Model model){
        List<Movie> movieList = movieService.fetchAll();

        model.addAttribute("movies", movieList);

        // for testing purposes
//        for (Movie movie: movieList){
//            System.out.println(movie);
//        }


        // Needs to return /movies.html, not index
        return "/index";
    }

}
