package com.example.demo.service;

import com.example.demo.model.Movie;
import com.example.demo.repository.MovieRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MovieService {
    @Autowired
    private MovieRepo movieRepo;

    public List<Movie> fetchAll(){
        return movieRepo.fetchAll();
    }

}
