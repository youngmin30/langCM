package com.jym.langCM.dto;

import lombok.Data;

@Data
public class MemberSaveForm {

    // 회원 가입시 필요한 정보를 필드로 넣기
    private String loginId;
    private String loginPw;
    private String name;
    private String nickname;
    private String email;

}
