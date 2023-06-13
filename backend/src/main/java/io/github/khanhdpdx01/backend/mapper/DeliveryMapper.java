package io.github.khanhdpdx01.backend.mapper;

import io.github.khanhdpdx01.backend.dto.delivery.DeliveryDto;
import io.github.khanhdpdx01.backend.entity.Delivery;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface DeliveryMapper {
    DeliveryMapper INSTANCE = Mappers.getMapper(DeliveryMapper.class);

    Delivery dtoToEntity(DeliveryDto deliveryDto);
}
