package io.github.khanhdpdx01.backend.mapper;

import io.github.khanhdpdx01.backend.dto.partner.PartnerDto;
import io.github.khanhdpdx01.backend.entity.Partner;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface PartnerMapper {
    PartnerMapper INSTANCE = Mappers.getMapper(PartnerMapper.class);

    Partner dtoToEntity(PartnerDto partnerDto);

}
