package io.github.khanhdpdx01.backend.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.sql.Timestamp;

@Getter
@Setter
@Entity
@Table(name = "raw_materials")
public class RawMaterial {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String prodUnitCode;
    private String address;
    private String imagePath;
    private String certificatePath;
    private double area;
    @Enumerated(EnumType.ORDINAL)
    private AreaUnit areaUnit;
    private double output;
    @Enumerated(EnumType.ORDINAL)
    private OutputUnit outputUnit;
    private Timestamp createdAt;
    private Timestamp updatedAt;

    @ManyToOne
    @JoinColumn(name = "representative_id")
    private User representative;

    @ManyToOne
    @JoinColumn(name = "updated_by")
    private User updatedBy;
}
