package io.github.khanhdpdx01.backend.dto.diary_detail;

import io.github.khanhdpdx01.backend.entity.DiaryStep;
import lombok.Getter;
import lombok.Setter;

import java.sql.Timestamp;

@Getter
@Setter
public class DiaryDetailBlc {
    private Long id;
    private Long diaryId;
    private String name;
    private DiaryStep step;
    private String description;
    private Timestamp createdAt;
    private String fullName;
}
