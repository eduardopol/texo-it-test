package com.texo.test.controller;

import com.texo.test.model.MinMaxIntervalWinnersResponse;
import com.texo.test.service.MovieService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = ProducerController.URL_BASE)
@RequiredArgsConstructor
public class ProducerController {

    public static final String URL_BASE = "texo/producers";

    private final MovieService movieService;

    @GetMapping("/biggest-smallest-winning-interval")
    public ResponseEntity<MinMaxIntervalWinnersResponse> getAllMovies() {
        return ResponseEntity.ok(movieService.getBiggestAndSmallest());
    }


}
