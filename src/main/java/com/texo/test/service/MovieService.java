package com.texo.test.service;

import com.texo.test.model.MinMaxIntervalWinnersResponse;
import com.texo.test.model.Movie;
import com.texo.test.model.WinnerResponse;
import com.texo.test.repository.MovieRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
@RequiredArgsConstructor
public class MovieService {

    private final MovieRepository movieRepository;

    public MinMaxIntervalWinnersResponse getAllMovies() {
        List<Movie> winners = movieRepository.findMoviesByWinnerOrderByYearAsc("yes");

        HashMap<String, List<Integer>> winningProducers = new HashMap<>();

        for (Movie winner : winners) {
            String producers = winner.getProducers().replace(", and", ",").replace(" and", ",");

            for (String producer : producers.split(", ")) {
                if (winningProducers.containsKey(producer)) {
                    List<Integer> winningYears = winningProducers.get(producer);
                    winningYears.add(winner.getYear());
                } else {
                    List<Integer> winningYears = new ArrayList<>();
                    winningYears.add(winner.getYear());
                    winningProducers.put(producer, winningYears);
                }
            }
        }

        List<WinnerResponse> min = new ArrayList<>();
        List<WinnerResponse> max = new ArrayList<>();

        Integer minWinner = Integer.MAX_VALUE;
        Integer maxWinner = Integer.MIN_VALUE;

        for (Map.Entry<String, List<Integer>> entry : winningProducers.entrySet()) {
            if (entry.getValue().size() == 1) {
                continue;
            }

            String producer = entry.getKey();
            List<Integer> winningYears = entry.getValue();

            Integer minYear = Integer.MAX_VALUE;
            Integer maxYear = Integer.MIN_VALUE;

            Integer previousYear = 0;
            Integer difference;

            for (Integer year : winningYears) {
                if (year < minYear) {
                    minYear = year;
                }
                if (year > maxYear) {
                    maxYear = year;
                }

                difference = year - previousYear;
                if (previousYear != 0 && difference == minWinner) {
                    min.add(new WinnerResponse(producer, difference, previousYear, year));
                }

                if (previousYear != 0 && difference < minWinner) {
                    minWinner = difference;

                    min = new ArrayList<>();
                    min.add(new WinnerResponse(producer, difference, previousYear, year));
                }

                previousYear = year;
            }

            difference = maxYear - minYear;
            if (difference == maxWinner) {
                max.add(new WinnerResponse(producer, difference, minYear, maxYear));
            }
            if (difference > maxWinner) {
                maxWinner = difference;

                max = new ArrayList<>();
                max.add(new WinnerResponse(producer, difference, minYear, maxYear));
            }


        }

        return new MinMaxIntervalWinnersResponse(min, max);
    }

}
