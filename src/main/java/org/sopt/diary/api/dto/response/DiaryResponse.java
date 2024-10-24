package org.sopt.diary.api.dto.response;

import org.sopt.diary.domain.DiaryEntity;

public class DiaryResponse {
    private long id;
    private String title;

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
