package io.github.khanhdpdx01.backend.repository;

import io.github.khanhdpdx01.backend.entity.Stamp;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StampRepository extends JpaRepository<Stamp, String> {
}
