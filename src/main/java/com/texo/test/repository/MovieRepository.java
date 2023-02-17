package com.texo.test.repository;

import com.texo.test.model.Movie;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MovieRepository extends JpaRepository<Movie, Long> {

    List<Movie> findMoviesByWinnerOrderByYearAsc(String winner);

}
