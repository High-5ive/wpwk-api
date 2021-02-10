package com.ssafy.wpwk.service;

import com.ssafy.wpwk.model.Board;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface BoardService {


    /**
     * 게시글 생성
     */
    void create(Board board) throws Exception;

    /**
     * 게시글 전체 조회
     */
    List<Board> findAll() throws Exception;

    /**
     * 페이지별 게시글 조회
     */
    List<Board> findAllByOffset(int offset,Long userId) throws Exception;

    /**
     * ID를 이용한 게시글 상세조회
     */
    Board findById(Long id,Long userId) throws Exception;

    /**
     * 카테고리를 이용한 게시글 상세조회
     */
    List<Board> findByCategory(String category, int offset,Long userId) throws Exception;

    /**
     * 게시글 수정
     */
    void update(Board board) throws Exception;

    /**
     * 게시글 삭제
     */
    void delete(Long id, Long userId) throws Exception;

    /**
     * 게시글 좋아요 증가&감소
     */
    void updateLikes(Long id, Long userId, int likes) throws Exception;

}
