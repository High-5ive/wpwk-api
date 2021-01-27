package com.ssafy.wpwk.controller;

import com.ssafy.wpwk.model.Notification;
import com.ssafy.wpwk.service.NotificationService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = {"*"}, maxAge = 6000)
@RestController
public class NotificationController {

    @Autowired
    private NotificationService notificationService;

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

    // 3. 공지사항 작성

    // 4. 공지사항 삭제
    
    // 5. 공지사항 읽음 처리
}
