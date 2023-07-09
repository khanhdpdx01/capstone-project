package io.github.khanhdpdx01.backend.dto.packing;

import lombok.Getter;
import lombok.Setter;

import java.sql.Timestamp;

@Getter
@Setter
public class PackageDto {
    private String sku;
    private String productName;
    private Timestamp packageDate;
    private Timestamp expiryDate;
    private int quantity;
    private double weight;
    private String basicUnit;
    private String conversionUnit;
    private int conversionValue;
    private String address;
    private String createdBy;
    private int maxScanTimes;
    private Long diaryId;
}
