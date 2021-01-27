package com.ssafy.wpwk.mappers;

import com.ssafy.wpwk.model.Notification;
import com.ssafy.wpwk.model.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Mapper
public interface NotificationMapper {

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
    void broadCast(@Param("notification")Notification notification,
                   @Param("userList")List<User> users);

    /**
     * 공지사항 특정 수신자에게 작성
     */
    void createNotification(Notification notification);

    /**
     * 공지사항 삭제
     */
    int delete(Long id);

    /**
     * 공지사항 확인처리(수정)
     */
    void confirm(Long userId);
}
