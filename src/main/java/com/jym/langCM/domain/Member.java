package com.jym.langCM.domain;

import com.jym.langCM.config.Role;
import lombok.AccessLevel;

import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Member implements UserDetails {

    @Id // 2 DB의 PK에 연결할 ID 만들기
    @GeneratedValue(strategy = GenerationType.IDENTITY) // 2 DB의 PK에 연결할 ID 만들기
    private Long id; // 2 DB의 PK에 연결할 ID 만들기

    private String loginId; // 1 오버라이드된 메소드의 필드 만들기
    private String loginPw; // 1 오버라이드된 메소드의 필드 만들기

    private String name; // 3 Member로 받을 나머지 값 넣기
    private String nickname; // 3 Member로 받을 나머지 값 넣기
    private String email; // 3 Member로 받을 나머지 값 넣기

    // config/Role.enum을 만든 뒤
    @Enumerated(EnumType.STRING)
    private Role authority;

    private boolean isAccountNonExpired = true;
    private boolean isAccountNonLocked = true;
    private boolean isCredentialsNonExpired = true;
    private boolean isEnabled = true;

    // 생성 메소드 만들기
    public static Member createMember(String loginId, String loginPw, String name, String nickname, String email, Role authority) {
        Member member = new Member();

        member.loginId = loginId;
        member.loginPw = loginPw;

        member.name = name;
        member.nickname = nickname;
        member.email = email;

        member.authority = authority;

        return member;

    }


    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {

        List<GrantedAuthority> authorities = new ArrayList<>();
        authorities.add(new SimpleGrantedAuthority(this.authority.getValue()));

        return authorities;
    }

    // 오버라이드 단축키 (ctrl + i)
    @Override
    public String getPassword() {
        return null;
    }

    @Override
    public String getUsername() {
        return null;
    }

    // 아래 메소드는 자동으로 오버라이드 되지 않았음
    @Override // 직접 작성함
    public boolean isAccountNonExpired() {
        return false;
    }

    @Override // 직접 작성함
    public boolean isAccountNonLocked() {
        return false;
    }

    @Override // 직접 작성함
    public boolean isCredentialsNonExpired() {
        return false;
    }

    @Override // 직접 작성함
    public boolean isEnabled() {
        return false;
    }

}

