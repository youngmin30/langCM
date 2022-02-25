package com.jym.langCM.dao;

import com.jym.langCM.domain.Member;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface MemberRepository extends JpaRepository<Member, Long> {

    Optional<Member> findByLoginId(String loginId);

    // 회원 아이디, 닉네임, 이메일 중복 체크를 위해 추가함
    boolean existByLoginId(String loginId);
    boolean existByNickname(String nickname);
    boolean existByEmail(String email);


}


