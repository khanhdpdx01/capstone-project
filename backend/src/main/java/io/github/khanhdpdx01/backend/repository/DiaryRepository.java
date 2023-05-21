package io.github.khanhdpdx01.backend.repository;

import io.github.khanhdpdx01.backend.entity.Diary;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface DiaryRepository extends JpaRepository<Diary, Long> {
    @Query("SELECT d from Diary d " +
            "where (:userId is NULL OR d.createdBy.id = :userId ) " +
            "and d.name LIKE %:keyword% OR d.product.name LIKE %:keyword% ")
    Page<Diary> search(@Param("userId") Long userId, @Param("keyword") String keyword, Pageable pageable);

    @Query("SELECT d from Diary d " +
            "where d.createdBy.id = :userId ")
    Page<Diary> findAll(@Param("userId") Long userId, Pageable pageable);
}
