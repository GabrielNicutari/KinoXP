package com.example.demo.controller;

import com.example.demo.model.Actor;
import com.example.demo.model.Movie;
import com.example.demo.model.Showtime;
import com.example.demo.service.MovieService;
import com.example.demo.service.ShowtimeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Controller
public class MovieController {

    @Autowired
    private MovieService movieService;

    @Autowired
    private ShowtimeService showtimeService;

    @GetMapping("/movies")
    public String fetchAll(Model model){
        List<Movie> movieList = movieService.fetchAll();

        model.addAttribute("movies", movieList);

        return "/movies";
    }

    @GetMapping("/movies/getOne/{id}")
    public String getOne(@PathVariable("id") int id, Model model) {
        Movie m = movieService.getOne(id);
        LocalDate date = LocalDate.parse("2020-10-17");
        List<Showtime> showtimes = showtimeService.fetchShowtimesWithDateAndMovieId(date, id);

        //Fetch actors for this specific movie as well
        List<Actor> actorList = movieService.fetchActorsByMovieId(id);
        if(!actorList.isEmpty()) {
            String actors = actorList.get(0).getName();

            for (int i = 1; i < actorList.size(); i++) {
                actors += ", " + actorList.get(i).getName();
            }

            m.setActors(actors);
        }
        model.addAttribute(m);
        model.addAttribute("showtimes", showtimes);

        return "/movie";
    }

    //Use this only to populate the pop-up automatically
    @RequestMapping("/movies/viewOne")
    @ResponseBody
    public Movie viewOne(int id) {
        Movie m = movieService.getOne(id);

        List<Actor> actorList = movieService.fetchActorsByMovieId(id);
        if(!actorList.isEmpty()) {
            String actors = actorList.get(0).getName();

            for (int i = 1; i < actorList.size(); i++) {
                actors += ", " + actorList.get(i).getName();
            }
            m.setActors(actors);
        }

        return m; //still uses the same flow as getOne
    }

    @PostMapping(value="/movies/add")
    public String add(@ModelAttribute Movie m) {
        movieService.add(m);
        return "redirect:/movies";
    }

    @RequestMapping(value="/movies/update", method = {RequestMethod.PUT, RequestMethod.GET})
    public String update(@ModelAttribute Movie movie) {
        movieService.update(movie, movie.getId());
        return "redirect:/movies/getOne/" + movie.getId();
    }

    @RequestMapping(value="/movies/delete", method = {RequestMethod.DELETE, RequestMethod.GET})
    public String delete(int id) {
        movieService.delete(id);
        return "redirect:/movies";
    }
}
