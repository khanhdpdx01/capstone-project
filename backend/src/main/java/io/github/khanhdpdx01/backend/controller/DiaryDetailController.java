package io.github.khanhdpdx01.backend.controller;

import io.github.khanhdpdx01.backend.dto.diary_detail.DiaryDetailDto;
import io.github.khanhdpdx01.backend.entity.DiaryDetail;
import io.github.khanhdpdx01.backend.service.DiaryDetailService;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequestMapping("/api/diary-details")
public class DiaryDetailController {
    private final DiaryDetailService diaryDetailService;

    public DiaryDetailController(DiaryDetailService diaryDetailService) {
        this.diaryDetailService = diaryDetailService;
    }

    @PostMapping(produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    @PreAuthorize("hasRole('ROLE_ADMIN') or hasRole('ROLE_REPRESENTATIVE')")
    public ResponseEntity<?> createOrUpdate(@RequestPart("diary-detail") DiaryDetailDto diaryDetailDto,
                                            @RequestPart("images") List<MultipartFile> images) {
        DiaryDetail diaryDetail = diaryDetailService.create(diaryDetailDto, images);
        return ResponseEntity.status(HttpStatus.OK).body(diaryDetail);
    }
}
