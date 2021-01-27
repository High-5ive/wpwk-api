package com.ssafy.wpwk.service;

import com.ssafy.wpwk.mappers.NotificationMapper;
import com.ssafy.wpwk.mappers.UserMapper;
import com.ssafy.wpwk.model.Notification;
import com.ssafy.wpwk.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class NotificationServiceImpl implements NotificationService{

    @Autowired
    private NotificationMapper notificationMapper;

    @Autowired
    private UserMapper userMapper;

    /**
     * 공지사항 전체 조회
     */
    @Override
    public List<Notification> findAll() {
        return notificationMapper.findAll();
    }

    /**
     * 사용자별 공지사항 전체 조회
     */
    @Override
    public List<Notification> findAllByUserId(Long userId) {
        return notificationMapper.findAllByUserId(userId);
    }

    /**
     * 사용자별 확인하지 않은 공지사항 전체 조회
     */
    @Override
    public List<Notification> findAllByUserIdAndNotRead(Long userId) {
        return notificationMapper.findAllByUserIdAndNotRead(userId);
    }

    /**
     * 공지사항 브로드캐스팅
     */
    @Override
    public void broadcast(Notification notification) {

        // 1. 활성화된 회원 목록 가져옴
        List<User> userList = userMapper.findAllByNormalUser();

        // 2. 회원목록과 notification 정보 전달
        notificationMapper.broadcast(notification, userList);
    }

    /**
     * 공지사항 특정 수신자에게 작성
     */
    @Override
    public void createNotification(Notification notification) {
        notificationMapper.createNotification(notification);
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