package io.github.khanhdpdx01.backend.mapper;

import io.github.khanhdpdx01.backend.dto.ingredient.IngredientDto;
import io.github.khanhdpdx01.backend.entity.Ingredient;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface IngredientMapper {
    IngredientMapper INSTANCE = Mappers.getMapper(IngredientMapper.class);

    Ingredient dtoToEntity(IngredientDto ingredientDto);
}
