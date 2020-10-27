package com.carrefour.innit.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import java.util.Map;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class Product {

    private String status;
    private String gtin;
    private String name;
    private String brand;
    private Score score;

    // Diets Scores
    @JsonIgnore
    private Score scoreVeggie;
    @JsonIgnore
    private Score scoreVegan;
    @JsonIgnore
    private Score scorePork;
    @JsonIgnore
    private Score scoreSport;
    @JsonIgnore
    private Score scoreWeight;
    @JsonIgnore
    private Score scoreMed;

    // Avoidables
    Map<String,Score> avoidableScores;

    @JsonIgnore
    private String scope;
    @JsonIgnore
    private Object size;
    @JsonIgnore
    private Object codes;
    @JsonIgnore
    private Object image;
    @JsonIgnore
    private Object classification;
    @JsonIgnore
    private Object nutrients;
    @JsonIgnore
    private Object ingredients;
    @JsonIgnore
    private Object labels;
    @JsonIgnore
    private Object boostFactors;
    @JsonIgnore
    private Object langs;
    @JsonIgnore
    private Object countries;
    @JsonIgnore
    private Object additives;
    @JsonIgnore
    private Object lastMod;
    @JsonIgnore
    private Object stats;
    @JsonIgnore
    private Object suggestions;


}
