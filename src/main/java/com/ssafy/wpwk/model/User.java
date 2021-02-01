package com.ssafy.wpwk.model;

import lombok.*;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class User {

    /**
     * 회원 기본키 id
     * */
    private Long id;

    /**
     * 회원 이메일
     * */
    private String email;

    /**
     * 회원 비밀번호
     * */
    private String password;

    /**
     * 회원 닉네임
     * */
    private String nickname;

    /**
     * 회원의 팔로잉 수
     * */
    private Long following;

    /**
     * 회원의 팔로우 수
     * */
    private Long followed;

    /**
     * 회원 상태(0:비활성화 / 1:활성화(일반고객) / 2:관리자)
     * */
    private int status;

    /**
     * 회원 이메일 인증키
     * */
    private String verificationKey;

    /**
     * 회원 생성일자
     * */
    private LocalDateTime createdAt;

    /**
     * 회원 생성자
     * */
    private String createdBy;

    /**
     * 회원정보 수정일자
     * */
    private LocalDateTime updatedAt;

    /**
     * 회원정보 수정자
     * */
    private String updatedBy;
}
