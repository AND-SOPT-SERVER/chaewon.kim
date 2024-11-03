package org.sopt.diary.api.service;

import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Join;
import jakarta.persistence.criteria.Root;
import org.sopt.diary.domain.Category;
import org.sopt.diary.domain.DiaryEntity;
import org.springframework.data.jpa.domain.Specification;

public class DiarySpecification {

    public static Specification<DiaryEntity> withCategoryAndIsPublic(Category category) {
        return (Root<DiaryEntity> root, CriteriaQuery<?>query, CriteriaBuilder cb) -> cb.and(
                    cb.equal(root.get("category"), category),
                    cb.isTrue(root.get("isPublic")) // isPublic이 true인 경우만
            );
    }

    public static Specification<DiaryEntity> withMemberIdAndCategory(Long memberId, Category category) {
        return (root, query, cb) -> {
            Join<Object, Object> memberJoin = root.join("soptMember");  // soptMember 필드 조인
            return category != null
                    ? cb.and(cb.equal(memberJoin.get("id"), memberId), cb.equal(root.get("category"), category))
                    : cb.equal(memberJoin.get("id"), memberId);
        };
    }

    public static Specification<DiaryEntity> orderByCreatedAt() {
        return (Root<DiaryEntity> root, CriteriaQuery<?> query, CriteriaBuilder cb) -> {
            query.orderBy(cb.desc(root.get("createdAt")));
            return query.getRestriction();
        };
    }

    public static Specification<DiaryEntity> orderByContentLength() {
        return (Root<DiaryEntity> root, CriteriaQuery<?> query, CriteriaBuilder cb) -> {
            query.orderBy(cb.desc(cb.length(root.get("content"))));
            return query.getRestriction();
        };
    }
}

