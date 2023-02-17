package com.texo.test.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class MinMaxIntervalWinnersResponse {

    private List<WinnerResponse> min;
    private List<WinnerResponse> max;

}
