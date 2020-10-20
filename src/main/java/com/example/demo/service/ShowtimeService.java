package com.example.demo.service;

import com.example.demo.model.Showtime;
import com.example.demo.repository.ShowtimeRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeParseException;
import java.util.*;

@Service
public class ShowtimeService {
    @Autowired
    ShowtimeRepo showtimeRepo;

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

    public List<Showtime> fetchShowtimesWithDateAndMovieId (LocalDate date, int movieId){
        return showtimeRepo.fetchShowtimesWithDateAndMovieId(date, movieId);
    }
    public List<Showtime> fetchShowtimeFuture(int movieId){
        return showtimeRepo.fetchShowtimeFuture(movieId);
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

    public boolean isDate (String date) {
        boolean dates = true;
        try{
            LocalDate sd = LocalDate.parse(date);
        } catch (DateTimeParseException e) {
            dates = false;
        }
        return dates;
    }

    //For Movies
    public LinkedHashMap<LocalDate, TreeSet<LocalTime>> fetchDatesAndTimesForMovie (int movieId){
        LinkedHashMap<LocalDate, TreeSet<LocalTime>> map = new LinkedHashMap<>();

        List<Showtime> all = showtimeRepo.fetchAllWithMovieId(movieId);

        for(int i = 0; i < all.size(); i++){
            LocalDate date = all.get(i).getDateTime().toLocalDate();

            if(map.containsKey(date)) {
                continue;
            }

            TreeSet<LocalTime> times = new TreeSet<>();
            for(int j = 0; j < all.size(); j++) {
                if(date.equals(all.get(j).getDateTime().toLocalDate())){
                    LocalTime time = all.get(j).getDateTime().toLocalTime();
                    times.add(time);
                }
            }
            map.put(date, times);
        }
        map = sortMap(map); //ascending
        return map;
    }

    private LinkedHashMap<LocalDate, TreeSet<LocalTime>> sortMap(HashMap<LocalDate, TreeSet<LocalTime>> map) {
        LinkedHashMap<LocalDate, TreeSet<LocalTime>> sortedMap = new LinkedHashMap<>();

        map.entrySet()
                .stream()
                .sorted(Map.Entry.comparingByKey())
                .forEachOrdered(x -> sortedMap.put(x.getKey(), x.getValue()));
        return sortedMap;
    }

    public int findIdBasedOnFields(int movieId, LocalDateTime dateTime) {
        return showtimeRepo.findIdBasedOnFields(movieId, dateTime);
    }
}
