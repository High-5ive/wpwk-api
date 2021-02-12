package com.ssafy.wpwk.controller;


import com.ssafy.wpwk.model.LoginRequestDTO;
import com.ssafy.wpwk.model.LoginResponseDTO;
import com.ssafy.wpwk.model.User;
import com.ssafy.wpwk.service.UserService;
import com.ssafy.wpwk.utils.JWTUtil;
import io.jsonwebtoken.Claims;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.security.core.Authentication;

import org.springframework.web.bind.annotation.*;



@CrossOrigin(origins = {"*"}, maxAge = 6000)
@RestController
public class LoginController {

    @Autowired
    private UserService userService;

    @Autowired
    private JWTUtil jwtUtil;



    /**
     * 로그인 요청
     */
    @ApiOperation(value = "사용자 로그인 시도")
    @PostMapping("/login")
    public ResponseEntity<?> userLogin(@RequestBody LoginRequestDTO resource) {

        String email = resource.getEmail();
        String password = resource.getPassword();

        User user = userService.login(email, password);
        // 1. 해당 이메일과 비밃번호가 일치하는 유저가 존재하지 않는 경우 -> 204 (이메일 또는 비밀번호가 일치하지 않습니다)
        if (user == null) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }

        // 2. 이메일 인증이 되지 않은 경우 -> 403 FORBIDDEN "deactivate" Message 전송
        if (user.getStatus() == 0) {
            return new ResponseEntity<>("email", HttpStatus.FORBIDDEN);
        }

        // 3. 회원 인증 성공 --> token발급
        String accessToken = jwtUtil.createToken(user.getId(), user.getEmail(), user.getNickname());
        LoginResponseDTO loginResponseDTO = LoginResponseDTO.builder().accessToken(accessToken).build();

        return new ResponseEntity<>(loginResponseDTO, HttpStatus.CREATED);
    }

    /**
     * 로그인한 유저정보 요청
     */
    @ApiOperation(value = "로그인한 유저정보 요청")
    @GetMapping("/loginUser/{id}")
    public ResponseEntity<?> loginUser(@PathVariable("id") Long id, Authentication authentication) {

        Claims claims = (Claims) authentication.getPrincipal();

        Long userId = claims.get("userId", Long.class);
        if (id != userId) {
            return new ResponseEntity<>(HttpStatus.FORBIDDEN);
        }

        User user = userService.findUserById(userId);
        User loginUser = User.builder()
                .id(user.getId())
                .nickname(user.getNickname())
                .email(user.getEmail())
                .build();

        return new ResponseEntity<>(loginUser, HttpStatus.OK);
    }
}
