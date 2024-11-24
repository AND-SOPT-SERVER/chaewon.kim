package org.sopt.diary.api.dto.response.Diary;

import org.sopt.diary.domain.DiaryEntity;

import java.time.LocalDateTime;

public class DiaryResponse {
    private final long id;
    private final String title;
    private final String nickname;
    private final LocalDateTime createdAt;

    public DiaryResponse(long id, String title, String nickname, LocalDateTime createdAt) {
        this.id = id;
        this.title = title;
        this.nickname = nickname;
        this.createdAt = createdAt;
    }

    public static DiaryResponse from(DiaryEntity diaryEntity) {
        return new DiaryResponse(
                diaryEntity.getId(),
                diaryEntity.getTitle(),
                diaryEntity.getSoptMember().getNickname(),
                diaryEntity.getCreatedAt()
        );
    }

    public long getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }
}
