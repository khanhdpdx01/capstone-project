package io.github.khanhdpdx01.backend.dto.response;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class JwtResponse {
    private String role;
    private String username;
    private String accessToken;
}
