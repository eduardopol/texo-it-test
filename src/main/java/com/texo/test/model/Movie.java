package com.texo.test.model;

import com.opencsv.bean.CsvBindByPosition;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Movie {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "movie_seq")
    @SequenceGenerator(name = "movie_seq", sequenceName = "movie_seq", allocationSize = 1)
    private Long id;
    @CsvBindByPosition(position = 1)
    private String title;
    @CsvBindByPosition(position = 0)
    private Integer year;
    @CsvBindByPosition(position = 2)
    private String studios;
    @CsvBindByPosition(position = 3)
    private String producers;
    @CsvBindByPosition(position = 4)
    private String winner;

}
