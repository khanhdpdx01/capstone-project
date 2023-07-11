package io.github.khanhdpdx01.backend.service;

import io.github.khanhdpdx01.backend.dto.packing.PackageDto;
import io.github.khanhdpdx01.backend.entity.*;
import io.github.khanhdpdx01.backend.mapper.PackageMapper;
import io.github.khanhdpdx01.backend.repository.KdmConstRepository;
import io.github.khanhdpdx01.backend.repository.PackageRepository;
import io.github.khanhdpdx01.backend.repository.StampRepository;
import io.github.khanhdpdx01.backend.util.FileUtil;
import io.github.khanhdpdx01.backend.util.PaginationAndSortUtil;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class PackageService {
    private static final Logger logger = LoggerFactory.getLogger(PartnerService.class);
    private final StampRepository stampRepository;
    private final PackageRepository packageRepository;
    private final KdmConstRepository kdmConstRepository;

    @Value("${storage.file.directory}")
    private String fileDirectory;

    public PackageService(StampRepository stampRepository, PackageRepository packageRepository, KdmConstRepository kdmConstRepository) {
        this.stampRepository = stampRepository;
        this.packageRepository = packageRepository;
        this.kdmConstRepository = kdmConstRepository;
    }

    public void doPackage(PackageDto packageDto, List<MultipartFile> images) {
        // create packing
        PackageProduct packageProduct = PackageMapper.INSTANCE.dtoToEntity(packageDto);

        if (images.size() > 0) {
            List<String> imgListAsStr =
                    images
                            .stream()
                            .map(image -> UUID.randomUUID().toString())
                            .collect(Collectors.toList());

            int i = 0;
            for (; i < imgListAsStr.size(); ++i) {
                FileUtil.save(Paths.get(fileDirectory, imgListAsStr.get(i)), images.get(i));
            }

            packageProduct.setImagePath(imgListAsStr.stream().collect(Collectors.joining()));
        }

        PackageProduct newPackageProduct = packageRepository.save(packageProduct);
        logger.info("Packing success");

        // create product stamps
        int quantity = packageDto.getQuantity();
        KdmConst kdmconst = kdmConstRepository.findByName();
        int curentValue = kdmconst.getValue();
        int i;

        Stamp stamp = null;
        List<Stamp> stamps = new ArrayList<>();
        for (i = 0; i < quantity; ++i) {
            stamp = new Stamp();
            stamp.setStatus(StampStatus.AVAILABLE);
            stamp.setType(StampType.PRODUCT);
            stamp.setMaxScanTimes(packageDto.getMaxScanTimes());
            stamp.setSku(newPackageProduct);
            stamp.setCode("VN" + (curentValue + i));
            stamps.add(stamp);
        }
        stampRepository.saveAll(stamps);
        kdmconst.setValue(curentValue + quantity);
        kdmConstRepository.save(kdmconst);

        // create carton stamps
//        quantity = quantity / packageDto.getConversionValue();
//        stamps.clear();
//        for (i = 0; i < quantity; ++i) {
//            stamp = new Stamp();
//            stamp.setId("CAT" + (i + 1));
//            stamp.setStatus(StampStatus.AVAILABLE);
//            stamp.setType(StampType.CARTON);
//            stamp.setMaxScanTimes(packageDto.getMaxScanTimes());
//            stamp.setSku(newPackageProduct);
//            stamps.add(stamp);
//        }
//        stampRepository.saveAll(stamps);

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

    public PackageProduct detail(String sku) {
        PackageProduct packageProduct = packageRepository.findById(sku)
                .orElseThrow(() -> new RuntimeException("Package product not found"));

        return packageProduct;
    }

    public Page<Stamp> getListStampBySku(String sku, int page, int size, String[] sort, String keyword) {
        Pageable pageable = PaginationAndSortUtil.create(page, size, sort);

        Page<Stamp> pageRoom = null;

        if (keyword == null || StringUtils.isBlank(keyword)) {
            pageRoom = stampRepository.findAllBySku(sku, pageable);
        }

        return new PageImpl<>(pageRoom.getContent(), pageable, pageRoom.getTotalElements());
    }
}
