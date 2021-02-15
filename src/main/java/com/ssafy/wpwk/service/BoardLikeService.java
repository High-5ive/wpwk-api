package com.ssafy.wpwk.service;

public interface BoardLikeService {

    /**
     * 게시글 좋아요 등록
     */
    void likeBoard(Long id, Long userId) throws Exception;

    /**
     * 게시글 좋아요 해제
     */
    void deleteLikeBoard(Long id, Long userId) throws Exception;
}
