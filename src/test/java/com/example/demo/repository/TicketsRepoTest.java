package com.example.demo.repository;

import com.example.demo.model.Ticket;
import org.junit.After;
import org.junit.Before;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.event.annotation.BeforeTestExecution;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.ArrayList;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
@ExtendWith(SpringExtension.class)
@SpringBootTest
@AutoConfigureMockMvc
class TicketsRepoTest {

    @Autowired
    private TicketsRepo ticketsRepo= new TicketsRepo();
    @Before
    void enterTestTicket(){
        Ticket test_ticket=new Ticket(1,"test email",45,1);
        ticketsRepo.bookSingleTicket(test_ticket);
    }
   @After
    void removeTestTicket() {
       ticketsRepo.cancelTicket(73);
   }
    @Test
    void fetchAll() {
        assertThat(ticketsRepo.fetchAll()).isNotEmpty();
        assertThat(ticketsRepo.fetchAll()).isInstanceOf(ArrayList.class);
    }

    @Test
    void soldTicketsForShowtime() {
        assertThat(ticketsRepo.soldTicketsForShowtime(1)).isNotEmpty();
        assertThat(ticketsRepo.soldTicketsForShowtime(1)).isInstanceOf(ArrayList.class);
    }

    @Test
    void amountOfTicketsForShowtime() {
        assertThat(ticketsRepo.amountOfTicketsForShowtime(1)).isNotEmpty();
        assertThat(ticketsRepo.amountOfTicketsForShowtime(1)).isInstanceOf(ArrayList.class);

    }

    @Test
    void bookSingleTicket() {
        assertThat(ticketsRepo.findTicketByEmail("test_email")).isNotNull();
    }

    @Test
    void availableTicketsToShowtime() {
        assertThat(ticketsRepo.availableTicketsToShowtime(1)).isNotEmpty();
        assertThat(ticketsRepo.availableTicketsToShowtime(1)).isInstanceOf(ArrayList.class);
    }

    @Test
    void findTicketByEmail() {
        assertThat(ticketsRepo.findTicketByEmail("2")).isNotNull();
    }

}