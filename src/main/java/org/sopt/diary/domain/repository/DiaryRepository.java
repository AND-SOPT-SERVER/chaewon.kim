package org.sopt.diary.domain.repository;

import org.sopt.diary.domain.DiaryEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface DiaryRepository extends JpaRepository<DiaryEntity, Long> {

    List<DiaryEntity> findTop10ByIsPublicTrueOrderByCreatedAtDesc();

    List<DiaryEntity> findTop10BySoptMemberIdAndIsPublicTrueOrderByCreatedAtDesc(Long memberId);

    @Query("SELECT d FROM DiaryEntity d " +
            "JOIN FETCH d.soptMember " +
            "WHERE d.id = :id")
    Optional<DiaryEntity> findByIdWithMember(Long id);
}
