package com.carrefour.innit.domain;

import com.fasterxml.jackson.annotation.JsonAlias;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class Score {

    private double search;
    @JsonAlias("default")
    private double defaultScore;
    private double fit;
    private double innit;
    private Rating rating;
    private Nutri nutri;
}
