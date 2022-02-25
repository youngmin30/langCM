package com.jym.langCM.service;

import com.jym.langCM.config.Role;
import com.jym.langCM.dao.MemberRepository;
import com.jym.langCM.domain.Member;
import com.jym.langCM.dto.MemberSaveForm;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor

public class MemberService implements UserDetailsService {

    private final MemberRepository memberRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return memberRepository.findByLoginId(username).get();
    }
    // 회원 중복 확인 다음에 회원 가입
    /**
     * 회원 중복 확인
     * // MemberRepository 회원 아이디, 닉네임, 이메일 중복 체크를 위해 추가함과 연결
     * @param loginId
     * @param nickname
     * @param email
     */
    public void isDuplicateMember(String loginId, String nickname, String email){
        if(memberRepository.existByLoginId(loginId)){
            throw new IllegalStateException("이미 존재하는 아이디입니다.");
        } else if(memberRepository.existByNickname(nickname)){
            throw new IllegalStateException("이미 존재하는 아이디입니다.");
        } else if(memberRepository.existByEmail(email)){
            throw new IllegalStateException("이미 존재하는 이메일입니다.");
        }

    }

    /**
     * 회원 가입 로직
     * @param memberSaveForm
     */
    public void save(MemberSaveForm memberSaveForm) throws IllegalStateException{

        isDuplicateMember(
                memberSaveForm.getLoginId(),
                memberSaveForm.getNickname(),
                memberSaveForm.getEmail()
        );

        BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();

        Member member = Member.createMember(
                memberSaveForm.getLoginId(),
                bCryptPasswordEncoder.encode(memberSaveForm.getLoginPw()),
                memberSaveForm.getName(),
                memberSaveForm.getNickname(),
                memberSaveForm.getEmail(),
                Role.MEMBER
        );

        memberRepository.save(member);

    }

}