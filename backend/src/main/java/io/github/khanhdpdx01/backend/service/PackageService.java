package io.github.khanhdpdx01.backend.service;

import io.github.khanhdpdx01.backend.dto.packing.PackageDto;
import io.github.khanhdpdx01.backend.entity.PackageProduct;
import io.github.khanhdpdx01.backend.entity.Stamp;
import io.github.khanhdpdx01.backend.entity.StampStatus;
import io.github.khanhdpdx01.backend.entity.StampType;
import io.github.khanhdpdx01.backend.mapper.PackageMapper;
import io.github.khanhdpdx01.backend.repository.PackageRepository;
import io.github.khanhdpdx01.backend.repository.StampRepository;
import io.github.khanhdpdx01.backend.util.PaginationAndSortUtil;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class PackageService {
    private static final Logger logger = LoggerFactory.getLogger(PartnerService.class);
    private final StampRepository stampRepository;
    private final PackageRepository packageRepository;

    public PackageService(StampRepository stampRepository, PackageRepository packageRepository) {
        this.stampRepository = stampRepository;
        this.packageRepository = packageRepository;
    }

    public void doPackage(PackageDto packageDto) {
        // create packing
        PackageProduct packageProduct = PackageMapper.INSTANCE.dtoToEntity(packageDto);
        PackageProduct newPackageProduct = packageRepository.save(packageProduct);
        logger.info("Packing success");

        // create product stamps
        int quantity = packageDto.getQuantity();
        int i;
        Stamp stamp = null;
        List<Stamp> stamps = new ArrayList<>();
        for (i = 0; i < quantity; ++i) {
            stamp = new Stamp();
            stamp.setId("ITEM" + (i + 1));
            stamp.setStatus(StampStatus.AVAILABLE);
            stamp.setType(StampType.PRODUCT);
            stamp.setMaxScanTimes(packageDto.getMaxScanTimes());
            stamp.setSku(newPackageProduct);
            stamps.add(stamp);
        }
        stampRepository.saveAll(stamps);

        // create carton stamps
        quantity = quantity / packageDto.getConversionValue();
        stamps.clear();
        for (i = 0; i < quantity; ++i) {
            stamp = new Stamp();
            stamp.setId("CAT" + (i + 1));
            stamp.setStatus(StampStatus.AVAILABLE);
            stamp.setType(StampType.CARTON);
            stamp.setMaxScanTimes(packageDto.getMaxScanTimes());
            stamp.setSku(newPackageProduct);
            stamps.add(stamp);
        }
        stampRepository.saveAll(stamps);

        logger.info("Create stamp success");
    }

    public Page<PackageProduct> getAllWithPaging(int page, int size, String[] sort, String keyword) {
        Pageable pageable = PaginationAndSortUtil.create(page, size, sort);

        Page<PackageProduct> pageRoom;

        if (keyword == null || StringUtils.isBlank(keyword)) {
            pageRoom = packageRepository.findAll(pageable);
        } else {
            pageRoom = packageRepository.search(keyword, pageable);
        }

        return new PageImpl<>(pageRoom.getContent(), pageable, pageRoom.getTotalElements());
    }
}
