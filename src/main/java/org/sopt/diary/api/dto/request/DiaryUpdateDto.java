package org.sopt.diary.api.dto.request;

import org.sopt.diary.api.validator.DiaryValidator;

public class DiaryUpdateDto {
    private String title;
    private String content;

    public DiaryUpdateDto(String title, String content) {
        DiaryValidator.validateTitle(title);
        DiaryValidator.validateContent(content);
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
