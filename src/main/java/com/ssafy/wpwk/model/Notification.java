package com.ssafy.wpwk.model;

import com.ssafy.wpwk.enums.MessageType;
import lombok.*;

import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@Builder
public class Notification {

    /**
     * 공지사항 id
     * */
    private Long id;

    /**
     * 수신자
     * */
    private Long toUserId;

    /**
     * 송신자
     * */
    private User fromUser;

    /**
     * 메시지 타입
     * */
    private MessageType messageType;

    /**
     * 메시지
     * */
    private String message;

    /**
     * 생성일자
     * */
    private LocalDateTime createdAt;

    /**
     * 생성자
     * */
    private String createdBy;

    /**
     * 확인일자
     * */
    private LocalDateTime readAt;
}
