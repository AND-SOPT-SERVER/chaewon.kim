package org.sopt.diary.api.service;

// DiarySpecification.java
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Root;
import org.sopt.diary.domain.Category;
import org.sopt.diary.domain.DiaryEntity;
import org.springframework.data.jpa.domain.Specification;

public class DiarySpecification {

    public static Specification<DiaryEntity> withCategoryAndIsPublic(Category category) {
        return (Root<DiaryEntity> root, CriteriaQuery<?>query, CriteriaBuilder cb) -> {
//            root.fetch("soptMember"); // JOIN FETCH 설정
//            query.distinct(true); // 중복 제거
            return cb.and(
                    cb.equal(root.get("category"), category),
                    cb.isTrue(root.get("isPublic")) // isPublic이 true인 경우만
            );
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

