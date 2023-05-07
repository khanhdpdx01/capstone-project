package io.github.khanhdpdx01.backend.service;

import io.github.khanhdpdx01.backend.dto.diary_detail.DiaryDetailDto;
import io.github.khanhdpdx01.backend.entity.DiaryDetail;
import io.github.khanhdpdx01.backend.entity.User;
import io.github.khanhdpdx01.backend.mapper.DiaryDetailMapper;
import io.github.khanhdpdx01.backend.repository.DiaryDetailRepository;
import io.github.khanhdpdx01.backend.security.AuthenticationFacade;
import io.github.khanhdpdx01.backend.util.FileUtil;
import io.github.khanhdpdx01.backend.util.StringUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

@Service
public class DiaryDetailService {
    private static final Logger logger = LoggerFactory.getLogger(PartnerService.class);
    private final AuthenticationFacade authenticationFacade;
    private final DiaryDetailRepository diaryDetailRepository;

    @Value("${storage.file.directory}")
    private String fileDirectory;

    public DiaryDetailService(AuthenticationFacade authenticationFacade, DiaryDetailRepository diaryDetailRepository) {
        this.authenticationFacade = authenticationFacade;
        this.diaryDetailRepository = diaryDetailRepository;
    }

    public DiaryDetail create(DiaryDetailDto diaryDetailDto, List<MultipartFile> images) {
        Path path = Paths.get(fileDirectory, Long.toString(authenticationFacade.getUserId()));

        try {
            Files.createDirectories(path);
        } catch (IOException e) {
            logger.error("Create directory not success: ", path);
        }

        for (MultipartFile image : images) {
            FileUtil.save(path, image);
        }

        DiaryDetail diaryDetail = DiaryDetailMapper.INSTANCE.dtoToEntity(diaryDetailDto);
        diaryDetail.setImagePath(StringUtil.join(FileUtil.getFilenameArray(images)));

        User createdBy = new User();
        createdBy.setId(authenticationFacade.getUserId());
        diaryDetail.setCreatedBy(createdBy);

        DiaryDetail newDiaryDetail = diaryDetailRepository.save(diaryDetail);
        logger.info("Create diary detail success: " + diaryDetail.getId());

        return newDiaryDetail;
    }
}
