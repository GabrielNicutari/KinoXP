package com.example.demo.service;

import com.example.demo.model.Showtime;
import com.example.demo.repository.ShowtimeRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Service;

import java.sql.Time;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.List;

@Service
public class ShowtimeService {
    @Autowired
    ShowtimeRepo showtimeRepo;

    // Fetches all showtimes within the next 7 days and has the correct movieId. ArrayList per day for thymeleaf.
    public ArrayList<ArrayList<Showtime>> fetchAllInWeekWithMovieId (int movieId){
        List<Showtime> unformatted = showtimeRepo.fetchAllInWeekWithMovieId(movieId);

        ArrayList<Showtime> day1 = new ArrayList<>();
        ArrayList<Showtime> day2 = new ArrayList<>();
        ArrayList<Showtime> day3 = new ArrayList<>();
        ArrayList<Showtime> day4 = new ArrayList<>();
        ArrayList<Showtime> day5 = new ArrayList<>();
        ArrayList<Showtime> day6 = new ArrayList<>();
        ArrayList<Showtime> day7 = new ArrayList<>();
        ArrayList<ArrayList<Showtime>> week = new ArrayList<ArrayList<Showtime>>();
        for(int i = 0; i < unformatted.size(); i++){
            LocalDate dateNow = LocalDate.now();
            LocalDate fromList = unformatted.get(i).getDateTime().toLocalDate();
            if(fromList.equals(dateNow)){
                day1.add(unformatted.get(i));
                System.out.println(unformatted.get(i).getDateTime());
            } else if (fromList.equals(dateNow.plusDays(1))){
                day2.add(unformatted.get(i));
            } else if (fromList.equals(dateNow.plusDays(2))){
                day3.add(unformatted.get(i));
            } else if (fromList.equals(dateNow.plusDays(3))){
                day4.add(unformatted.get(i));
            } else if (fromList.equals(dateNow.plusDays(4))){
                day5.add(unformatted.get(i));
            } else if (fromList.equals(dateNow.plusDays(5))){
                day6.add(unformatted.get(i));
            } else if (fromList.equals(dateNow.plusDays(6))){
                day7.add(unformatted.get(i));
            }
        }
        week.add(day1);
        week.add(day2);
        week.add(day3);
        week.add(day4);
        week.add(day5);
        week.add(day6);
        week.add(day7);
        return week;
    }

    public List<Showtime> fetchShowtimesWithDateAndMovieId (LocalDate date, int movieId){
        return showtimeRepo.fetchShowtimesWithDateAndMovieId(date, movieId);
    }

    public boolean addShowtime(Showtime showtime){
        return showtimeRepo.addShowtime(showtime);
    }

    public boolean deleteShowtime(int showtimeId){
        return showtimeRepo.deleteShowtime(showtimeId);
    }

    public Showtime fetchShowtimeWithId(int showtimeId){
        return showtimeRepo.fetchShowtimeWithId(showtimeId);
    }

    public List<Showtime> fetchAllShowtimes(){
        return showtimeRepo.fetchAllShowtimes();
    }
    public List<Showtime> fetchAllWithMovieId(int movieId){
        return showtimeRepo.fetchAllWithMovieId(movieId);
    }

    // Alternative for using type = time in the HTML/THYMELEAF code. Passing this list and another for value to match.
    public ArrayList<LocalTime> generateTimeList(int startHour, int startMinute, int intervalMinutes, int intervalTimes){
        ArrayList<LocalTime> showtimes = new ArrayList<>();
        LocalTime t = LocalTime.of(startHour, startMinute);
        for(int i = 0; i < intervalTimes; i++){
            showtimes.add(t);
            t = t.plusMinutes(intervalMinutes);
        }
        return showtimes;
    }
    public boolean isDate (String date) {
        boolean dates = true;
        try{
            LocalDate sd = LocalDate.parse(date);
        } catch (DateTimeParseException e) {
            dates = false;
        }
        return dates;
    }
}
