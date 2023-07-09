package io.github.khanhdpdx01.backend.service;

import io.github.khanhdpdx01.backend.dto.diary.DiaryDto;
import io.github.khanhdpdx01.backend.dto.trace.TraceDto;
import io.github.khanhdpdx01.backend.entity.Diary;
import io.github.khanhdpdx01.backend.entity.DiaryDetail;
import io.github.khanhdpdx01.backend.entity.PackageProduct;
import io.github.khanhdpdx01.backend.entity.Stamp;
import io.github.khanhdpdx01.backend.mapper.DiaryMapper;
import io.github.khanhdpdx01.backend.repository.DiaryDetailRepository;
import io.github.khanhdpdx01.backend.repository.DiaryRepository;
import io.github.khanhdpdx01.backend.repository.PackageRepository;
import io.github.khanhdpdx01.backend.repository.StampRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TraceService {
    private static final Logger logger = LoggerFactory.getLogger(PartnerService.class);
    private final StampRepository stampRepository;
    private final PackageRepository packageRepository;
    private final DiaryRepository diaryRepository;
    private final DiaryDetailRepository diaryDetailRepository;

    public TraceService(StampRepository stampRepository, PackageRepository packageRepository, DiaryRepository diaryRepository, DiaryDetailRepository diaryDetailRepository) {
        this.stampRepository = stampRepository;
        this.packageRepository = packageRepository;
        this.diaryRepository = diaryRepository;
        this.diaryDetailRepository = diaryDetailRepository;
    }

    public TraceDto trace(String id) {
        Stamp stamp = stampRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Stamp not found"));

        String sku = stamp.getSku().getSku();

        PackageProduct packageProduct = packageRepository.findById(sku)
                .orElseThrow(() -> new RuntimeException("Package product not found"));

        Long diaryId = packageProduct.getDiary().getId();

        Diary diary = diaryRepository.findById(diaryId)
                .orElseThrow(() -> new RuntimeException("Diary not found"));

        List<DiaryDetail> detailList = diaryDetailRepository.findAllByDiaryId(diary.getId());
        DiaryDto diaryDto = DiaryMapper.INSTANCE.entityToDto(diary);
        diaryDto.setDiaryDetails(detailList);

        TraceDto traceDto = new TraceDto();
        traceDto.setDiaryDto(diaryDto);
        traceDto.setStamp(stamp);
        traceDto.setPackageProduct(packageProduct);

        return traceDto;
    }
}
