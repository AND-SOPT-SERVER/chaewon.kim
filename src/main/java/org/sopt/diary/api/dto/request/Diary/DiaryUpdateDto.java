package org.sopt.diary.api.dto.request.Diary;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public class DiaryUpdateDto {
    @NotBlank(message = "제목을 입력해주세요.")
    @Size(max = 30, message = "제목은 30자 이하로 작성해야 합니다.")
    private String title;
    @NotBlank(message = "내용을 입력해주세요.")
    @Size(max = 100, message = "내용은 100자 이하로 작성해야 합니다.")
    private String content;

    public DiaryUpdateDto(String title, String content) {
        this.title = title;
        this.content = content;
    }

    public String getTitle() {
        return title;
    }

    public String getContent() {
        return content;
    }
}
