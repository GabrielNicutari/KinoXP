package com.example.demo.Controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class Home {

    @GetMapping({"", "/", "/index", "/index.html"})
    public String getHomePage() {
        return "index";
    }

    @GetMapping("/listOfMovies")
    public String getListOfMovies() {
        return "movieList";
    }
}