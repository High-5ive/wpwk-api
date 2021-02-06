package com.ssafy.wpwk.service;


import com.ssafy.wpwk.model.BoardComment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BoardCommentServiceImpl implements BoardCommentService {
    @Autowired
    private BoardCommentMapper boardCommentMapper;


    /*
     * 게시글 댓글 등록
     */
    @Override
    public void addBoardComment(BoardComment boardComment) throws Exception {

    }

    /**
     * 게시글 댓글 전체조회
     */
    @Override
    public List<BoardComment> allBoardComments(Long boardId) throws Exception {
        return null;
    }

    /**
     * 게시글 댓글 수정
     */
    @Override
    public void updateComment(BoardComment boardComment) throws Exception {

    }

    /**
     * 게시글 댓글 삭제
     */
    @Override
    public void deleteComment(Long boardId, Long userId) throws Exception {

    }
}
