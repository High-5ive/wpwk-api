package com.ssafy.wpwk.service;

import com.ssafy.wpwk.mappers.UserMapper;
import com.ssafy.wpwk.model.ContentsAbilityDTO;
import com.ssafy.wpwk.model.User;
import com.ssafy.wpwk.model.UserAbilityDTO;
import com.ssafy.wpwk.utils.VerificationKeyUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
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

    @Override
    /** 사용자 회원가입 */
    public void insertUser(User user) throws UnsupportedEncodingException, MessagingException {

        String encodedPassword = passwordEncoder.encode(user.getPassword());
        user.setPassword(encodedPassword);

        String verificationKey = VerificationKeyUtil.getKey(); // 인증키 발급
        user.setVerificationKey(verificationKey);

        userMapper.insertUser(user);

        // TODO: 인증메일 전송요청처리
        mailService.sendAuthMail(user, verificationKey);
    }

    @Override
    /** 이메일을 이용한 사용자 조회 */
    public User findUserByEmail(String email) {
        return userMapper.findUserByEmail(email);
    }

    @Override
    /** 사용자 로그인 */
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

    @Override
    /** 아이디를 이용한 사용자 조회 */
    public User findUserById(Long userId) {
        return userMapper.findUserById(userId);
    }

    @Override
    /** 사용자 목록 전체 조회 */
    public List<User> findAll() {
        return userMapper.findAll();
    }

    @Override
    public void deactivateUser(Long id) {
        userMapper.deactivateUser(id);
    }

    @Override
    public void changePassword(Long id, String newPassword) {
        String encodedPassword = passwordEncoder.encode(newPassword);
        userMapper.changePassword(id, encodedPassword);
    }

    @Override
    public void verification(Long id, String key) {
        userMapper.verification(id, key);
    }

    @Override
    public void updateUserAbilities(Long id, ContentsAbilityDTO contentsAbilityDTO) {

        userMapper.updateUserAbilities(id, contentsAbilityDTO);

    }


    @Override
    public ContentsAbilityDTO findUserAbilitiesById(Long id) {
        return userMapper.findUserAbilitiesById(id);
    }

}