package io.github.khanhdpdx01.backend.mapper;

import io.github.khanhdpdx01.backend.dto.packing.PackageDto;
import io.github.khanhdpdx01.backend.entity.PackageProduct;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper
public interface PackageMapper {
    PackageMapper INSTANCE = Mappers.getMapper(PackageMapper.class);

    @Mapping(target = "diary.id", source = "packageDto.diaryId")
    PackageProduct dtoToEntity(PackageDto packageDto);
}
