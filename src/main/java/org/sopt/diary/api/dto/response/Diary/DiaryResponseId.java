package org.sopt.diary.api.dto.response.Diary;

import org.sopt.diary.domain.DiaryEntity;

public class DiaryResponseId {
    private final long id;

    public DiaryResponseId(long id) {
        this.id = id;
    }

    public long getId() {
        return id;
    }

    public static DiaryResponseId from(DiaryEntity diaryEntity) {
        return new DiaryResponseId(
                diaryEntity.getId()
        );
    }
}
