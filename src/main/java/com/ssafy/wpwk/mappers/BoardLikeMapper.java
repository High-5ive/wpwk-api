package com.ssafy.wpwk.mappers;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

@Repository
@Mapper
public interface BoardLikeMapper {

    /**
     * 게시글 좋아요 등록
     */
    void likeBoard(@Param("id")Long id, @Param("userId")Long userId) throws Exception;

    /**
     * 게시글 좋아요 해제
     */
    void deleteLikeBoard(@Param("id")Long id, @Param("userId")Long userId) throws Exception;
}
