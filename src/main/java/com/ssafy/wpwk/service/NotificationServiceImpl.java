package com.ssafy.wpwk.service;

import com.ssafy.wpwk.enums.MessageType;
import com.ssafy.wpwk.mappers.NotificationMapper;
import com.ssafy.wpwk.mappers.UserMapper;
import com.ssafy.wpwk.model.Notification;
import com.ssafy.wpwk.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static com.ssafy.wpwk.utils.MessageUtil.makeMessage;

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
     * @Param(fromUserId) : 송신자 아이디
     * @Param(targetUserId) : 수신자 아이디
     * @Param(contentsTitle) : 콘텐츠 제목
     * @Param(type) : 메시지 타입
     */
    @Override
    @Async
    @Transactional
    public void createNotification(Long fromUserId, 
                                   Long targetUserId,
                                   String contentsTitle,
                                   String message,
                                   MessageType type) {

        if(fromUserId.equals(targetUserId)) { return; } // 송신자와 수신자가 같은 경우 메시지 전송 X

        User fromUser = userMapper.findUserById(fromUserId);

        System.out.println(" :: " + contentsTitle);

        Notification notification = Notification.builder()
                .fromUserId(fromUserId)
                .fromUserNickname(fromUser.getNickname())
                .toUserId(targetUserId)
                .messageType(type)
                .message(makeMessage(fromUser.getNickname(),
                        contentsTitle,
                        message,
                        type))
                .build();

        System.out.println(notification.getMessage());

        notificationMapper.createNotification(notification);
    }

    /**
     * 사용자별 공지사항 삭제
     */
    @Override
    public int deleteByUserId(Long userId) {
        return notificationMapper.deleteByUserId(userId);
    }

    /**
     * 공지사항 확인처리(수정)
     */
    @Override
    public void confirm(Long userId) {
        notificationMapper.confirm(userId);
    }


}