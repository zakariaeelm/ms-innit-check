package com.carrefour.innit.domain;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class Token {
    private String access_token;
    private String scope;
    private String expires_in;
    private String token_type;
}
