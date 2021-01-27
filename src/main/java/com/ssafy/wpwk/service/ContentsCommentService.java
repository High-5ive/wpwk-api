package com.ssafy.wpwk.service;

import com.ssafy.wpwk.model.ContentsComment;

import java.util.List;

public interface ContentsCommentService {

    /**
     * 컨텐츠 댓글 전체조회
     */
    List<ContentsComment> allComments(Long contentsId) throws Exception;

    /**
     * 컨텐츠 댓글 생성
     */
    void addComment(ContentsComment comment) throws Exception;

    /**
     * 컨텐츠 댓글 수정
     */
    void updateComment(ContentsComment comment) throws Exception;

    /**
     * 컨텐츠 댓글 삭제
     */
    void deleteComment(Long commentId, Long userId) throws Exception;
}