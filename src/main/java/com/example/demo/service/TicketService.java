package com.example.demo.service;

import com.example.demo.model.Seat;
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
    public Seat[][] roomList(int showtimeId){
        int size = roomSize(showtimeId);
        int row;
        int column;
        if (size == 240){
            row = 20;
            column  = 12;
        }
        else{
            row = 25;
            column = 16;
        }
        Seat[][] seats = new Seat[row][column];
        List<Integer> bookedSeats = bookedSeats(showtimeId); // list of booked seats

        for(int bookedSeat : bookedSeats ){ //mark occupied seats in the matrix
            seats[bookedSeat/column][bookedSeat%column] = new Seat(" taken",0);
        }
        int count = 0;
        for (int  i = 0; i < seats.length ;i ++){
            for (int j = 0 ; j<seats[i].length ; j ++){
                if(seats[i][j]==null) {
                    seats[i][j] = new Seat(" ", 0); //sign up seats object into the matrix with seat number
                }
                seats[i][j].setNumber(count);
                count++;
            }
        }
        return seats;
    }

    public List<Ticket> findTicketByEmail(String email){
        return ticketsRepo.findTicketByEmail(email);
    }

    public void cancelTicket(int id){
        ticketsRepo.cancelTicket(id);
    }



}
