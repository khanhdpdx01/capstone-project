package io.github.khanhdpdx01.backend.repository;

import io.github.khanhdpdx01.backend.entity.PackageProduct;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PackageRepository extends JpaRepository<PackageProduct, String> {
}
