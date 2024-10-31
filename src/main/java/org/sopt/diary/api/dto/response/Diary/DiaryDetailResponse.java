package org.sopt.diary.api.dto.response.Diary;

import org.sopt.diary.domain.DiaryEntity;

import java.time.LocalDateTime;

public class DiaryDetailResponse {
    private final Long id;
    private final String title;
    private final String content;
    private final LocalDateTime createdAt;
    private final String category;
    private final String nickname;

    public DiaryDetailResponse(Long id, String title, String content, LocalDateTime createdAt, String category, String nickname) {
        this.id = id;
        this.title = title;
        this.content = content;
        this.createdAt = createdAt;
        this.category = category;
        this.nickname = nickname;
    }

    public Long getId(){
        return id;
    }

    public String getTitle(){
        return title;
    }

    public String getContent(){
        return content;
    }

    public LocalDateTime getCreatedAt(){
        return createdAt;
    }

    public String getCategory(){
        return category;
    }

    public String getNickname(){
        return nickname;
    }

    public static DiaryDetailResponse from(DiaryEntity diaryEntity) {
        return new DiaryDetailResponse(
                diaryEntity.getId(),
                diaryEntity.getTitle(),
                diaryEntity.getContent(),
                diaryEntity.getCreatedAt(),
                diaryEntity.getCategory().getName(),
                diaryEntity.getSoptMember().getNickname()
        );
    }
}
