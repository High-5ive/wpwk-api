package com.ssafy.wpwk.mappers;

import com.ssafy.wpwk.model.Board;
import com.ssafy.wpwk.model.Contents;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface MyPageMapper {


    /**
     * 사용자가 제작한 컨텐츠 리스트 조회
     */
    List<Contents> findMyContents(@Param("userId") Long userId, @Param("offset") int offset);

    /**
     * 사용자가 제작한 게시글 리스트 조회
     */
    List<Board> findMyBoard(@Param("userId") Long userId, @Param("offset") int offset);

    /**
     * 사용자의 즐겨찾기 컨텐츠 리스트 조회
     */
    List<Contents> findContentsByFavorite(@Param("userId") Long userId, @Param("offset") int offset);

    /**
     * 사용자가 댓글 단 게시글 리스트 조회
     */
    List<Board> findBoardsByComments(@Param("userId") Long userId, @Param("offset") int offset);
}
