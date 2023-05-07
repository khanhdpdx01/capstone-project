package io.github.khanhdpdx01.backend.dto.raw_material;

import io.github.khanhdpdx01.backend.entity.AreaUnit;
import io.github.khanhdpdx01.backend.entity.OutputUnit;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import java.sql.Timestamp;

@Getter
@Setter
public class RawMaterialDto {
    private Long id;
    private String name;
    private String prodUnitCode;
    private String address;
    private double area;
    @Enumerated(EnumType.ORDINAL)
    private AreaUnit areaUnit;
    private double output;
    @Enumerated(EnumType.ORDINAL)
    private OutputUnit outputUnit;
    private Timestamp createdAt;
    private Timestamp updatedAt;
    private Long representativeId;
}
