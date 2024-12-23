package org.sopt.diary.api.service;

import org.sopt.diary.api.dto.response.Diary.DiaryDetailResponse;
import org.sopt.diary.api.dto.response.Diary.DiaryListResponse;
import org.sopt.diary.api.dto.response.Diary.DiaryResponseId;
import org.sopt.diary.api.dto.response.Diary.MyDiaryListResponse;
import org.sopt.diary.common.exception.DiaryErrorCode;
import org.sopt.diary.common.exception.BusinessException;
import org.sopt.diary.domain.Category;
import org.sopt.diary.domain.DiaryEntity;
import org.sopt.diary.domain.SoptMember;
import org.sopt.diary.domain.repository.DiaryRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
public class DiaryService {

    public static final String CREATED_AT = "createdAt";
    public static final String CONTENT_LENGTH = "contentLength";

    private final DiaryRepository diaryRepository;
    private final MemberService memberService;

    public DiaryService(DiaryRepository diaryRepository, MemberService memberService) {
        this.diaryRepository = diaryRepository;
        this.memberService = memberService;
    }

    // 일기 작성
    @Transactional
    public DiaryResponseId createDiary(final Long memberId, final String title, final String content, final String category, final boolean isPublic) {
        SoptMember member = memberService.findById(memberId);
        DiaryEntity diaryEntity = new DiaryEntity(title, content, Category.fromName(category), isPublic, member);
        return DiaryResponseId.from(diaryRepository.save(diaryEntity));
    }

    // 일기 상세 조회
    @Transactional(readOnly = true)
    public DiaryDetailResponse getDiary(final Long diaryId) {
        DiaryEntity diaryEntity = findDiaryById(diaryId);
        if (!diaryEntity.isPublic()) {
            throw new BusinessException(DiaryErrorCode.DIARY_FORBIDDEN);
        }
        return DiaryDetailResponse.from(diaryEntity);
    }

    // 일기 목록 조회
    @Transactional(readOnly = true)
    public DiaryListResponse getDiaryList(
            final Category category,
            final String sortBy,
            Pageable pageable
    ) {
        Specification<DiaryEntity> specification = createSpecificationWithSorting(category, null, sortBy);
        pageable = PageRequest.of(pageable.getPageNumber(), pageable.getPageSize());

        Page<DiaryEntity> diaryEntities = diaryRepository.findAll(specification, pageable);
        return DiaryListResponse.from(diaryEntities);
    }

    // 내 일기 목록 조회
    @Transactional(readOnly = true)
    public MyDiaryListResponse getMyDiaryList(
            final Long memberId,
            final Category category,
            final String sortBy,
            Pageable pageable
    ) {
        Specification<DiaryEntity> specification = createSpecificationWithSorting(category, memberId, sortBy);
        pageable = PageRequest.of(pageable.getPageNumber(), pageable.getPageSize());

        memberService.findById(memberId);
        Page<DiaryEntity> diaryEntities = diaryRepository.findAll(specification, pageable);
        return MyDiaryListResponse.from(diaryEntities);
    }

    // 일기 수정
    @Transactional
    public void updateDiary(final Long diaryId, final String title, final String content) {
        DiaryEntity diaryEntity = findDiaryById(diaryId);
        diaryEntity.updateDiary(title, content);
    }

    // 일기 삭제
    @Transactional
    public void deleteDiary(final Long diaryId) {
        findDiaryById(diaryId);
        diaryRepository.deleteById(diaryId);
    }

    private DiaryEntity findDiaryById(final Long diaryId) {
        return diaryRepository.findByIdWithMember(diaryId).orElseThrow(
                () -> new BusinessException(DiaryErrorCode.DIARY_NOT_FOUND)
        );
    }

    private Specification<DiaryEntity> createSpecificationWithSorting(Category category, Long memberId, String sortBy) {
        Specification<DiaryEntity> specification;

        if (memberId != null) {
            specification = DiarySpecification.withMemberIdAndCategory(memberId, category);
        } else {
            specification = DiarySpecification.withCategoryAndIsPublic(category);
        }

        // 정렬 조건 추가
        if (CREATED_AT.equals(sortBy)) {
            specification = specification.and(DiarySpecification.orderByCreatedAt());
        } else if (CONTENT_LENGTH.equals(sortBy)) {
            specification = specification.and(DiarySpecification.orderByContentLength());
        }

        return specification;
    }
}
