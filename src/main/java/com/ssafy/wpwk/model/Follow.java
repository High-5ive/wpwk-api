package com.ssafy.wpwk.model;

import lombok.*;

import java.time.LocalDateTime;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
public class Follow {
    /**
     * 팔로우 기본키 id
     */
    private Long id;

    /**
     * 팔로우 신청받은 수신자 id
     */
    private User toUserId;

    /**
     * 팔로우 신청한 송신자 id
     */
    private User fromUserId;

    /**
     * 팔로우 생성일자
     */
    private LocalDateTime createdAt;

    /**
     * 팔로우 생성자
     */
    private String createdBy;


}
