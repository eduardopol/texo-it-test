package com.texo.test.config;

import com.opencsv.bean.CsvToBeanBuilder;
import com.texo.test.model.Movie;
import com.texo.test.repository.MovieRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.io.FileReader;
import java.util.List;

@Component
@RequiredArgsConstructor
public class LoadDatabase implements CommandLineRunner {
     
    private final MovieRepository movieRepository;

    @Override
    public void run(String... args) throws Exception {
        String fileName = "src/main/resources/movielist.csv";

        List<Movie> movies = new CsvToBeanBuilder(new FileReader(fileName))
                .withType(Movie.class)
                .withSkipLines(1)
                .withSeparator(';')
                .build()
                .parse();

        movieRepository.saveAll(movies);
    }
 
}