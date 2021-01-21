package com.ssafy.wpwk.controller;

import com.ssafy.wpwk.model.Contents;
import com.ssafy.wpwk.model.PasswordChangeDTO;
import com.ssafy.wpwk.model.User;
import com.ssafy.wpwk.service.UserService;
import io.jsonwebtoken.Claims;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.net.URISyntaxException;

@CrossOrigin(origins = {"*"}, maxAge = 6000)
@RestController
public class UserController {

    @Autowired
    private UserService userService;

    /**
     * 사용자 생성 요청
     */
    @PostMapping("/users")
    public ResponseEntity<?> create(@RequestBody User resource) throws URISyntaxException {

        // 해당 이메일을 사용하는 User가 존재하는 경우
        if (userService.findUserByEmail(resource.getEmail()) != null) {
            return new ResponseEntity<>(HttpStatus.CONFLICT);
        }

        User user = User.builder().email(resource.getEmail())
                .password(resource.getPassword())
                .nickname(resource.getNickname())
                .createdBy("server1")
                .updatedBy("server1")
                .build();

        userService.insertUser(user);

        String url = "users/" + user.getId();
        return ResponseEntity.created(new URI(url)).body("user create successfully");
    }

    /**
     * 사용자 목록 전체조회
     */
    @GetMapping("/users")
    public ResponseEntity<?> list() {
        return ResponseEntity.ok(userService.findAll());
    }

    /**
     * 아이디를 이용한 사용자 조회
     */
    @GetMapping("/users/{id}")
    public ResponseEntity<?> getUserById(@PathVariable("id") Long id) throws URISyntaxException {

        User user = userService.findUserById(id);

        if (user == null) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }

        User findUser = User.builder().email(user.getEmail())
                .password(user.getPassword())
                .nickname(user.getNickname())
                .createdBy("server1")
                .updatedBy("server1")
                .build();

        return ResponseEntity.ok(findUser);
    }

    /**
     * 비밀번호 변경요청
     */
    @PostMapping("/users/changePassword/{id}")
    public ResponseEntity<?> changePassword(@PathVariable("id") Long id,
                                            @RequestBody PasswordChangeDTO passwordChangeDTO,
                                            Authentication authentication) {

        Claims claims = (Claims) authentication.getPrincipal();

        Long userId = claims.get("userId", Long.class);

        System.out.println(userId);
        System.out.println(passwordChangeDTO.getCurPassword());
        System.out.println(passwordChangeDTO.getNewPassword());
        // TODO: 비밀번호 변경 로직
        // 1. 해당 유저의 비밀번호 일치여부 확인
        // 2-1. 일치하지 않는 경우 -> NO_CONTENT 리턴
        // 2-2. 일치하는 경우
        // 3-1. 새 비밀번호와 현재 비밀번호가 일치하는 경우 -> CONFLICT 리턴
        // 3-2. 새 비밀번호와 현재 비밀번호가 일치하지 않는 경우 -> 새 비밀번호 변경 처리 후 OK리턴
        return null;
    }

    /**
     * 사용자 탈퇴(비활성화)
     */
    @DeleteMapping("/users/{id}")
    public ResponseEntity<?> deactivate(@PathVariable("id") Long id,
                                        Authentication authentication) {
        userService.deactivateUser(id);
        return ResponseEntity.ok(id);
    }

}
