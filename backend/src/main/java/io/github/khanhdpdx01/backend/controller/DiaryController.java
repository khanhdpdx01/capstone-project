package io.github.khanhdpdx01.backend.controller;

import io.github.khanhdpdx01.backend.dto.diary.DiaryDto;
import io.github.khanhdpdx01.backend.dto.pagination.PaginationParams;
import io.github.khanhdpdx01.backend.dto.pagination.PaginationResponse;
import io.github.khanhdpdx01.backend.entity.Diary;
import io.github.khanhdpdx01.backend.entity.Ingredient;
import io.github.khanhdpdx01.backend.service.DiaryService;
import io.github.khanhdpdx01.backend.util.PaginationAndSortUtil;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/diaries")
public class DiaryController {
    private final DiaryService diaryService;

    public DiaryController(DiaryService diaryService) {
        this.diaryService = diaryService;
    }

    @GetMapping
    @PreAuthorize("hasRole('ROLE_ADMIN') or hasRole('ROLE_REPRESENTATIVE')")
    public ResponseEntity<?> getAllWithPaging(@Valid PaginationParams params) {
        Page<Diary> productPage = diaryService.getAllWithPaging(
                params.getPage(),
                params.getSize(),
                params.getSort(),
                params.getKeyword());
        PaginationResponse<Diary> response = PaginationAndSortUtil.map(productPage);

        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @PostMapping
    @PreAuthorize("hasRole('ROLE_ADMIN') or hasRole('ROLE_REPRESENTATIVE')")
    public ResponseEntity<?> createOrUpdate(@RequestBody DiaryDto diaryDto) {
        Diary diary = diaryService.create(diaryDto);
        return ResponseEntity.status(HttpStatus.OK).body(diary);
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasRole('ROLE_ADMIN') or hasRole('ROLE_REPRESENTATIVE')")
    public ResponseEntity<?> getDetail(@PathVariable("id") Long id) {
        DiaryDto dto = diaryService.getDetail(id);
        return ResponseEntity.status(HttpStatus.OK).body(dto);
    }
}
