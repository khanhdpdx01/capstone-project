package io.github.khanhdpdx01.backend.mapper;

import io.github.khanhdpdx01.backend.dto.diary_detail.DiaryDetailDto;
import io.github.khanhdpdx01.backend.entity.DiaryDetail;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper
public interface DiaryDetailMapper {
    DiaryDetailMapper INSTANCE = Mappers.getMapper(DiaryDetailMapper.class);

    @Mapping(target = "diary.id", source = "diaryDetailDto.diaryId")
    @Mapping(target = "ingredient.id", source = "diaryDetailDto.ingredientId")
    DiaryDetail dtoToEntity(DiaryDetailDto diaryDetailDto);
}
