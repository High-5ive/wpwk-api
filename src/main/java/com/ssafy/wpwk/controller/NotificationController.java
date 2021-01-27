package com.ssafy.wpwk.controller;

import com.ssafy.wpwk.enums.MessageType;
import com.ssafy.wpwk.model.Notification;
import com.ssafy.wpwk.model.User;
import com.ssafy.wpwk.service.NotificationService;
import com.ssafy.wpwk.service.UserService;
import io.jsonwebtoken.Claims;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static com.ssafy.wpwk.utils.ExceptionUtil.isInValidAuthentication;

@CrossOrigin(origins = {"*"}, maxAge = 6000)
@RestController
public class NotificationController {

    @Autowired
    private NotificationService notificationService;

    @Autowired
    private UserService userService;

    // 1. 공지사항 전체 조회
    @ApiOperation(value = "공지사항 전체 조회")
    @GetMapping("/notifications")
    public ResponseEntity<?> list() {
        List<Notification> notificationList = notificationService.findAll();

        return notificationList.size() == 0 ?
                new ResponseEntity<>(HttpStatus.NO_CONTENT) :
                new ResponseEntity<>(notificationList, HttpStatus.OK);
    }
    
    // 2. 사용자별 공지 조회
    @ApiOperation(value = "사용자별 공지 조회")
    @GetMapping("/notifications/users/{userId}")
    public ResponseEntity<?> listByUserId(@PathVariable("userId")Long id) {
        List<Notification> notificationList = notificationService.findAllByUserId(id);

        return notificationList.size() == 0 ?
                new ResponseEntity<>(HttpStatus.NO_CONTENT) :
                new ResponseEntity<>(notificationList, HttpStatus.OK);
    }

    // 3. 사용자별 확인하지 않은 공지사항 전체 조회
    @ApiOperation(value = "사용자별 확인하지 않은 공지사항 전체 조회")
    @GetMapping("/notifications/main")
    public ResponseEntity<?> listByUserIdAndNotRead(
            Authentication authentication
    ) {

        if(isInValidAuthentication(authentication)) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        Claims claims = (Claims) authentication.getPrincipal();
        Long userId = claims.get("userId", Long.class);

        List<Notification> notificationList = notificationService.findAllByUserIdAndNotRead(userId);

        return notificationList.size() == 0 ?
                new ResponseEntity<>(HttpStatus.NO_CONTENT) :
                new ResponseEntity<>(notificationList, HttpStatus.OK);
    }

    // 4. 공지사항 브로드캐스팅
    @ApiOperation(value = "공지사항 브로드캐스팅")
    @PostMapping("/notifications/broadcast")
    public ResponseEntity<?> broadcast(
            @RequestBody Notification resource,
            Authentication authentication
    ) {

        // 인증 토큰 유효 체크
        if(isInValidAuthentication(authentication)) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        Claims claims = (Claims) authentication.getPrincipal();
        Long userId = claims.get("userId", Long.class);
        
        User user = userService.findUserById(userId);

        // 관리자가 아닌 경우
        if(user.getStatus() != 2) {
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED); // UNAUTHORIZED 401 권한이 없음을 명시
        }

        Notification notification = Notification.builder()
                                                .fromUser(user)
                                                .message(resource.getMessage())
                                                .messageType(MessageType.ADMIN_MESSAGE)
                                                .createdBy("server1")
                                                .build();

        notificationService.broadcast(notification); // broadcasting
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    // 5. 공지사항 특정 수신자에게 작성
    @ApiOperation(value = "공지사항 특정 수신자에게 작성")
    @PostMapping("/notifications/target/{targetId}")
    public ResponseEntity<?> send(
            @RequestBody Notification resource,
            @PathVariable("targetId") Long targetId,
            Authentication authentication
    ) {

        // 인증 토큰 유효 체크
        if(isInValidAuthentication(authentication)) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        Claims claims = (Claims) authentication.getPrincipal();
        Long userId = claims.get("userId", Long.class);

        User user = userService.findUserById(userId);

        // 관리자가 아닌 경우
        if(user.getStatus() != 2) {
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED); // UNAUTHORIZED 401 권한이 없음을 명시
        }

        User targetUser = User.builder().id(targetId).build();

        Notification notification = Notification.builder()
                .fromUser(user)
                .toUser(targetUser)
                .message(resource.getMessage())
                .messageType(MessageType.ADMIN_MESSAGE)
                .createdBy("server1")
                .build();

        notificationService.createNotification(notification);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }
    
    // 6. 공지사항 확인처리(수정)
    
    // 7. 공지사항 삭제


}
