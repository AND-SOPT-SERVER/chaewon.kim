package org.sopt.diary.api.dto.response.Diary;

import org.sopt.diary.domain.DiaryEntity;
import org.springframework.data.domain.Page;

import java.util.List;

public class DiaryListResponse {
    private List<DiaryResponse> diaries;

    public DiaryListResponse(List<DiaryResponse> diaries) {
        this.diaries = diaries;
    }

    public List<DiaryResponse> getDiaries() {
        return diaries;
    }

    public static DiaryListResponse from(Page<DiaryEntity> diaryEntities) {
        return new DiaryListResponse(
                diaryEntities.stream()
                        .map(DiaryResponse::from)
                        .toList()
        );
    }
}
