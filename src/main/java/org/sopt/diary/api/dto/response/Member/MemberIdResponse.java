package org.sopt.diary.api.dto.response.Member;

import org.sopt.diary.domain.SoptMember;

public class MemberIdResponse {
    private final long id;

    public MemberIdResponse(long id) {
        this.id = id;
    }

    public long getId() {
        return id;
    }

    public static MemberIdResponse from(SoptMember member){
        return new MemberIdResponse(member.getId());
    }
}
