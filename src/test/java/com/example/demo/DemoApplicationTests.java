package com.example.demo;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;


import com.example.demo.repository.TicketsRepo;
import com.example.demo.service.TicketService;
import com.example.demo.controller.Home;
import com.example.demo.model.Actor;
import com.example.demo.model.Movie;
import com.example.demo.repository.ActorRepo;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class DemoApplicationTests {
    @Autowired
    TicketService ticketService;

/*
    @Autowired
    private Home controller;
    @Autowired
    private ActorRepo actorRepo;
    @Autowired
    private Movie movie;

 */

    @Test
    public void contextLoads() throws Exception {
       // assertThat(controller).isNotNull();
    }

    @Test
    public void whenFindByName_thenReturnEmployee() {
        // given
/*
        TestEntityManager entityManager = null;
        entityManager.persist(alex);
        entityManager.flush();

        // when
        Actor found = actorRepo.findByName("Tim Roth");

        // then
        assertEquals("Tim Roth", found.getName());
*/
    }
  
    @Test
    public void checkTicketSoldCount(){ //those test will not pass when the database will get more populated
        assertEquals(1,ticketService.amountOfTicketsForShowtime(1));
        assertEquals(239, ticketService.availableTicketsToShowtime(1));
        assertEquals(2,ticketService.bookedSeats(1).get(0));
    }

}