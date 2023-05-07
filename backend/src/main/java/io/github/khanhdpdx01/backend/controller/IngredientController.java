package io.github.khanhdpdx01.backend.controller;

import io.github.khanhdpdx01.backend.dto.ingredient.IngredientDto;
import io.github.khanhdpdx01.backend.dto.pagination.PaginationParams;
import io.github.khanhdpdx01.backend.dto.pagination.PaginationResponse;
import io.github.khanhdpdx01.backend.entity.Ingredient;
import io.github.khanhdpdx01.backend.service.IngredientService;
import io.github.khanhdpdx01.backend.util.PaginationAndSortUtil;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/ingredients")
public class IngredientController {
    private final IngredientService ingredientService;

    public IngredientController(IngredientService ingredientService) {
        this.ingredientService = ingredientService;
    }

    @GetMapping
    @PreAuthorize("hasRole('ROLE_ADMIN') or hasRole('ROLE_PARTNER')")
    public ResponseEntity<?> getAllWithPaging(@Valid PaginationParams params) {
        Page<Ingredient> ingredientPage = ingredientService.getAllWithPaging(
                params.getPage(),
                params.getSize(),
                params.getSort(),
                params.getKeyword());
        PaginationResponse<Ingredient> response = PaginationAndSortUtil.map(ingredientPage);

        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasRole('ROLE_ADMIN') or hasRole('ROLE_PARTNER')")
    public ResponseEntity<?> getDetail(@PathVariable("id") Long id) {
        Ingredient ingredient = ingredientService.getDetail(id);
        return ResponseEntity.status(HttpStatus.OK).body(ingredient);
    }

    @PostMapping(produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    @PreAuthorize("hasRole('ROLE_ADMIN') or hasRole('ROLE_PARTNER')")
    public ResponseEntity<?> createOrUpdate(@RequestPart("ingredient") IngredientDto ingredientDto,
                                            @RequestPart("certificates") List<MultipartFile> certificates,
                                            @RequestPart("images") List<MultipartFile> images) {
        Ingredient ingredient = ingredientService.createOrUpdate(ingredientDto, certificates, images);
        return ResponseEntity.status(HttpStatus.OK).body(ingredient);
    }
}
