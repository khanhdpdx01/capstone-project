package io.github.khanhdpdx01.backend.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.sql.Timestamp;


@Getter
@Setter
@Entity
@Table(name = "package")
public class PackageProduct {
    @Id
    private String sku;
    private String productName;
    private Timestamp packageDate;
    private Timestamp expiryDate;
    private int quantity;
    private double weight;
    private String basicUnit;
    private String conversionUnit;
    private String conversionValue;
    private String address;
    private String createdBy;
    private String imagePath;

    @ManyToOne
    @JoinColumn(name = "product_id")
    private Product product;
}
