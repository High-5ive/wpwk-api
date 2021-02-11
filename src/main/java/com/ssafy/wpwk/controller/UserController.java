package com.ssafy.wpwk.controller;

import com.ssafy.wpwk.model.*;
import com.ssafy.wpwk.service.UserService;
import io.jsonwebtoken.Claims;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import javax.mail.MessagingException;
import java.io.UnsupportedEncodingException;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.ssafy.wpwk.utils.ExceptionUtil.isInValidAuthentication;


@CrossOrigin(origins = {"*"}, maxAge = 6000)
@RestController
public class UserController {

    @Autowired
    private UserService userService;

    @Value("${INIT_KEY}")
    private String INIT_KEY;

    /**
     * 사용자 생성 요청
     */
    @ApiOperation(value = "회원 가입 및 이메일 인증 요청")
    @PostMapping("/users")
    public ResponseEntity<?> create(@RequestBody User resource) {
        //todo 500에러 발생 추가 구현 필요...
        //해당 이메일을 사용하는 User가 존재하는 경우

        if (userService.findUserByEmail(resource.getEmail()) != null) {
            return new ResponseEntity<>(HttpStatus.CONFLICT);
        }

        User user = User.builder()
                .email(resource.getEmail())
                .password(resource.getPassword())
                .nickname(resource.getNickname())
                .status(0)
                .build();

        try {
            userService.insertUser(user, true);
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        } catch (MessagingException e) {
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    /**
     * 사용자 목록 전체조회
     */
    @ApiOperation(value = "사용자 목록 전체 조회")
    @GetMapping("/users")
    public ResponseEntity<?> list() {
        return new ResponseEntity<>(userService.findAll(), HttpStatus.OK);
    }

    /**
     * 아이디를 이용한 사용자 조회
     */
    @ApiOperation(value = "아이디를 이용한 사용자 조회")
    @GetMapping("/users/{id}")
    public ResponseEntity<?> getUserById(@PathVariable("id") Long id, Authentication authentication) {

        User user = userService.findUserById(id);
        if (user == null) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        Claims claims = (Claims) authentication.getPrincipal();
        Long userId = claims.get("userId", Long.class);

        User isFollowUser = userService.findFollowById(id, userId);

        boolean isFollowed = false;
        if (isFollowUser != null) {
            isFollowed = true;
        }

        User findUser = User.builder().email(user.getEmail())
                .id(user.getId())
                .nickname(user.getNickname())
                .createdBy(user.getCreatedBy())
                .createdAt(user.getCreatedAt())
                .updatedBy(user.getUpdatedBy())
                .updatedAt(user.getUpdatedAt())
                .build();
        Map<String, Object> map = new HashMap<>();
        map.put("findUser", findUser);
        map.put("isFollowed", isFollowed);
        return new ResponseEntity<>(map, HttpStatus.OK);
    }

    /**
     * 비밀번호 변경요청
     */
    @ApiOperation(value = "비밀번호 변경 요청")
    @PutMapping("/users/changePassword")
    public ResponseEntity<?> changePassword(@RequestBody PasswordChangeDTO passwordChangeDTO,
                                            Authentication authentication) {

        if (isInValidAuthentication(authentication)) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        Claims claims = (Claims) authentication.getPrincipal();

        String email = claims.get("email", String.class);

        String curPassword = passwordChangeDTO.getCurPassword();
        String newPassword = passwordChangeDTO.getNewPassword();

        // TODO: 비밀번호 변경 로직
        // 1. 해당 유저의 비밀번호 일치여부 확인
        User user = userService.login(email, passwordChangeDTO.getCurPassword());

        // 2-1. 일치하지 않는 경우 -> NO_CONTENT 리턴
        if (user == null) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        // 2-2. 일치하는 경우
        // 3-1. 새 비밀번호와 현재 비밀번호가 일치하는 경우 -> CONFLICT 리턴
        if (curPassword.equals(newPassword)) {
            return new ResponseEntity<>(HttpStatus.CONFLICT); // 현재 비밀번호와 새 비밀번호가 일치한 경우 -> 409 CONFLICT
        }
        // 3-2. 새 비밀번호와 현재 비밀번호가 일치하지 않는 경우 -> 새 비밀번호 변경 처리 후 OK리턴
        user.setPassword(newPassword);
        setUpdate(user);
        userService.changePassword(user);

        return new ResponseEntity<>(HttpStatus.OK);
    }

    /**
     * 사용자 탈퇴(비활성화)
     */
    @ApiOperation(value = "사용자 회원 탈퇴(비활성화)")
    @DeleteMapping("/users")
    public ResponseEntity<?> deactivate(Authentication authentication) {

        if (isInValidAuthentication(authentication)) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        Claims claims = (Claims) authentication.getPrincipal();

        Long id = claims.get("userId", Long.class);

        userService.deactivateUser(id);
        return ResponseEntity.ok(id);
    }

    @ApiOperation(value = "사용자,컨텐츠의 역량 정보 업데이트")
    @PutMapping("/users/contentsEnd")
    public ResponseEntity<?> contentsEnd(@RequestBody ContentsEndRequestDTO requestDTO,
                                        Authentication authentication) {

        if (isInValidAuthentication(authentication)) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        Claims claims = (Claims) authentication.getPrincipal();

        Long id = claims.get("userId", Long.class);

        userService.contentsEnd(id, requestDTO);

        return new ResponseEntity<>(HttpStatus.OK);
    }

    @ApiOperation(value = "사용자 역량 정보 조회")
    @GetMapping("/users/abilities")
    public ResponseEntity<?> findUserAbilities(Authentication authentication) {

        if (isInValidAuthentication(authentication)) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        Claims claims = (Claims) authentication.getPrincipal();

        Long id = claims.get("userId", Long.class);
        AbilityResponseDTO abilities = userService.findUserAbilitiesById(id);
        if (abilities == null) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(abilities, HttpStatus.OK);
    }

    /**
     * 사용자 이메일 인증 요청
     */
    @ApiOperation(value = "사용자 이메일 인증 요청")
    @GetMapping("/users/confirm")
    public ResponseEntity<?> confirm(@RequestParam("uid") Long userId,
                                     @RequestParam("verificationKey") String verificationKey) {
        User user = userService.findUserById(userId);

        if (verificationKey.equals(user.getVerificationKey())) { // authKey가 같은 경우 -> 응답코드 201
            userService.verification(userId, INIT_KEY); // 사용자 인증
            return new ResponseEntity<>(HttpStatus.OK);
        } else if (INIT_KEY.equals(user.getVerificationKey())) { // 이미 인증을 한 경우 -> 응답코드 208
            return new ResponseEntity<>(HttpStatus.ALREADY_REPORTED);
        } else { // 인증코드가 다른 경우 -> 응답코드 400
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    /**
     * 사용자 비밀번호 변경 요청 (비밀번호를 잊어버린 경우)
     */
    @ApiOperation(value = "사용자 비밀번호 변경 요청(비밀번호를 잊어버린 경우)")
    @PutMapping("/users/changePassword2")
    public ResponseEntity<?> changePassword2(@RequestBody LoginRequestDTO resource) {
        // 1. 해당 이메일로 사용자 검색
        User user = userService.findUserByEmail(resource.getEmail());

        if (user == null) { // 해당 사용자가 없는 경우
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }

        // 2. 해당 사용자의 비밀번호 변경
        user.setPassword(resource.getPassword());
        setUpdate(user);
        userService.changePassword(user);

        return new ResponseEntity<>(HttpStatus.OK);
    }

    /**
     * 사용자 비밀번호 찾기 이메일 인증 요청
     */
    @ApiOperation(value = "사용자 비밀번호 찾기를 위한 이메일 인증 요청")
    @PostMapping("/users/findPassword")
    public ResponseEntity<?> findPasswordConfirm(@RequestBody Map<String, Object> map) throws UnsupportedEncodingException, MessagingException {
        String email = (String) map.get("email");
        User user = userService.findUserByEmail(email);

        if (user == null) { //회원가입한 회원의 email이 아닌경우 NO_CONTENT 응답
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } else {
            //사용자의 업데이트 시간 수정
            setUpdate(user);
            //사용자 verificationKey값 변경 및 email인증 요청
            userService.findPasswordConfirm(user);
            return new ResponseEntity<>(HttpStatus.OK);
        }
    }

    /**
     * 사용자 팔로잉 요청
     */
    @ApiOperation(value = "사용자 팔로잉 요청")
    @PostMapping("/users/following/{id}")
    public ResponseEntity<?> requestFollowing(@PathVariable("id") Long id, Authentication authentication) {

        if (isInValidAuthentication(authentication)) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        //팔로우 신청당하는 사용자의 ID
        Long toUserId = id;
        Claims claims = (Claims) authentication.getPrincipal();

        //팔로우 신청한 사용자의 ID
        Long fromUserId = claims.get("userId", Long.class);

        if (userService.requestFollowing(toUserId, fromUserId))
            return new ResponseEntity<>(HttpStatus.OK);
        else {
            //팔로잉을 이미 한경우 다시 팔로잉 신청 할 경우 언팔로잉이 된다.
            return new ResponseEntity<>(HttpStatus.ALREADY_REPORTED);
        }
    }

    /**
     * 사용자 팔로워 or 팔로잉 리스트 정보 조회
     */
    @ApiOperation(value = "사용자 팔로우&팔로워 리스트 정보 조회")
    @GetMapping("/users/follow")
    public ResponseEntity<?> findFollowerListById(@RequestBody Map<String, Object> map, Authentication authentication) {
        List<User> followerList;
        Claims claims = (Claims) authentication.getPrincipal();

        Long id = claims.get("userId", Long.class);

        String option = (String) map.get("option");
        followerList = userService.findFollowListById(id, option);
        return new ResponseEntity<>(followerList, HttpStatus.OK);
    }

    /**
     * 사용자 정보 업데이트시 공통 호출 메서드
     */
    public void setUpdate(User user) {
        user.setUpdatedBy("server1");
        user.setUpdatedAt(LocalDateTime.now());
    }

}