package com.example.demo.Controller;


import com.example.demo.model.Ticket;
import com.example.demo.service.TicketService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
public class TicketController {
    @Autowired
    TicketService ticketService;


    @GetMapping("/FillCustomerInformation/{id}")//need to be a url with the id of the showtime in order to book the ticket to
    public String bookTicket1(@PathVariable("id") int id,Model model){
        model.addAttribute("id", id);
        return "bookTicket";

    }
    @PostMapping("/FillCustomerInfo")
    public String bookTicket (@ModelAttribute Ticket ticket){
        ticketService.bookSingleTicket(ticket);
        return "bookTicket";
    }

    @GetMapping("/ticketByMail/{email}")
    public String viewTicket(@PathVariable("email") String email,Model model){
        var ls= ticketService.findTicketByEmail(email);
        model.addAttribute("tickets",ls);
        return "viewTickets";

    }

    @PostMapping("/cancelTicket/{id}")
    public String cancelContract(@PathVariable("id") int id){
        ticketService.cancelTicket(id);
        return "bookTicket";
    }


}
