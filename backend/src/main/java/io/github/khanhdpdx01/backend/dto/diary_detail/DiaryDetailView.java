package io.github.khanhdpdx01.backend.dto.diary_detail;

import io.github.khanhdpdx01.backend.entity.DiaryStep;

import java.sql.Timestamp;

public interface DiaryDetailView {
    Long getId();

    Long getDiaryId();

    String getName();

    DiaryStep getStep();

    String getDescription();

    Timestamp getCreatedAt();

    String getFullName();
}