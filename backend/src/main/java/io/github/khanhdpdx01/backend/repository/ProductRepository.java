package io.github.khanhdpdx01.backend.repository;

import io.github.khanhdpdx01.backend.entity.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface ProductRepository extends JpaRepository<Product, Long> {
    @Query("SELECT p from Product p where p.name LIKE %:keyword% OR p.gtinCode LIKE %:keyword% ")
    Page<Product> search(@Param("keyword") String keyword, Pageable pageable);
}
