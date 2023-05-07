package io.github.khanhdpdx01.backend.repository;

import io.github.khanhdpdx01.backend.entity.RawMaterial;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface RawMaterialRepository extends JpaRepository<RawMaterial, Long> {
    @Query("SELECT rm from RawMaterial rm where rm.name LIKE %:keyword% OR rm.prodUnitCode LIKE %:keyword% ")
    Page<RawMaterial> search(@Param("keyword") String keyword, Pageable pageable);
}
