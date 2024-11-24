package org.sopt.diary.domain;

import org.sopt.diary.common.exception.BusinessErrorCode;
import org.sopt.diary.common.exception.BusinessException;

public enum Category {
    FOOD("음식"),
    SCHOOL("학교"),
    MOVIE("영화"),
    EXERCISE("운동")
    ;

    private final String name;

    Category(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public static Category fromName(String name) {
        for (Category category : Category.values()) {
            if (category.getName().equals(name)) {
                return category;
            }
        }
        throw new BusinessException(BusinessErrorCode.BAD_REQUEST);
    }
}
