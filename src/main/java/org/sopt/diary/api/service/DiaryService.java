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
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Component
public class DiaryService {
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
        Specification<DiaryEntity> specification = DiarySpecification.withCategoryAndIsPublic(category);

        if (sortBy.equals("createdAt"))  {
            specification = specification.and(DiarySpecification.orderByCreatedAt());
        } else if (sortBy.equals("contentLength"))  {
            specification = specification.and(DiarySpecification.orderByContentLength());
        }

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
        List<Sort.Order> sorts = new ArrayList<>();

        if (sortBy.equals("createdAt"))  {
            sorts.add(Sort.Order.desc("createdAt"));
        } else if (sortBy.equals("contentLength"))  {
            sorts.add(Sort.Order.desc("contentLength"));
        }

        pageable = PageRequest.of(pageable.getPageNumber(), pageable.getPageSize(), Sort.by(sorts));

        memberService.findById(memberId);
        Page<DiaryEntity> diaryEntities = diaryRepository.findByMemberIdAndCategory(memberId, category, pageable);
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
}
