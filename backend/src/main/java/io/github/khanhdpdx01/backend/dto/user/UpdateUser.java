package io.github.khanhdpdx01.backend.dto.user;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

@Getter
@Setter
@Accessors
public class UpdateUser {
    private long id;
    private boolean isActive;
}
