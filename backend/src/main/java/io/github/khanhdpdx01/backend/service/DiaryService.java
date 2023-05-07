package io.github.khanhdpdx01.backend.service;

import io.github.khanhdpdx01.backend.dto.diary.DiaryDto;
import io.github.khanhdpdx01.backend.entity.Diary;
import io.github.khanhdpdx01.backend.entity.User;
import io.github.khanhdpdx01.backend.mapper.DiaryMapper;
import io.github.khanhdpdx01.backend.repository.DiaryRepository;
import io.github.khanhdpdx01.backend.security.AuthenticationFacade;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class DiaryService {
    private static final Logger logger = LoggerFactory.getLogger(PartnerService.class);
    private final AuthenticationFacade authenticationFacade;
    private final DiaryRepository diaryRepository;

    public DiaryService(DiaryRepository diaryRepository, AuthenticationFacade authenticationFacade) {
        this.diaryRepository = diaryRepository;
        this.authenticationFacade = authenticationFacade;
    }

    public Diary create(DiaryDto diaryDto) {
        Diary diary = DiaryMapper.INSTANCE.dtoToEntity(diaryDto);
        User createdBy = new User();
        createdBy.setId(authenticationFacade.getUserId());
        diary.setCreatedBy(createdBy);

        Diary newDiary = diaryRepository.save(diary);
        logger.info("Create diary success: " + newDiary.getId());

        return newDiary;
    }
}
