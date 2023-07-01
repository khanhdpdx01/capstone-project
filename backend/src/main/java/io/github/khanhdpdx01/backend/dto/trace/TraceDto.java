package io.github.khanhdpdx01.backend.dto.trace;

import io.github.khanhdpdx01.backend.dto.diary.DiaryDto;
import io.github.khanhdpdx01.backend.entity.PackageProduct;
import io.github.khanhdpdx01.backend.entity.Stamp;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class TraceDto {
    DiaryDto diaryDto;
    PackageProduct packageProduct;
    Stamp stamp;
}
