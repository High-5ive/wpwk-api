package com.ssafy.wpwk.controller;

import com.ssafy.wpwk.model.LoginRequestDTO;
import com.ssafy.wpwk.model.LoginResponseDTO;
import com.ssafy.wpwk.model.User;
import com.ssafy.wpwk.service.UserService;
import com.ssafy.wpwk.utils.JWTUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin(origins = {"*"}, maxAge = 6000)
public class LoginController {

    @Autowired
    private UserService userService;

    @Autowired
    private JWTUtil jwtUtil;

    /** 로그인 요청 */
    @PostMapping("/login")
    public ResponseEntity<?> userLogin(@RequestBody LoginRequestDTO resource) {

        String email = resource.getEmail();
        String password = resource.getPassword();

        User user = userService.login(email, password);
        // 1. 해당 이메일과 비밃번호가 일치하는 유저가 존재하지 않는 경우 -> 204 (이메일 또는 비밀번호가 일치하지 않습니다)
        if(user == null) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }

        // 2. 비활성화 여부 확인 -> 401 UNAUTHORIZED Message 전송
        if(user.getStatus() == 0) {
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        }

        // 3. 회원 인증 성공 --> token발급
        String accessToken = jwtUtil.createToken(user.getId(), user.getEmail(), user.getNickname());
        LoginResponseDTO loginResponseDTO = LoginResponseDTO.builder().accessToken(accessToken).build();

        return new ResponseEntity<>(loginResponseDTO, HttpStatus.CREATED);
    }
}
