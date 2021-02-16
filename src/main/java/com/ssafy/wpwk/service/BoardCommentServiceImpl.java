package com.ssafy.wpwk.service;


import com.ssafy.wpwk.enums.MessageType;
import com.ssafy.wpwk.mappers.BoardCommentMapper;
import com.ssafy.wpwk.mappers.BoardMapper;
import com.ssafy.wpwk.model.Board;
import com.ssafy.wpwk.model.BoardComment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BoardCommentServiceImpl implements BoardCommentService {

    @Autowired
    private BoardCommentMapper boardCommentMapper;

    @Autowired
    private BoardMapper boardMapper;

    @Autowired
    private NotificationService notificationService;

    /**
     * 게시글 댓글 등록
     */
    @Override
    public void addBoardComment(BoardComment boardComment) throws Exception {
        boardCommentMapper.create(boardComment);
        boardMapper.updateCommentsCnt(boardComment.getBoardId(),1);

        Board board = boardMapper.findById(boardComment.getBoardId(), boardComment.getUserId());

        // 댓글 생성시 알림메시지 전송
        notificationService.createNotification(
                boardComment.getUserId(),
                board.getUserId(),
                "",
                "",
                MessageType.COMMENT
        );
    }

    /**
     * 게시글 댓글 전체조회
     */
    @Override
    public List<BoardComment> allBoardComments() throws Exception {
        return boardCommentMapper.findAll();
    }

    /**
     * 게시글 ID를 이용한 게시글 댓글 조회
     */
    @Override
    public List<BoardComment> findByBoardIdAndOffset(Long boardId, int offset) throws Exception {
        return boardCommentMapper.findByBoardIdAndOffset(boardId, offset);
    }

    /**
     * 게시글 댓글 수정
     */
    @Override
    public void updateComment(BoardComment boardComment) throws Exception {
        boardCommentMapper.update(boardComment);
    }

    /**
     * 게시글 댓글 삭제
     */
    @Override
    public void deleteComment(Long boardCommentId, Long userId, Long boardId) throws Exception {
        boardCommentMapper.delete(boardCommentId, userId);
        boardMapper.updateCommentsCnt(boardId,-1);
    }


}
