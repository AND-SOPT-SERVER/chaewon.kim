package org.sopt.diary.api.controller;

import jakarta.validation.Valid;
import org.sopt.diary.api.dto.ResponseDto;
import org.sopt.diary.api.dto.request.Member.MemberCreateDto;
import org.sopt.diary.api.dto.request.Member.MemberLoginDto;
import org.sopt.diary.api.dto.response.Member.MemberIdResponse;
import org.sopt.diary.api.service.MemberService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

//TODO: 비밀번호 해시화, Long이랑 long, 카테고리,
@RestController
@RequestMapping("/luckybicky")
public class MemberController {
    private final MemberService memberService;

    public MemberController(MemberService memberService) {
        this.memberService = memberService;
    }

    // 회원가입
    @PostMapping("/signup")
    public ResponseEntity<ResponseDto<Void>> signup(
            @Valid @RequestBody final MemberCreateDto memberCreateDto
    ) {
        memberService.signup(
                memberCreateDto.getUsername(),
                memberCreateDto.getPassword(),
                memberCreateDto.getNickname(),
                memberCreateDto.getAge()
        );
        return ResponseEntity.status(HttpStatus.CREATED).body(ResponseDto.success());
    }

    // 로그인
    @PostMapping("/login")
    public ResponseEntity<ResponseDto<MemberIdResponse>> login(
            @Valid @RequestBody final MemberLoginDto memberLoginDto
    ) {
        return ResponseEntity.status(HttpStatus.OK).
                body(ResponseDto.success(memberService.login(memberLoginDto.getUsername(), memberLoginDto.getPassword())));
    }
}
