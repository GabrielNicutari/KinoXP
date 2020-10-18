package com.example.demo.controller;

import com.example.demo.model.Movie;
import com.example.demo.model.Showtime;
import com.example.demo.model.ShowtimeSplit;
import com.example.demo.service.MovieService;
import com.example.demo.service.ShowtimeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;


@Controller
public class ShowtimeController {
    @Autowired
    ShowtimeService showtimeService;

    @Autowired
    MovieService movieService;

    @PostMapping("/saveShowtimeSplit/{movieId}")
    public String saveShowtimeSplit (@PathVariable("movieId") int movieId, ShowtimeSplit showtimeSplit){
        showtimeSplit.setMovieId(movieId);
        LocalDateTime lt = LocalDateTime.of(showtimeSplit.getDate(), showtimeSplit.getTime());
        Showtime converted = new Showtime();
        converted.setMovieId(movieId);
        converted.setRoomId(showtimeSplit.getRoomId());
        converted.setDateTime(lt.plusHours(8)); //TimeDiff gearhost (Mountain Standard Time) -> java (UTC+2)

        // After saving the new showtime, this inserts path to view the date that the showtime belongs to when redirecting
        String dSelectPath = "?dateSelected="+showtimeSplit.getDate();

        showtimeService.addShowtime(converted);

        return "redirect:/showtime/"+converted.getMovieId()+dSelectPath;
    }

    @GetMapping("deleteShowtime/{showtimeId}")
    public String deleteShowtime(@PathVariable("showtimeId") int showtimeId){
        Showtime s = showtimeService.fetchShowtimeWithId(showtimeId);
        int movieId = s.getMovieId();
        showtimeService.deleteShowtime(showtimeId);
        return "redirect:/doubleListTest/"+movieId;
    }

    // Testing Link for future integration with TicketController
    @GetMapping("/testTicket/{showtimeId}")
    public String showtimeTicketTest (@PathVariable("showtimeId") int showtimeId, Model model){
        return "testTicket";
    }

    @GetMapping("/showtime/{movieId}")
    public String doubleTest (@PathVariable("movieId") int movieId, Model model, String dateSelected){

        // Displays showtimes for the next week:
        List<List<Showtime>> lists = showtimeService.fetchWeekSowtimes(movieId);
        model.addAttribute("days", lists);
        Movie m = movieService.getOne(movieId);
        model.addAttribute("title", m.getTitle());

        // Create New Showtime:
        ShowtimeSplit showtimeSplit = new ShowtimeSplit();
        showtimeSplit.setMovieId(movieId);
        // model.addAttribute("timeSelection", showtimeService.generateTimeList(16, 00, 15, 25));
        model.addAttribute("showtimeSplit", showtimeSplit);

        // Gets list of all movies in the db and displays a dropdown menu for picking the movie for showtime display
        List<Movie> movies = movieService.fetchAll();
        model.addAttribute("moviePojo", m);
        model.addAttribute("movies", movies);

        // If employee uses date selection, will display showtimes for selected date at the bottom of the page.
        boolean d = false;
        if(dateSelected != null){
            if(showtimeService.isDate(dateSelected)){
                LocalDate ds = LocalDate.parse(dateSelected);
                List<Showtime> selectedDateShowtime = showtimeService.fetchShowtimesWithDateAndMovieId(ds, movieId);
                if(selectedDateShowtime.size() > 0){
                    model.addAttribute("d", true);
                    model.addAttribute("date", selectedDateShowtime);
                }
            } else {
                model.addAttribute("d", false);
            }
        } else {
            model.addAttribute("d", false);
        }
        return "showtime";
    }

    @PostMapping("/anotherMovie")
    public String changeMovie(Movie moviePojo){
        return "redirect:/showtime/"+moviePojo.getId();
    }



}
