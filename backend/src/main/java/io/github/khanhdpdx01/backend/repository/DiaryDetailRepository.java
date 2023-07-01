package io.github.khanhdpdx01.backend.repository;

import io.github.khanhdpdx01.backend.dto.diary_detail.DiaryDetailView;
import io.github.khanhdpdx01.backend.entity.DiaryDetail;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Transactional
public interface DiaryDetailRepository extends JpaRepository<DiaryDetail, Long> {

    @Query("SELECT d from DiaryDetail d where d.diary.id = :diaryId")
    List<DiaryDetail> findAllByDiaryId(@Param("diaryId") Long diaryId);

    @Query(value = "select dd.id, dd.diary_id as diaryId, i.name , dd.step , dd.description , dd.created_at as createdAt, u.full_name as fullName " +
            "from diary_details dd " +
            "inner join ingredients i " +
            "on dd.ingredient_id = i.id " +
            "inner join users u " +
            "on dd.created_by = u.id " +
            "where dd.id  = :id", nativeQuery = true)
    Optional<DiaryDetailView> getDiaryDetailById(@Param("id") Long id);

    @Modifying
    @Query("UPDATE DiaryDetail d set d.transactionId = :transactionId where d.id = :id")
    void updateTransactionId(@Param("id") Long id, @Param("transactionId") String transactionId);
}
