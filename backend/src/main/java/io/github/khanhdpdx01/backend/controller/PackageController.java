package io.github.khanhdpdx01.backend.controller;

import io.github.khanhdpdx01.backend.dto.packing.PackageDto;
import io.github.khanhdpdx01.backend.dto.pagination.PaginationParams;
import io.github.khanhdpdx01.backend.dto.pagination.PaginationResponse;
import io.github.khanhdpdx01.backend.entity.Ingredient;
import io.github.khanhdpdx01.backend.entity.PackageProduct;
import io.github.khanhdpdx01.backend.entity.Stamp;
import io.github.khanhdpdx01.backend.service.PackageService;
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
@RequestMapping("/api/packages")
public class PackageController {
    private final PackageService packageService;

    public PackageController(PackageService packageService) {
        this.packageService = packageService;
    }

    @PostMapping(produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    @PreAuthorize("hasRole('ROLE_ADMIN') or hasRole('ROLE_REPRESENTATIVE')")
    public ResponseEntity<?> doPackage(@RequestPart("packageDto") PackageDto packageDto,
                                       @RequestPart("images") List<MultipartFile> images) {
        packageService.doPackage(packageDto, images);
        return ResponseEntity.status(HttpStatus.OK).body("Success");
    }

    @GetMapping("/stamps/{sku}")
    public ResponseEntity<?> getListStampBySku(@PathVariable("sku") String sku, @Valid PaginationParams params) {
        Page<Stamp> ingredientPage = packageService.getListStampBySku(sku,
                params.getPage(),
                params.getSize(),
                params.getSort(),
                params.getKeyword());
        PaginationResponse<Ingredient> response = PaginationAndSortUtil.map(ingredientPage);

        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @GetMapping("/{sku}")
    public ResponseEntity<?> getDetail(@PathVariable("sku") String sku) {
        PackageProduct packageProduct = packageService.detail(sku);
        return ResponseEntity.status(HttpStatus.OK).body(packageProduct);
    }

    @GetMapping
    @PreAuthorize("hasRole('ROLE_ADMIN') or hasRole('ROLE_PARTNER') or hasRole('ROLE_REPRESENTATIVE')")
    public ResponseEntity<?> getAllWithPaging(@Valid PaginationParams params) {
        Page<PackageProduct> ingredientPage = packageService.getAllWithPaging(
                params.getPage(),
                params.getSize(),
                params.getSort(),
                params.getKeyword());
        PaginationResponse<Ingredient> response = PaginationAndSortUtil.map(ingredientPage);

        return ResponseEntity.status(HttpStatus.OK).body(response);
    }
}
