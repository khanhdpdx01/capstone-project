package io.github.khanhdpdx01.backend.dto.ingredient;

import io.github.khanhdpdx01.backend.entity.IngredientType;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;

@Getter
@Setter
public class IngredientDto {
    private Long id;
    private String name;
    @Enumerated(EnumType.ORDINAL)
    private IngredientType type;
    private String gtinCode;
    private String description;
    private String certificatePath;
    private String imagePath;
    private String traceUrl;
}
