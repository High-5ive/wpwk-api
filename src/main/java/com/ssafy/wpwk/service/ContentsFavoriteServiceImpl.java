package com.ssafy.wpwk.service;

import com.ssafy.wpwk.enums.MessageType;
import com.ssafy.wpwk.mappers.ContentsFavoriteMapper;

import com.ssafy.wpwk.mappers.ContentsMapper;
import com.ssafy.wpwk.model.Contents;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ContentsFavoriteServiceImpl implements ContentsFavoriteService{

    @Autowired
    private ContentsFavoriteMapper contentsFavoriteMapper;

    @Autowired
    private ContentsMapper contentsMapper;

    @Autowired
    private NotificationService notificationService;

    /**
     * 컨텐츠 즐겨찾기 등록
     */
    @Override
    public void addFavoriteContents(Long id, Long userId) throws Exception {
        contentsFavoriteMapper.addFavoriteContents(id, userId);
        
        // 해당 컨텐츠 좋아요 +1 카운트
        contentsMapper.countLike(id, 1);

        Contents contents = contentsMapper.findContentsById(id);

        // 콘텐츠 좋아요 메시지 전송
        notificationService.createNotification(
                userId,
                contents.getUserId(),
                contents.getTitle(),
                "",
                MessageType.LIKE
        );
    }

    /**
     * 컨텐츠 즐겨찾기 해제
     */
    @Override
    public void deleteFavoriteContents(Long id, Long userId) throws Exception {
        contentsFavoriteMapper.deleteFavoriteContents(id, userId);

        // 해당 컨텐츠 좋아요 -1 카운트
        contentsMapper.countLike(id, -1);
    }
}
