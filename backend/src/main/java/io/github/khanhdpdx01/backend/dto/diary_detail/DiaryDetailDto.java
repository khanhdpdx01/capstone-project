package io.github.khanhdpdx01.backend.dto.diary_detail;

import io.github.khanhdpdx01.backend.entity.DiaryStep;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import java.sql.Timestamp;

@Getter
@Setter
public class DiaryDetailDto {
    @Enumerated(EnumType.ORDINAL)
    private DiaryStep step;
    private String description;
    private Timestamp createdAt;
    private Long diaryId;
    private Long ingredientId;
}
