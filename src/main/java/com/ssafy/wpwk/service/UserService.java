package com.ssafy.wpwk.service;

import com.ssafy.wpwk.model.ContentsAbilityDTO;
import com.ssafy.wpwk.model.User;
import com.ssafy.wpwk.model.UserAbilityDTO;

import javax.mail.MessagingException;
import java.io.UnsupportedEncodingException;
import java.util.List;

public interface UserService {

    /**
    void insertUser(User user) throws UnsupportedEncodingException, MessagingException;
     */

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
    void changePassword(Long id, String newPassword);

    void updateUserAbilities(Long id, ContentsAbilityDTO contentsAbilityDTO);

    /** 사용자 인증 */
    void verification(Long id, String key);
    ContentsAbilityDTO findUserAbilitiesById(Long id);

}
