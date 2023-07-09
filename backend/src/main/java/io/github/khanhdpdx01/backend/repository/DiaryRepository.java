package io.github.khanhdpdx01.backend.repository;

import io.github.khanhdpdx01.backend.entity.Diary;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Transactional
public interface DiaryRepository extends JpaRepository<Diary, Long> {
    @Query("SELECT d from Diary d " +
            "where (:userId is NULL OR d.createdBy.id = :userId ) " +
            "and d.name LIKE %:keyword% OR d.product.name LIKE %:keyword% ")
    Page<Diary> search(@Param("userId") Long userId, @Param("keyword") String keyword, Pageable pageable);

    @Query("SELECT d from Diary d " +
            "where d.createdBy.id = :userId ")
    Page<Diary> findAll(@Param("userId") Long userId, Pageable pageable);

    @Query("SELECT d from Diary d " +
            "where d.product.id = :productId")
    Optional<Diary> findByProduct(@Param("productId") Long productId);

    @Modifying
    @Query("update Diary d " +
            "set d.transactionId = :transactionId " +
            "where d.id = :diaryId")
    void updateTransactionId(@Param("diaryId") Long diaryId, @Param("transactionId") String transactionId);

    @Query("SELECT d from Diary d " +
            "where d.createdBy.id = :userId ")
    List<Diary> findAllWithoutPaging(@Param("userId") Long userId);
}
