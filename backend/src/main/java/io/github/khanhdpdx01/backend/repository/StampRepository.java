package io.github.khanhdpdx01.backend.repository;

import io.github.khanhdpdx01.backend.entity.Stamp;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface StampRepository extends JpaRepository<Stamp, String> {
    @Query("SELECT st from Stamp st where st.sku.sku = :sku")
    Page<Stamp> findAllBySku(String sku, Pageable pageable);
}
