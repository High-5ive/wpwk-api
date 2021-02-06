package com.ssafy.wpwk.service;

import com.ssafy.wpwk.model.Board;
import com.ssafy.wpwk.model.Contents;

import java.util.List;

public interface BoardService {


    /*
     * 게시글 생성
     */
    void create(Board board) throws Exception;

    /*
     * 게시글 전체 조회
     */
    List<Board> findAll() throws Exception;

    /*
     * 페이지별 게시글 조회
     */
    List<Board> findAllByOffset(int offset) throws Exception;

    /*
     * ID를 이용한 게시글 상세조회
     */
    Board findById(Long id) throws Exception;

    /*
     * 게시글 수정
     */
    void update(Board board) throws Exception;

    /*
     * 게시글 삭제
     */
    void delete(Long id) throws Exception;
}
