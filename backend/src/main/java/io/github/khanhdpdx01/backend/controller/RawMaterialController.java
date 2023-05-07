package io.github.khanhdpdx01.backend.controller;

import io.github.khanhdpdx01.backend.dto.ingredient.IngredientDto;
import io.github.khanhdpdx01.backend.dto.raw_material.RawMaterialDto;
import io.github.khanhdpdx01.backend.entity.Ingredient;
import io.github.khanhdpdx01.backend.entity.RawMaterial;
import io.github.khanhdpdx01.backend.service.RawMaterialService;
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
@RequestMapping("/api/raw-materials")
public class RawMaterialController {
    private RawMaterialService rawMaterialService;

    public RawMaterialController(RawMaterialService rawMaterialService) {
        this.rawMaterialService = rawMaterialService;
    }

    @PostMapping(produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    @PreAuthorize("hasRole('ROLE_ADMIN') or hasRole('ROLE_REPRESENTATIVE')")
    public ResponseEntity<?> createOrUpdate(@RequestPart("raw-material") RawMaterialDto rawMaterialDto,
                                            @RequestPart("certificates") List<MultipartFile> certificates,
                                            @RequestPart("images") List<MultipartFile> images) {
        RawMaterial rawMaterial = rawMaterialService.createOrUpdate(rawMaterialDto, certificates, images);
        return ResponseEntity.status(HttpStatus.OK).body(rawMaterial);
    }
}
