package org.sopt.diary.api.dto.response.Member;

import org.sopt.diary.domain.SoptMember;

//Long, long
public class MemberIdResponse {
    private final Long id;

    public MemberIdResponse(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public static MemberIdResponse from(SoptMember member){
        return new MemberIdResponse(member.getId());
    }
}
