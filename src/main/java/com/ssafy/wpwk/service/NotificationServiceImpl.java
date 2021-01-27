package com.ssafy.wpwk.service;

import com.ssafy.wpwk.mappers.NotificationMapper;
import com.ssafy.wpwk.model.Notification;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class NotificationServiceImpl implements NotificationService{

    private NotificationMapper notificationMapper;

    /**
     * 공지사항 전체 조회
     */
    @Override
    public List<Notification> findAll() {
        return null;
    }

    /**
     * 사용자별 공지사항 전체 조회
     */
    @Override
    public List<Notification> findAllByUserId(Long userId) {
        return null;
    }

    /**
     * 사용자별 확인하지 않은 공지사항 전체 조회
     */
    @Override
    public List<Notification> findAllByUserIdAndNotRead(Long userId) {
        return null;
    }

    /**
     * 공지사항 브로드캐스팅
     */
    @Override
    public void broadCast() {

    }

    /**
     * 공지사항 특정 수신자에게 작성
     */
    @Override
    public void createNotification(Notification notification) {

    }

    /**
     * 공지사항 삭제
     */
    @Override
    public int delete(Long id) {
        return 0;
    }

    /**
     * 공지사항 확인처리(수정)
     */
    @Override
    public void confirm(Long id) {

    }
}