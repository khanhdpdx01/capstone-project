package io.github.khanhdpdx01.backend.repository;

import io.github.khanhdpdx01.backend.entity.PackageProduct;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface PackageRepository extends JpaRepository<PackageProduct, String> {
    @Query("SELECT pd from PackageProduct pd where pd.sku LIKE %:keyword% OR pd.productName LIKE %:keyword%")
    Page<PackageProduct> search(String keyword, Pageable pageable);
}
