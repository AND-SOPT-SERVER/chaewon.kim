package org.sopt.diary.api.dto.request.Diary;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public class DiaryCreateDto {
    @NotBlank(message = "제목을 입력해주세요.")
    @Size(max = 30, message = "제목은 30자 이하로 작성해야 합니다.")
    private final String title;
    @NotBlank(message = "내용을 입력해주세요.")
    @Size(max = 100, message = "내용은 100자 이하로 작성해야 합니다.")
    private final String content;
    private final String category;
    private final boolean isPublic;

    public DiaryCreateDto(String title, String content, String category, boolean isPublic) {
        this.title = title;
        this.content = content;
        this.category = category;
        this.isPublic = isPublic;
    }

    public String getTitle() {
        return title;
    }

    public String getContent() {
        return content;
    }

    public String getCategory() {
        return category;
    }

    public boolean isPublic() {
        return isPublic;
    }
}
