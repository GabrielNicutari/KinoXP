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

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
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
    // Add to paths - showtime/movieid/week... - showtime/movieId/dateSelected (Both with editable - adminEdit extension)
    /*
    @GetMapping(value = {"/showtime/{movieId}", "/showtime/{movieId}/{adminEdit}", "/showtime/{movieId}/date/{dateSelected}"})
    public String showtimeMovieId (@PathVariable("movieId") int movieId,
                                   @PathVariable("adminEdit") Optional<String> adminEdit,
                                   @PathVariable("dateSelected") Optional<String> dateSelected,
                                   Model model){
        if(adminEdit.isPresent()){
            String editable = adminEdit.get();
            System.out.println("Says admin is present");
            if(editable.equals("admin1234")){
                model.addAttribute("edit", true);
                model.addAttribute("create", true);
            } else {
                model.addAttribute("edit", false);
                model.addAttribute("create", false);
            }
        }
        if(dateSelected.isPresent()){
            // Code for dateSelected.
            System.out.println(dateSelected + "This is the date selected. Inside isPresent for date");
            String d = dateSelected.get();
            System.out.println("After converting: " + d);
            if(showtimeService.isDate(d)){
                LocalDate ld = LocalDate.parse(d);
                List<Showtime> showtimeList = showtimeService.fetchShowtimesWithDateAndMovieId(ld, movieId);
                model.addAttribute("date", "isDate");
                model.addAttribute("day1", showtimeList);
                System.out.println("Gotten from showtime");
            }
        } else {
            System.out.println("isNotDate Sent");
            model.addAttribute("date", "isNotDate");
            ArrayList<ArrayList<Showtime>> week = showtimeService.fetchAllInWeekWithMovieId(movieId);
            model.addAttribute("day1", week.get(0));
            model.addAttribute("day2", week.get(1));
            model.addAttribute("day3", week.get(2));
            model.addAttribute("day4", week.get(3));
            model.addAttribute("day5", week.get(4));
            model.addAttribute("day6", week.get(5));
            model.addAttribute("day7", week.get(6));
        }
        return "showtime";
    }
     */
    @GetMapping(value = {"/showtime/{movieId}", "/showtime/{movieId}/{adminEdit}"})
    public String showtimeMovieId (@PathVariable("movieId") int movieId,
                                   @PathVariable("adminEdit") Optional<String> adminEdit,
                                   Model model, String dateSelected){
        if(adminEdit.isPresent()){
            String editable = adminEdit.get();
            System.out.println("Says admin is present");
            if(editable.equals("admin1234")){
                model.addAttribute("edit", true);
                model.addAttribute("create", true);
            } else {
                model.addAttribute("edit", false);
                model.addAttribute("create", false);
            }
        }
        if(dateSelected != null){
            // Code for dateSelected.
            System.out.println(dateSelected + "This is the date selected. Inside isPresent for date");
            if(showtimeService.isDate(dateSelected)){
                LocalDate ld = LocalDate.parse(dateSelected);
                List<Showtime> showtimeList = showtimeService.fetchShowtimesWithDateAndMovieId(ld, movieId);
                model.addAttribute("date", "isDate");
                model.addAttribute("day1", showtimeList);
                System.out.println("Gotten from showtime");
            }
        } else {
            System.out.println("isNotDate Sent");
            model.addAttribute("date", "isNotDate");
            ArrayList<ArrayList<Showtime>> week = showtimeService.fetchAllInWeekWithMovieId(movieId);
            model.addAttribute("day1", week.get(0));
            model.addAttribute("day2", week.get(1));
            model.addAttribute("day3", week.get(2));
            model.addAttribute("day4", week.get(3));
            model.addAttribute("day5", week.get(4));
            model.addAttribute("day6", week.get(5));
            model.addAttribute("day7", week.get(6));
        }
        System.out.println("Testing a push, seeing if local branch becomes remote. ");
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
