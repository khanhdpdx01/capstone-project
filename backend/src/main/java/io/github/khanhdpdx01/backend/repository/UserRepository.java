package io.github.khanhdpdx01.backend.repository;

import io.github.khanhdpdx01.backend.entity.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Integer> {
    @Query("SELECT u from User u where u.username = :username")
    Optional<User> findByUsername(String username);

    @Query("SELECT u from User u where u.username LIKE %:keyword%")
    Page<User> search(@Param("keyword") String keyword, Pageable pageable);
}
