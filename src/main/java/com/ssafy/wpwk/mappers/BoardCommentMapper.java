package com.ssafy.wpwk.mappers;

import com.ssafy.wpwk.model.Board;
import com.ssafy.wpwk.model.BoardComment;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Mapper
public interface BoardCommentMapper {

    /**
     * 게시글 댓글 생성
     */
    void create(BoardComment boardComment) throws Exception;

    /**
     * 게시글 댓글 전체 조회
     */
    List<BoardComment> findAll() throws Exception;

    /**
     * 게시글에서 페이지별 게시글 댓글 조회
     */
    List<BoardComment> findAllByBoardIdAndOffset(
            @Param("boardId")Long boardId,
            @Param("offset")int offset) throws Exception;

    /**
     * 게시글 댓글 수정
     */
    void update(BoardComment boardComment) throws Exception;

    /**
     * 게시글 댓글 삭제
     */
    void delete(Long id) throws Exception;
}