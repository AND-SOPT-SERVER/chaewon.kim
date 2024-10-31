package org.sopt.diary.api.dto.response.Diary;

import org.sopt.diary.domain.DiaryEntity;

import java.time.LocalDateTime;
import java.util.List;

public class MyDiaryListResponse {
    private List<MyDiaryDetailResponse> diaries;

    public MyDiaryListResponse(List<MyDiaryDetailResponse> diaries) {
        this.diaries = diaries;
    }

    public List<MyDiaryDetailResponse> getDiaries() {
        return diaries;
    }

    public static MyDiaryListResponse from(List<DiaryEntity> diaryEntities) {
        return new MyDiaryListResponse(
                diaryEntities.stream()
                        .map(MyDiaryDetailResponse::from)
                        .toList()
        );
    }

    public static class MyDiaryDetailResponse {
        private final long id;
        private final String title;
        private final LocalDateTime createdAt;

        public MyDiaryDetailResponse(long id, String title, LocalDateTime createdAt) {
            this.id = id;
            this.title = title;
            this.createdAt = createdAt;
        }

        public static MyDiaryDetailResponse from(DiaryEntity diaryEntity) {
            return new MyDiaryDetailResponse(
                    diaryEntity.getId(),
                    diaryEntity.getTitle(),
                    diaryEntity.getCreatedAt()
            );
        }

        public long getId() {
            return id;
        }

        public String getTitle() {
            return title;
        }

        public LocalDateTime getCreatedAt() {
            return createdAt;
        }
    }
}
