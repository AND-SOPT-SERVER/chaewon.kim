package org.sopt.diary.api.service;

import org.sopt.diary.api.dto.request.DiaryCreateDto;
import org.sopt.diary.api.dto.request.DiaryUpdateDto;
import org.sopt.diary.api.dto.response.DiaryDetailResponse;
import org.sopt.diary.api.dto.response.DiaryListResponse;
import org.sopt.diary.api.dto.response.DiaryResponse;
import org.sopt.diary.common.exception.ErrorCode;
import org.sopt.diary.common.exception.NotFoundException;
import org.sopt.diary.domain.DiaryEntity;
import org.sopt.diary.domain.repository.DiaryRepository;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Component
public class DiaryService {
    private final DiaryRepository diaryRepository;

    public DiaryService(DiaryRepository diaryRepository) {
        this.diaryRepository = diaryRepository;
    }

    // 일기 작성
    @Transactional
    public DiaryResponse createDiary(final DiaryCreateDto diaryCreateDto) {
        DiaryEntity diaryEntity = new DiaryEntity(diaryCreateDto.getTitle(), diaryCreateDto.getContent());
        return DiaryResponse.from(diaryRepository.save(diaryEntity));
    }

    // 일기 상세 조회
    @Transactional(readOnly = true)
    public DiaryDetailResponse getDiary(final Long diaryId) {
        DiaryEntity diaryEntity = findDiaryById(diaryId);
        return DiaryDetailResponse.from(diaryEntity);
    }

    // 일기 목록 조회
    @Transactional(readOnly = true)
    public DiaryListResponse getDiaryList() {
        List<DiaryEntity> diaryEntities = diaryRepository.findTop10ByOrderByCreatedAtDesc();
        return DiaryListResponse.from(diaryEntities);
    }

    // 일기 수정
    @Transactional
    public void updateDiary(final Long diaryId, final DiaryUpdateDto diaryUpdateDto) {
        DiaryEntity diaryEntity = findDiaryById(diaryId);
        diaryEntity.updateDiary(diaryUpdateDto.getTitle(), diaryUpdateDto.getContent());
    }

    // 일기 삭제
    @Transactional
    public void deleteDiary(final Long diaryId) {
        findDiaryById(diaryId);
        diaryRepository.deleteById(diaryId);
    }

    private DiaryEntity findDiaryById(final Long diaryId) {
        return diaryRepository.findById(diaryId).orElseThrow(
                () -> new NotFoundException(ErrorCode.DIARY_NOT_FOUND)
        );
    }
}
