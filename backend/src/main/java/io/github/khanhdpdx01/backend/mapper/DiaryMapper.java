package io.github.khanhdpdx01.backend.mapper;

import io.github.khanhdpdx01.backend.dto.diary.DiaryDto;
import io.github.khanhdpdx01.backend.entity.Diary;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper
public interface DiaryMapper {
    DiaryMapper INSTANCE = Mappers.getMapper(DiaryMapper.class);

    @Mapping(target = "product.id", source = "diaryDto.productId")
    Diary dtoToEntity(DiaryDto diaryDto);

    @Mapping(target = "productId", source = "diary.product.id")
    DiaryDto entityToDto(Diary diary);
}
