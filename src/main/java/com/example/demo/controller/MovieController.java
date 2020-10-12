package com.example.demo.Controller;

import com.example.demo.model.Actor;
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

    @GetMapping("/movies/getOne/{id}")
    public String getOne(@PathVariable("id") int id, Model model) {
        //Fetch actors for this specific movie as well
        List<Actor> actorList = movieService.fetchActorsByMovieId(id);

        Movie m = movieService.getOne(id);
        String actors = actorList.get(0).getName();
        for (int i = 1; i < actorList.size(); i++) {
            actors += ", " + actorList.get(i).getName();
        }
        System.out.println(actors);
        m.setActors(actors);
        model.addAttribute(m);

        return "/movie";
    }

    //Use this only to populate the pop-up automatically
    @RequestMapping("/movies/viewOne")
    @ResponseBody
    public Movie viewOne(int id) {
        return movieService.getOne(id); //still uses the same flow as getOne
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
