package com.ssafy.wpwk.mappers;

import com.ssafy.wpwk.model.Board;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Mapper
public interface BoardMapper {

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
    List<Board> findAllByOffset(int offset) throws Exception;

    /**
     * ID를 이용한 게시글 상세조회
     */
    Board findById(Long id) throws Exception;

    /**
     * 카테고리를 이용한 게시글 조회
     */
    List<Board> findByCategory(@Param("category") String category, @Param("offset") int offset) throws Exception;

    /**
     * 게시글 수정
     */
    void update(Board board) throws Exception;

    /**
     * 게시글 좋아요 증가/감소
     */
    void updateLikes(@Param("id") Long id, @Param("likes") int likes) throws Exception;
    
    /**
     * 게시글 조회수 증가
     */
    void increaseViews(Long id) throws Exception;

    /**
     * 게시글 삭제
     */
    void delete(@Param("id")Long id, @Param("userId")Long userId) throws Exception;

    /**
     * 게시글 댓글수 증가
     */
    void updateCommentsCnt(@Param("id") Long id,@Param("cnt") int cnt) throws Exception;

}