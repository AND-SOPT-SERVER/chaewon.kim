package org.sopt.diary.api.service;

import org.sopt.diary.api.dto.response.Member.MemberIdResponse;
import org.sopt.diary.common.exception.BusinessException;
import org.sopt.diary.common.exception.MemberErrorCode;
import org.sopt.diary.domain.SoptMember;
import org.sopt.diary.domain.repository.MemberRepository;
import org.springframework.stereotype.Component;

@Component
public class MemberService {
    private final MemberRepository memberRepository;

    public MemberService(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

    // 회원가입
    public void signup(
            final String username, final String password, final String nickname, final int age
    ) {
        validateDuplicateMember(username, nickname);
        SoptMember soptMember = new SoptMember(username, password, nickname, age);
        memberRepository.save(soptMember);
    }

    // 로그인
    public MemberIdResponse login(final String username, final String password) {
        SoptMember member = findMemberByUsername(username);

        if (!password.equals(member.getPassword())) {
            throw new BusinessException(MemberErrorCode.MEMBER_UNAUTHORIZED);
        }
        return MemberIdResponse.from(member);
    }

    //중복 검사
    private void validateDuplicateMember(String username, String nickname) {
        // 아이디(username) 중복 검사
        if (memberRepository.existsByUsername(username)) {
            throw new BusinessException(MemberErrorCode.USERNAME_DUPLICATE_CONFLICT);
        }

        // 닉네임(nickname) 중복 검사
        if (memberRepository.existsByNickname(nickname)) {
            throw new BusinessException(MemberErrorCode.NICKNAME_DUPLICATE_CONFLICT);
        }
    }

    private SoptMember findMemberByUsername(final String username) {
        return memberRepository.findByUsername(username)
                .orElseThrow(() -> new BusinessException(MemberErrorCode.MEMBER_NOT_FOUND));
    }
}
