package io.github.khanhdpdx01.backend.dto.user;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CreateUserDTO {
    private String fullName;
    private String username;
    private String password;
}
