package io.github.khanhdpdx01.backend.controller;

import io.github.khanhdpdx01.backend.dto.product.ProductDto;
import io.github.khanhdpdx01.backend.entity.Product;
import io.github.khanhdpdx01.backend.service.ProductService;
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
@RequestMapping("/api/products")
public class ProductController {
    private final ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @PostMapping(produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    @PreAuthorize("hasRole('ROLE_ADMIN') or hasRole('ROLE_REPRESENTATIVE')")
    public ResponseEntity<?> createOrUpdate(@RequestPart("products") ProductDto productDto,
                                            @RequestPart("certificates") List<MultipartFile> certificates,
                                            @RequestPart("images") List<MultipartFile> images) {
        Product product = productService.createOrUpdate(productDto, certificates, images);
        return ResponseEntity.status(HttpStatus.OK).body(product);
    }
}
