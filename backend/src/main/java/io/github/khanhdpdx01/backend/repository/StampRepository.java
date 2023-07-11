package io.github.khanhdpdx01.backend.repository;

import io.github.khanhdpdx01.backend.dto.stamp.CurrentValue;
import io.github.khanhdpdx01.backend.entity.Stamp;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface StampRepository extends JpaRepository<Stamp, String> {
    @Query("SELECT st from Stamp st where st.sku.sku = :sku")
    Page<Stamp> findAllBySku(String sku, Pageable pageable);

    @Query(value = "SELECT AUTO_INCREMENT as currentId " +
            "FROM INFORMATION_SCHEMA.TABLES " +
            "WHERE TABLE_SCHEMA = 'demo' " +
            "AND TABLE_NAME = 'series_stamps'" , nativeQuery = true)
    CurrentValue getCurrentId();
}
