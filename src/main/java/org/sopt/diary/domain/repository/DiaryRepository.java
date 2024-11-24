package org.sopt.diary.domain.repository;

import org.sopt.diary.domain.Category;
import org.sopt.diary.domain.DiaryEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface DiaryRepository extends JpaRepository<DiaryEntity, Long>, JpaSpecificationExecutor<DiaryEntity> {

    // 여기서 내 private 일기 보여줄지 말지
    @Query("SELECT d FROM DiaryEntity d JOIN FETCH d.soptMember " +
            "WHERE (:category IS NULL OR d.category = :category) " +
            "AND d.isPublic = true")
    Page<DiaryEntity> findByCategoryAndIsPublicTrue(Category category, Pageable pageable);

    @Query("SELECT d FROM DiaryEntity d JOIN FETCH d.soptMember m " +
            "WHERE m.id = :memberId " +
            "AND (:category IS NULL OR d.category = :category)")
    Page<DiaryEntity> findByMemberIdAndCategory(Long memberId, Category category, Pageable pageable);

    @Query("SELECT d FROM DiaryEntity d " +
            "JOIN FETCH d.soptMember " +
            "WHERE d.id = :id")
    Optional<DiaryEntity> findByIdWithMember(Long id);

    @Override
    @EntityGraph(attributePaths = {"soptMember"})
    Page<DiaryEntity> findAll(Specification<DiaryEntity> specification, Pageable pageable);

}
