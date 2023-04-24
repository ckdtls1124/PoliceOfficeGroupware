package org.spring.p21suck2jo.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor

public class NaverTokenDTO {
    private String access_token;
    private String refresh_token;
    private String scope;
    private String token_type;
    private String expires_in;

}
