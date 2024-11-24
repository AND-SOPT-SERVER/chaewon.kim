package org.sopt.diary.api.dto.request.Member;

import jakarta.validation.constraints.NotBlank;

public class MemberLoginDto {
    @NotBlank(message = "아이디를 입력해주세요")
    private String username;
    @NotBlank(message = "비밀번호를 입력해주세요")
    private String password;

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }
}
