package io.github.khanhdpdx01.backend.mapper;

import io.github.khanhdpdx01.backend.dto.raw_material.RawMaterialDto;
import io.github.khanhdpdx01.backend.entity.RawMaterial;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper
public interface RawMaterialMapper {
    RawMaterialMapper INSTANCE = Mappers.getMapper(RawMaterialMapper.class);

    @Mapping(target = "representative.id", source = "rawMaterialDto.representativeId")
    RawMaterial dtoToEntity(RawMaterialDto rawMaterialDto);
}
