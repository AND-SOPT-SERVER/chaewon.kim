package org.sopt.diary.api.validator;

public class DiaryValidator {

    public static void validateTitle(String title) {
        if (title == null || title.length() > 30) {
            throw new IllegalArgumentException("제목은 30자 이하로 작성해야 합니다.");
        }
    }

    public static void validateContent(String content) {
        if (content == null || content.length() > 100) {
            throw new IllegalArgumentException("내용은 100자 이하로 작성해야 합니다.");
        }
    }
}
