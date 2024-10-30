package org.sopt.diary.domain.repository;

import org.sopt.diary.domain.DiaryEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface DiaryRepository extends JpaRepository<DiaryEntity, Long> {

    List<DiaryEntity> findTop10ByOrderByCreatedAtDesc();
}
