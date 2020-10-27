package com.carrefour.innit.domain;

import com.fasterxml.jackson.annotation.JsonAlias;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class Nutri {

    @JsonAlias("package")
    private UniPackage packaging;
    private Computed computed;
}
