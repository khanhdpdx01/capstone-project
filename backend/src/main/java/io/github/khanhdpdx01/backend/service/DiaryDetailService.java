package io.github.khanhdpdx01.backend.service;

import io.github.khanhdpdx01.backend.dto.diary_detail.DiaryDetailDto;
import io.github.khanhdpdx01.backend.dto.diary_detail.DiaryDetailView;
import io.github.khanhdpdx01.backend.entity.DiaryDetail;
import io.github.khanhdpdx01.backend.entity.DiaryStatus;
import io.github.khanhdpdx01.backend.entity.DiaryStep;
import io.github.khanhdpdx01.backend.entity.User;
import io.github.khanhdpdx01.backend.exception.ApiRequestException;
import io.github.khanhdpdx01.backend.mapper.DiaryDetailMapper;
import io.github.khanhdpdx01.backend.repository.DiaryDetailRepository;
import io.github.khanhdpdx01.backend.security.AuthenticationFacade;
import io.github.khanhdpdx01.backend.util.FileUtil;
import org.hyperledger.fabric.gateway.Transaction;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.nio.file.Paths;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class DiaryDetailService {
    private static final Logger logger = LoggerFactory.getLogger(PartnerService.class);
    private final AuthenticationFacade authenticationFacade;
    private final DiaryDetailRepository diaryDetailRepository;
    private final DiaryService diaryService;
    private final ChaincodeService chaincodeService;
    @Value("${storage.file.directory}")
    private String fileDirectory;

    public DiaryDetailService(AuthenticationFacade authenticationFacade, DiaryDetailRepository diaryDetailRepository, DiaryService diaryService, ChaincodeService chaincodeService) {
        this.authenticationFacade = authenticationFacade;
        this.diaryDetailRepository = diaryDetailRepository;
        this.diaryService = diaryService;
        this.chaincodeService = chaincodeService;
    }

    public DiaryDetail create(DiaryDetailDto diaryDetailDto, List<MultipartFile> images) {
        DiaryDetail diaryDetail = DiaryDetailMapper.INSTANCE.dtoToEntity(diaryDetailDto);

        // generate random string for each image
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

            diaryDetail.setImagePath(imgListAsStr.stream().collect(Collectors.joining(",")));
        }

        User createdBy = new User();
        createdBy.setId(authenticationFacade.getUserId());
        diaryDetail.setCreatedBy(createdBy);

        DiaryDetail newDiaryDetail = diaryDetailRepository.save(diaryDetail);
        logger.info("Create diary detail success: " + diaryDetail.getId());

        // update diary status
        DiaryStatus status = diaryService.getDiaryStatus(diaryDetailDto.getDiaryId());
        if (DiaryStatus.OPENING.equals(status)) {
            diaryService.updateDiaryStatus(diaryDetailDto.getDiaryId(), DiaryStatus.IN_PRODUCTION);
            logger.info("Update diary status success: " + diaryDetail.getId());
        }

        if (DiaryStep.HARVESTING.equals(diaryDetailDto.getStep())) {
            diaryService.updateDiaryStatus(diaryDetailDto.getDiaryId(), DiaryStatus.CLOSED);
            logger.info("Update diary status success: " + diaryDetail.getId());
        }

        DiaryDetailView diaryDetailInfo = diaryDetailRepository.getDiaryDetailById(newDiaryDetail.getId())
                .orElseThrow(() -> new ApiRequestException("Diary detail not found"));
        // upload to blockchain
        try {
            Transaction transaction = chaincodeService.createTransaction("CreateDiaryDetail");
            byte[] res = transaction.submit(Long.toString(diaryDetailInfo.getId()),
                    Long.toString(diaryDetailInfo.getDiaryId()),
                    diaryDetailInfo.getName(),
                    diaryDetailInfo.getStep().name(),
                    diaryDetailInfo.getDescription(),
                    diaryDetailInfo.getCreatedAt().toString(),
                    diaryDetailInfo.getFullName());

            String transactionId = transaction.getTransactionId();
            diaryDetailRepository.updateTransactionId(diaryDetailInfo.getId(), transactionId);
            logger.info("Updating transaction is success: " + diaryDetailInfo.getId());
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        return newDiaryDetail;
    }
}
