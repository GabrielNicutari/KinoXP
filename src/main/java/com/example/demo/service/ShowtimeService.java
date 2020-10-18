package com.example.demo.service;

import com.example.demo.model.Showtime;
import com.example.demo.repository.ShowtimeRepo;
import org.apache.tomcat.jni.Local;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Service;

import java.sql.Time;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeParseException;
import java.util.*;

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
    /*
    public List<List<Showtime>> doubleTest (int movieId){
        List<List<Showtime>> doubleList = new ArrayList<List<Showtime>>();
        List<Showtime> week = showtimeRepo.fetchAllInWeekWithMovieId(movieId);
        LocalDate ld = LocalDate.now();
        List<Showtime> today = new ArrayList<>();
        for(int i = 0; i < week.size(); i++){
            if(week.get(i).getDateTime().toLocalDate().equals(ld)){

            }
        }
        for(int i = 0; i < week.size(); i++){
            LocalDate listDate = week.get(i).getDateTime().toLocalDate(); // Date in the week from mysql
            for(int j = 0; j < doubleList.size(); j++){
                List<Showtime> list = doubleList.get(j);
                for(int k = 0; k < list.size(); k++){
                    if(list.get(k).getDateTime().toLocalDate().equals(listDate)){ // Equal to the list within doubleList dates
                        doubleList.get(j).add(week.get(i));
                    }
                }
            }
        }
        return ;
    }
     */
    public List<List<Showtime>> fetchWeekSowtimes (int movieId){
        List<List<Showtime>> weekShowtimes = new ArrayList<>();
        LocalDate today = LocalDate.now();
        for(int i = 0; i < 7; i++){
            LocalDate date = today.plusDays(i);
            List<Showtime> dateShowtimes = showtimeRepo.fetchShowtimesWithDateAndMovieId(date, movieId);
            weekShowtimes.add(dateShowtimes);
        }
        return weekShowtimes;
    }

    /// NEEDS FIX 
    public List<Showtime> fetchDateMovieGabriel (int movieId){
        HashMap<LocalDate, List<LocalTime>> map = new HashMap<>();
        List<Showtime> all = showtimeRepo.fetchAllWithMovieId(movieId);
        HashSet<LocalDate> set = new HashSet<>();
        for(int i = 0; i < all.size(); i++){
            LocalDate date = all.get(i).getDateTime().toLocalDate();
            set.add(date);
        }
        for (LocalDate d : set) {
            LocalDate key = d;
            List<LocalTime> times = new ArrayList<>();
            for(int i = 0; i < all.size(); i++){
                if(key.equals(all.get(i).getDateTime().toLocalDate())){
                    times.add(all.get(i).getDateTime().toLocalTime());
                }
            }
        }
        return all;
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
