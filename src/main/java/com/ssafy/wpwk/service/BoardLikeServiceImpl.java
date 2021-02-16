package com.ssafy.wpwk.service;

import com.ssafy.wpwk.enums.MessageType;
import com.ssafy.wpwk.mappers.BoardLikeMapper;
import com.ssafy.wpwk.mappers.BoardMapper;
import com.ssafy.wpwk.model.Board;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
class BoardLikeServiceImpl implements BoardLikeService {

    @Autowired
    private BoardLikeMapper boardLikeMapper;

    @Autowired
    private BoardMapper boardMapper;

    @Autowired
    private NotificationService notificationService;

    @Override
    public void likeBoard(Long id, Long userId) throws Exception {
        boardLikeMapper.likeBoard(id, userId);
        boardMapper.updateLikes(id, 1);

        Board board = boardMapper.findById(id, userId);

        notificationService.createNotification(
                userId,
                board.getUserId(),
                "",
                "",
                MessageType.LIKE
        );
    }

    @Override
    public void deleteLikeBoard(Long id, Long userId) throws Exception {
        boardLikeMapper.deleteLikeBoard(id, userId);
        boardMapper.updateLikes(id, -1);
    }
}
