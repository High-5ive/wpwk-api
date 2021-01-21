package com.ssafy.wpwk.service;

import com.ssafy.wpwk.mappers.UserMapper;
import com.ssafy.wpwk.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userMapper;

    @Autowired
    PasswordEncoder passwordEncoder;

    @Override
    /** 사용자 회원가입 */
    public void insertUser(User user) {

        String encodedPassword = passwordEncoder.encode(user.getPassword());
        user.setPassword(encodedPassword);

        userMapper.insertUser(user);
    }

    @Override
    /** 이메일을 이용한 사용자 조회 */
    public User findUserByEmail(String email) {
        return userMapper.findUserByEmail(email);
    }

    @Override
    /** 사용자 로그인 */
    public User login(String email, String password) {
        return null;
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
}