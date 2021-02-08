package com.ssafy.wpwk.service;

import com.ssafy.wpwk.model.BoardComment;
import com.ssafy.wpwk.model.ContentsComment;

import java.util.List;

public interface BoardCommentService {

    /*
     * 게시글 댓글 등록
     */
    void addBoardComment(BoardComment boardComment) throws Exception;

    /**
     * 게시글 댓글 전체조회
     */
    List<BoardComment> allBoardComments() throws Exception;

    /**
     * 게시글 ID를 이용한 게시글 댓글 조회
     */
    List<BoardComment> findByBoardIdAndOffset(Long boardId, int offset) throws Exception;

    /**
     * 게시글 댓글 수정
     */
    void updateComment(BoardComment boardComment) throws Exception;

    /**
     * 게시글 댓글 삭제
     */
    void deleteComment(Long boardCommentId, Long userId, Long boardId) throws Exception;
}
