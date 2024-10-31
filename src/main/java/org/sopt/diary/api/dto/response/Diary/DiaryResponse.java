package org.sopt.diary.api.dto.response.Diary;

import org.sopt.diary.domain.DiaryEntity;

public class DiaryResponse {
    private final long id;
    private final String title;

    public DiaryResponse(long id, String title) {
        this.id = id;
        this.title = title;
    }

    public static DiaryResponse from(DiaryEntity diaryEntity) {
        return new DiaryResponse(
                diaryEntity.getId(),
                diaryEntity.getTitle()
        );
    }

    public long getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }
}
