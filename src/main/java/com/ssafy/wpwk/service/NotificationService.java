package com.ssafy.wpwk.service;

import com.ssafy.wpwk.enums.MessageType;
import com.ssafy.wpwk.model.Notification;
import java.util.List;

public interface NotificationService {

    /**
     * 공지사항 전체 조회
     */
    List<Notification> findAll();

    /**
     * 사용자별 공지사항 전체 조회
     */
    List<Notification> findAllByUserId(Long userId);

    /**
     * 사용자별 확인하지 않은 공지사항 전체 조회
     */
    List<Notification> findAllByUserIdAndNotRead(Long userId);

    /**
     * 공지사항 브로드캐스팅
     */
    void broadcast(Notification notification);

    /**
     * 공지사항 특정 수신자에게 작성
     */
    void createNotification(Long fromUserId, Long targetUserId,
                            String message, String contentsTitle, MessageType type);

    /**
     * 공지사항 삭제
     */
    int deleteByUserId(Long id);

    /**
     * 공지사항 확인처리(수정)
     */
    void confirm(Long id);
}