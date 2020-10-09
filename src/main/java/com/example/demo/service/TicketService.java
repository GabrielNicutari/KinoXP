package com.example.demo.service;

import com.example.demo.model.Ticket;
import com.example.demo.repository.TicketsRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class TicketService {
    @Autowired
    TicketsRepo ticketsRepo;

    public List<Ticket> fetchAll(){
        return ticketsRepo.fetchAll();
    }

    public List<Ticket> soldTicketsForShowTime(int showtimeId){
        return ticketsRepo.soldTicketsForShowtime(showtimeId);
    }

    public List<Integer> bookedSeats(int showtimeId){
        List<Ticket> ls = soldTicketsForShowTime(showtimeId);
        List <Integer> unavailableSeats = new ArrayList<>();
        for ( Ticket ticket: ls){
            unavailableSeats.add(ticket.getSeat());
        }
        return unavailableSeats;
    }
     public int amountOfTicketsForShowtime(int showtimeId){
        return ticketsRepo.amountOfTicketsForShowtime(showtimeId).get(0).getId();
     }

     public void bookSingleTicket(Ticket ticket){
        ticketsRepo.bookSingleTicket(ticket);
     }

    public int availableTicketsToShowtime(int showtimeId) { //calculate the remaining seats for a showtime
        return ticketsRepo.availableTicketsToShowtime(showtimeId).get(0).getId();
    }

}
