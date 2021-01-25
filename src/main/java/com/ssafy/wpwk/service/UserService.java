package com.ssafy.wpwk.service;

import com.ssafy.wpwk.model.AbilityRequestDTO;
import com.ssafy.wpwk.model.AbilityResponseDTO;
import com.ssafy.wpwk.model.User;

import javax.mail.MessagingException;
import java.io.UnsupportedEncodingException;
import java.util.List;

public interface UserService {

    /**
     * 사용자 회원 가입
     */
    void insertUser(User user) throws UnsupportedEncodingException, MessagingException;

    /**
     * 이메일을 이용한 사용자 조회
     */
    User findUserByEmail(String email);

    /**
     * 사용자 로그인
     */
    User login(String email, String password);

    /**
     * 아이디를 이용한 사용자 조회
     */
    User findUserById(Long userId);

    /**
     * 사용자 목록 전체 조회
     */
    List<User> findAll();

    /**
     * 사용자 비활성화
     */
    void deactivateUser(Long id);

    /**
     * 사용자 비밀번호 변경
     */
    void changePassword(User user);

    /**
     * 사용자 역량 수정
     */
    void updateUserAbilities(Long id, AbilityRequestDTO contentsAbilityDTO);

    /**
     * 사용자 인증
     */
    void verification(Long id, String key);

    /**
     * 사용자 역량 정보 조회
     */
    AbilityResponseDTO findUserAbilitiesById(Long id);

}
