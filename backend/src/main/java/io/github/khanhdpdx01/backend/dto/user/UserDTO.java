package io.github.khanhdpdx01.backend.dto.user;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.Accessors;

@Getter
@Setter
@Accessors(chain = true)
@ToString
public class UserDTO {
    private long userId;
    private String fullName;
    private String username;
    private String role;
    private String address;
    private String phoneNumber;
    private String email;
    private String website;
    private String description;
    private boolean isActive;
}
