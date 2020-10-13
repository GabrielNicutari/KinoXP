package com.example.demo;

import com.example.demo.repository.ActorRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class DemoApplication {

    @Autowired
      static ActorRepo actorRepo;
    public static void main (String[] args)
    {

        SpringApplication.run(DemoApplication.class, args);
        //actorRepo.fetchAll();

    }

}
