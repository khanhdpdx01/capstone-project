package io.github.khanhdpdx01.backend.repository;

import io.github.khanhdpdx01.backend.entity.Diary;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DiaryRepository extends JpaRepository<Diary, Long> {
}
