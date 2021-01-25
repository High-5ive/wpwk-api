package com.ssafy.wpwk.service;

import com.ssafy.wpwk.mappers.UserMapper;
import com.ssafy.wpwk.model.AbilityRequestDTO;
import com.ssafy.wpwk.model.AbilityResponseDTO;
import com.ssafy.wpwk.model.User;
import com.ssafy.wpwk.utils.VerificationKeyUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.mail.MessagingException;
import java.io.UnsupportedEncodingException;
import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private MailService mailService;

    /**
     * 사용자 회원가입
     */
    @Override
    public void insertUser(User user) throws UnsupportedEncodingException, MessagingException {

        String encodedPassword = passwordEncoder.encode(user.getPassword());
        user.setPassword(encodedPassword);

        String verificationKey = VerificationKeyUtil.getKey(); // 인증키 발급
        user.setVerificationKey(verificationKey);

        userMapper.insertUser(user);

        // TODO: 인증메일 전송요청처리
        mailService.sendAuthMail(user, verificationKey);
    }

    /**
     * 이메일을 이용한 사용자 조회
     */
    @Override
    public User findUserByEmail(String email) {
        return userMapper.findUserByEmail(email);
    }

    /**
     * 사용자 로그인
     */
    @Override
    public User login(String email, String password) {
        User user = userMapper.findUserByEmail(email);

        if (user == null) {
            return null;
        } // 해당 이메일로 유저가 조회되지 않은 경우

        if (!passwordEncoder.matches(password, user.getPassword())) { // 비밀번호가 틀린 경우
            return null;
        }

        return user;
    }

    /**
     * 아이디를 이용한 사용자 조회
     */
    @Override
    public User findUserById(Long userId) {
        return userMapper.findUserById(userId);
    }

    /**
     * 사용자 목록 전체 조회
     */
    @Override
    public List<User> findAll() {
        return userMapper.findAll();
    }

    /**
     * 사용자 회원 탈퇴
     */
    @Override
    public void deactivateUser(Long id) {
        userMapper.deactivateUser(id);
    }

    /**
     * 사용자 비밀번호 변경
     */
    @Override
    public void changePassword(Long id, String newPassword) {
        String encodedPassword = passwordEncoder.encode(newPassword);
        userMapper.changePassword(id, encodedPassword);
    }

    /**
     * 사용자 인증
     */
    @Override
    public void verification(Long id, String key) {
        userMapper.verification(id, key);
    }

    /**
     * 사용자 역량 정보 수정
     */
    @Override
    public void updateUserAbilities(Long id, AbilityRequestDTO abilityDTO) {
        userMapper.updateUserAbilities(id, abilityDTO);
    }

    /**
     * ID를 이용한 사용자 역량 정보 조회
     */
    @Override
    public AbilityResponseDTO findUserAbilitiesById(Long id) {
        return userMapper.findUserAbilitiesById(id);
    }


    /**
     * 사용자 비밀번호 변경을 위한 사용자 이메일 인증
     */
    @Override
    public void findPasswordConfirm(User user) throws UnsupportedEncodingException, MessagingException {
        // 1.임의의 키 새롭게 발금
        String tempKey= VerificationKeyUtil.getKey();
        // 2.요청한 유저의 verificationKey 값을 임의로 변경
        user.setVerificationKey(tempKey);
        userMapper.updateKey(user);
        // 3.요청한 회원의 이메일로 임의의 키값을 전송
        mailService.sendResetPasswordMail(user);
    }
}