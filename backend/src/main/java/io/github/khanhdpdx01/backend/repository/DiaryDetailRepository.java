package io.github.khanhdpdx01.backend.repository;

import io.github.khanhdpdx01.backend.entity.DiaryDetail;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface DiaryDetailRepository extends JpaRepository<DiaryDetail, Long> {

    @Query("SELECT d from DiaryDetail d where d.diary.id = :diaryId")
    List<DiaryDetail> findAllByDiaryId(@Param("diaryId") Long diaryId);
}
