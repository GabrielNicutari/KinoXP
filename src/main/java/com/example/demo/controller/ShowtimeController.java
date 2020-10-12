package com.example.demo.controller;

import com.example.demo.model.Showtime;
import com.example.demo.model.ShowtimeSplit;
import com.example.demo.service.ShowtimeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Optional;

@Controller
public class ShowtimeController {
    @Autowired
    ShowtimeService showtimeService;

    /* showtimeMovieId
    * - Shows movie details (Has yet to be added)
    * Takes two parameters, one is required: movieId, the other is optional and is used to delete or create new showtimes for the movie
    * Method as is, gets all showtimes with the movieId from todays date and for the week.
    * */
    @RequestMapping(value = {"/showtime/{movieId}", "/showtime/{movieId}/{adminEdit}"})
    public String showtimeMovieId (@PathVariable("movieId") int movieId,
                                   @PathVariable("adminEdit") Optional<String> adminEdit,
                                   Model model){
        ArrayList<ArrayList<Showtime>> week = showtimeService.fetchAllInWeekWithMovieId(movieId);
        if(adminEdit.isPresent()){
            String editable = adminEdit.get();
            if(editable.equals("admin1234")){
                model.addAttribute("edit", true);
                model.addAttribute("create", true);
            } else {
                model.addAttribute("edit", false);
                model.addAttribute("create", false);
            }
        }
        model.addAttribute("day1", week.get(0));
        model.addAttribute("day2", week.get(1));
        model.addAttribute("day3", week.get(2));
        model.addAttribute("day4", week.get(3));
        model.addAttribute("day5", week.get(4));
        model.addAttribute("day6", week.get(5));
        model.addAttribute("day7", week.get(6));
        return "showtime";
    }

    @GetMapping("/newShowtime/{movieId}")
    public String newShowtime (@PathVariable("movieId") int movieId, Model model){
        ShowtimeSplit showtimeSplit = new ShowtimeSplit();
        showtimeSplit.setMovieId(movieId);
        // model.addAttribute("timeSelection", showtimeService.generateTimeList(16, 00, 15, 25));
        model.addAttribute("showtimeSplit", showtimeSplit);
        return "newShowtime";
    }

    @PostMapping("/saveShowtimeSplit/{movieId}")
    public String saveShowtimeSplit (@PathVariable("movieId") int movieId, ShowtimeSplit showtimeSplit){
        showtimeSplit.setMovieId(movieId);
        LocalDateTime lt = LocalDateTime.of(showtimeSplit.getDate(), showtimeSplit.getTime());
        Showtime converted = new Showtime();
        converted.setMovieId(movieId);
        converted.setRoomId(showtimeSplit.getRoomId());
        converted.setDateTime(lt.plusHours(8)); //TimeDiff gearhost (Mountain Standard Time) -> java (UTC+2)

        showtimeService.addShowtime(converted);

        return "redirect:/showtime/"+converted.getMovieId()+"/admin1234";
    }

    @GetMapping("deleteShowtime/{showtimeId}")
    public String deleteShowtime(@PathVariable("showtimeId") int showtimeId){
        Showtime s = showtimeService.fetchShowtimeWithId(showtimeId);
        int movieId = s.getMovieId();
        showtimeService.deleteShowtime(showtimeId);
        return "redirect:/showtime/"+movieId+"/admin1234";
    }

    // Testing Link for future integration with TicketController
    @GetMapping("/testTicket/{showtimeId}")
    public String showtimeTicketTest (@PathVariable("showtimeId") int showtimeId, Model model){
        return "testTicket";
    }


}
