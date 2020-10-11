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

    //need to move to showtimeRepo!!
    public int roomSize(int showtimeId){
        return ticketsRepo.roomSize(showtimeId).get(0).getSeats();
    }

    //need to move showtimeRepo!!
    public List<Boolean> roomList(int showtimeId){
        List<Boolean> cinema = new ArrayList<>(roomSize(showtimeId)); //list in the size of the cinema
        List<Integer> bookedSeats = bookedSeats(showtimeId); // list of booked seats
        for (int i = 0; i<bookedSeats.size(); i++){
            cinema.set(bookedSeats.get(i),true);
        }
        return cinema;
    }

    public List<Ticket> findTicketByEmail(String email){
        return ticketsRepo.findTicketByEmail(email);
    }

    public void cancelTicket(int id){
        ticketsRepo.cancelTicket(id);
    }



}
