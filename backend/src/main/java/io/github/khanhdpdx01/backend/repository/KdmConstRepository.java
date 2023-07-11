package io.github.khanhdpdx01.backend.repository;

import io.github.khanhdpdx01.backend.entity.KdmConst;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface KdmConstRepository extends JpaRepository<KdmConst, String> {
    @Query("SELECT kdm from KdmConst kdm where kdm.name = 'series_stamps'")
    KdmConst findByName();
}
