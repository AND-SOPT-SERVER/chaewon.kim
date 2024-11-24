package org.sopt.diary.domain.repository;

import org.sopt.diary.domain.SoptMember;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface MemberRepository extends JpaRepository<SoptMember, Long> {

    boolean existsByUsername(String username);

    boolean existsByNickname(String nickname);

    Optional<SoptMember> findByUsername(String username);
}
