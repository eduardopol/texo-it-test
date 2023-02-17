package com.texo.test.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class WinnerResponse {

    private String producer;
    private Integer interval;
    private Integer previousWin;
    private Integer followingWin;

}
