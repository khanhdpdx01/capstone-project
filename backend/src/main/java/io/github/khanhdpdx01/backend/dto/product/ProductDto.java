package io.github.khanhdpdx01.backend.dto.product;

import io.github.khanhdpdx01.backend.entity.ProductStatus;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import java.sql.Timestamp;

@Getter
@Setter
public class ProductDto {
    private Long id;
    private String name;
    private String gtinCode;
    private String description;
    @Enumerated(EnumType.ORDINAL)
    private ProductStatus status;
    private Timestamp createdAt;
    private Timestamp updatedAt;
    private Long rawMaterialId;
    private Long createdBy;
}
