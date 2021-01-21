package com.ssafy.wpwk.controller;

import com.ssafy.wpwk.model.User;
import com.ssafy.wpwk.service.UserService;
import io.jsonwebtoken.Claims;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin(origins = {"*"}, maxAge = 6000)
public class LoginController {

    @Autowired
    private UserService userService;

    /** 로그인 요청 */
    @PostMapping
    public ResponseEntity<?> userLogin(Authentication authentication) {

        Claims claims = (Claims) authentication.getPrincipal();

        Long userId = claims.get("userId", Long.class);

        User user = userService.findUserById(userId);

        if(user == null) { // 해당 유저가 없는 경우 (잘못된 토큰)
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        
        User loginUser = User.builder().email(user.getEmail())
                                       .nickname(user.getNickname())
                                       .func(user.getFunc())
                                       .status(user.getStatus())
                                       .build();

        return ResponseEntity.ok().body(loginUser);
    }
}
