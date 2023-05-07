package io.github.khanhdpdx01.backend.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.sql.Timestamp;

@Getter
@Setter
@Entity
@Table(name = "ingredients")
public class Ingredient {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    @Enumerated(EnumType.ORDINAL)
    private IngredientType type;
    private String gtinCode;
    private String description;
    private String certificatePath;
    private Timestamp createdAt;
    private String imagePath;
    private String traceUrl;
    @Enumerated(EnumType.ORDINAL)
    private IngredientStatus status;
    private Timestamp updatedAt;

    @ManyToOne
    @JoinColumn(name = "partner_id")
    private Partner partner;

    @ManyToOne
    @JoinColumn(name = "updated_by")
    private User updatedBy;
}