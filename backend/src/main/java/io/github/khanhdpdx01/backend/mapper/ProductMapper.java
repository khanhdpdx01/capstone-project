package io.github.khanhdpdx01.backend.mapper;

import io.github.khanhdpdx01.backend.dto.product.ProductDto;
import io.github.khanhdpdx01.backend.entity.Product;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper
public interface ProductMapper {
    ProductMapper INSTANCE = Mappers.getMapper(ProductMapper.class);

    @Mapping(target = "createdBy.id", source = "productDto.createdBy")
    @Mapping(target = "rawMaterial.id", source = "productDto.rawMaterialId")
    Product dtoToEntity(ProductDto productDto);
}
