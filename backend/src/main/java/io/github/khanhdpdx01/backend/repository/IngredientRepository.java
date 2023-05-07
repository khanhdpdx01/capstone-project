package io.github.khanhdpdx01.backend.repository;

import io.github.khanhdpdx01.backend.entity.Ingredient;
import io.github.khanhdpdx01.backend.entity.Partner;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface IngredientRepository extends JpaRepository<Ingredient, Long> {
    @Query("SELECT i from Ingredient i where i.name LIKE %:keyword% OR i.gtinCode LIKE %:keyword%")
    Page<Ingredient> search(@Param("keyword") String keyword, Pageable pageable);
}
