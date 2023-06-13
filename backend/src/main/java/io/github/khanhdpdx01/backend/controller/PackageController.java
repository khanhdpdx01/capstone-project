package io.github.khanhdpdx01.backend.controller;

import io.github.khanhdpdx01.backend.dto.packing.PackageDto;
import io.github.khanhdpdx01.backend.service.PackageService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/packages")
public class PackageController {
    private final PackageService packageService;

    public PackageController(PackageService packageService) {
        this.packageService = packageService;
    }

    @PostMapping
    @PreAuthorize("hasRole('ROLE_ADMIN') or hasRole('ROLE_REPRESENTATIVE')")
    public ResponseEntity<?> doPackage(@RequestBody PackageDto packageDto) {
        packageService.doPackage(packageDto);
        return ResponseEntity.status(HttpStatus.OK).body("Success");
    }
}
