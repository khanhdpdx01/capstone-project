package io.github.khanhdpdx01.backend.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.sql.Timestamp;

@Getter
@Setter
@Entity
@Table(name = "series_stamps")
public class Stamp {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String parentId;
    private int maxScanTimes;
    private String code;
    @Enumerated(EnumType.ORDINAL)
    private StampType type;
    @Enumerated(EnumType.ORDINAL)
    private StampStatus status;
    private Timestamp createdAt;

    @ManyToOne
    @JoinColumn(name = "sku")
    private PackageProduct sku;
}
