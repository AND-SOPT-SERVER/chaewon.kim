package org.sopt.diary.api.dto.request.Member;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public class MemberCreateDto {
    @NotBlank(message = "아이디를 입력해주세요.")
    @Size(max = 10, message = "아이디는 10자 이하로 작성해야 합니다.")
    private String username;
    @NotBlank
    private String password;
    @NotBlank(message = "닉네임을 입력해주세요.")
    @Size(max = 10, message = "닉네임은 10자 이하여야 합니다.")
    private String nickname;
    @NotNull(message = "나이를 입력해주세요.")
    private int age;

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public String getNickname() {
        return nickname;
    }

    public int getAge() {
        return age;
    }
}
