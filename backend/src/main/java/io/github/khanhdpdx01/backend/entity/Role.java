package io.github.khanhdpdx01.backend.entity;

import java.util.HashMap;
import java.util.Map;

public enum Role {
    ADMIN,
    SUPER_ADMIN;

    public static Map<Integer, String> getAllRoles() {
        Map<Integer, String> ranks = new HashMap<>();
        ranks.put(1, "ROLE_ADMIN");
        ranks.put(2, "ROLE_SUPERADMIN");
        return ranks;
    }
}
