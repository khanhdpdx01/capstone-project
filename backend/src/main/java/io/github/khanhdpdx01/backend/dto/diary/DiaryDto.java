package io.github.khanhdpdx01.backend.dto.diary;

import io.github.khanhdpdx01.backend.entity.DiaryStatus;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import java.sql.Timestamp;

@Getter
@Setter
public class DiaryDto {
    private String name;
    @Enumerated(EnumType.ORDINAL)
    private DiaryStatus status;
    private Timestamp createdAt;
    private Long productId;
}
