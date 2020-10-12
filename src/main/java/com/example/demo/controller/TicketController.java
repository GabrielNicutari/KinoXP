package com.example.demo.Controller;


import com.example.demo.model.Movie;
import com.example.demo.model.Ticket;
import com.example.demo.repository.MovieRepo;
import com.example.demo.repository.TicketsRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class TicketController {
    @Autowired
    TicketsRepo ticketsRepo;


    @GetMapping("/FillCustomerInformation/{id}")//need to be a url with the id of the showtime in order to book the ticket to
    public String bookTicket1(@PathVariable("id") int id,Model model){
        model.addAttribute("id", id);
        return "bookTicket";
    }
    @PostMapping("/FillCustomerInfo")
    public String bookTicket (@ModelAttribute Ticket ticket){
        ticketsRepo.bookSingleTicket(ticket);
        return "bookTicket";
    }


}
