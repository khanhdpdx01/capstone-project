package io.github.khanhdpdx01.backend.service;

import io.github.khanhdpdx01.backend.dto.diary.DiaryDto;
import io.github.khanhdpdx01.backend.entity.Diary;
import io.github.khanhdpdx01.backend.entity.User;
import io.github.khanhdpdx01.backend.mapper.DiaryMapper;
import io.github.khanhdpdx01.backend.repository.DiaryRepository;
import io.github.khanhdpdx01.backend.security.AuthenticationFacade;
import io.github.khanhdpdx01.backend.util.PaginationAndSortUtil;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import static io.github.khanhdpdx01.backend.common.AppConstant.ADMIN;

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
}
