package com.example.demo.Controller;

import com.example.demo.model.Movie;
import com.example.demo.service.MovieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class MovieController {

    @Autowired
    private MovieService movieService;

    @GetMapping("/movies")
    public String fetchAll(Model model){
        List<Movie> movieList = movieService.fetchAll();

        model.addAttribute("movies", movieList);
        return "/movies";
    }

    @GetMapping("/getOne/{id}")
    public String getOne(@PathVariable("id") int id, Model model) {
        model.addAttribute(movieService.getOne(id));
        return "/movie";
    }

    @PostMapping(value="/movies/add")
    public String add(@ModelAttribute Movie m) {
        movieService.add(m);
        return "redirect:/movies";
    }
}
