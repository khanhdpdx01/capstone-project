package io.github.khanhdpdx01.backend.service;

import io.github.khanhdpdx01.backend.dto.diary.DiaryDto;
import io.github.khanhdpdx01.backend.entity.*;
import io.github.khanhdpdx01.backend.exception.ApiRequestException;
import io.github.khanhdpdx01.backend.mapper.DiaryMapper;
import io.github.khanhdpdx01.backend.repository.DiaryDetailRepository;
import io.github.khanhdpdx01.backend.repository.DiaryRepository;
import io.github.khanhdpdx01.backend.repository.ProductRepository;
import io.github.khanhdpdx01.backend.security.AuthenticationFacade;
import io.github.khanhdpdx01.backend.util.PaginationAndSortUtil;
import org.apache.commons.lang3.StringUtils;
import org.hyperledger.fabric.gateway.Transaction;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

import static io.github.khanhdpdx01.backend.common.AppConstant.ADMIN;

@Service
@PropertySource("classpath:messages.properties")
public class DiaryService {
    private static final Logger logger = LoggerFactory.getLogger(PartnerService.class);
    private final AuthenticationFacade authenticationFacade;
    private final DiaryRepository diaryRepository;
    private final DiaryDetailRepository diaryDetailRepository;
    private final ProductRepository productRepository;

    private final ChaincodeService chaincodeService;

    @Value("${diary.not-found}")
    private String DIARY_NOT_FOUND;

    public DiaryService(AuthenticationFacade authenticationFacade, DiaryRepository diaryRepository, DiaryDetailRepository diaryDetailRepository, ProductRepository productRepository, ChaincodeService chaincodeService) {
        this.authenticationFacade = authenticationFacade;
        this.diaryRepository = diaryRepository;
        this.diaryDetailRepository = diaryDetailRepository;
        this.productRepository = productRepository;
        this.chaincodeService = chaincodeService;
    }

    public Diary create(DiaryDto diaryDto) {
        Diary diary = DiaryMapper.INSTANCE.dtoToEntity(diaryDto);
        User createdBy = new User();
        createdBy.setId(authenticationFacade.getUserId());
        diary.setCreatedBy(createdBy);

        Diary newDiary = diaryRepository.save(diary);
        logger.info("Create diary success: " + newDiary.getId());

        // get product name
        Product product = productRepository.findById(diary.getProduct().getId())
                .orElseThrow(() -> new ApiRequestException("Product not found"));

        // upload to blockchain
        try {
            Transaction transaction = chaincodeService.createTransaction("CreateDiary");
            byte[] res = transaction.submit(Long.toString(newDiary.getId()),
                    newDiary.getName(),
                    product.getName(),
                    newDiary.getStatus().name());

            String transactionId = transaction.getTransactionId();
            diaryRepository.updateTransactionId(newDiary.getId(), transactionId);
            logger.info("Updating transaction is success: " + newDiary.getId());
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        return newDiary;
    }

    public Page<Diary> getAllWithPaging(int page, int size, String[] sort, String keyword) {
        Pageable pageable = PaginationAndSortUtil.create(page, size, sort);
        Long userId = authenticationFacade.getUserId();
        String role = authenticationFacade.getRole();

        Page<Diary> pageRoom;

        if (keyword == null || StringUtils.isBlank(keyword)) {
            if (ADMIN.equals(role)) {
                pageRoom = diaryRepository.findAll(pageable);
            } else {
                pageRoom = diaryRepository.findAll(userId, pageable);
            }
        } else {
            if (ADMIN.equals(role)) {
                pageRoom = diaryRepository.search(null, keyword, pageable);
            } else {
                pageRoom = diaryRepository.search(userId, keyword, pageable);
            }
        }

        return new PageImpl<>(pageRoom.getContent(), pageable, pageRoom.getTotalElements());
    }

    public DiaryDto getDetail(Long id) {
        Diary diary = diaryRepository.findById(id)
                .orElseThrow(() -> new ApiRequestException(DIARY_NOT_FOUND));

        List<DiaryDetail> detailList = diaryDetailRepository.findAllByDiaryId(id);
        DiaryDto dto = DiaryMapper.INSTANCE.entityToDto(diary);
        dto.setDiaryDetails(detailList);

        return dto;
    }

    public DiaryStatus getDiaryStatus(Long id) {
        Diary diary = diaryRepository.findById(id)
                .orElseThrow(() -> new ApiRequestException(DIARY_NOT_FOUND));

        return diary.getStatus();
    }

    public void updateDiaryStatus(Long id, DiaryStatus status) {
        Diary diary = diaryRepository.findById(id)
                .orElseThrow(() -> new ApiRequestException(DIARY_NOT_FOUND));

        diary.setStatus(status);
        diaryRepository.save(diary);
    }
}
