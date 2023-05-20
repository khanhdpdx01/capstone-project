package io.github.khanhdpdx01.backend.controller;

import io.github.khanhdpdx01.backend.dto.pagination.PaginationParams;
import io.github.khanhdpdx01.backend.dto.pagination.PaginationResponse;
import io.github.khanhdpdx01.backend.dto.product.ProductDto;
import io.github.khanhdpdx01.backend.entity.Ingredient;
import io.github.khanhdpdx01.backend.entity.Product;
import io.github.khanhdpdx01.backend.service.ProductService;
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
@RequestMapping("/api/products")
public class ProductController {
    private final ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping
    @PreAuthorize("hasRole('ROLE_ADMIN') or hasRole('ROLE_REPRESENTATIVE')")
    public ResponseEntity<?> getAllWithPaging(@Valid PaginationParams params) {
        Page<Product> productPage = productService.getAllWithPaging(
                params.getPage(),
                params.getSize(),
                params.getSort(),
                params.getKeyword());
        PaginationResponse<Product> response = PaginationAndSortUtil.map(productPage);

        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @PostMapping(produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    @PreAuthorize("hasRole('ROLE_ADMIN') or hasRole('ROLE_REPRESENTATIVE')")
    public ResponseEntity<?> createOrUpdate(@RequestPart("product") ProductDto productDto,
                                            @RequestPart("certificates") List<MultipartFile> certificates,
                                            @RequestPart("images") List<MultipartFile> images) {
        Product product = productService.createOrUpdate(productDto, certificates, images);
        return ResponseEntity.status(HttpStatus.OK).body(product);
    }
}
