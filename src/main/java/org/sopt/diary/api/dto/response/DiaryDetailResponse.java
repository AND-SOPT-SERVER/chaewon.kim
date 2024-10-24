package org.sopt.diary.api.dto.response;

import org.sopt.diary.domain.DiaryEntity;

import java.time.LocalDateTime;

public class DiaryDetailResponse {
    private Long id;
    private String title;
    private String content;
    private LocalDateTime createdAt;

    public DiaryDetailResponse(Long id, String title, String content, LocalDateTime createdAt) {
        this.id = id;
        this.title = title;
        this.content = content;
        this.createdAt = createdAt;
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

    public static DiaryDetailResponse from(DiaryEntity diaryEntity) {
        return new DiaryDetailResponse(
                diaryEntity.getId(),
                diaryEntity.getTitle(),
                diaryEntity.getContent(),
                diaryEntity.getCreatedAt()
        );
    }
}
