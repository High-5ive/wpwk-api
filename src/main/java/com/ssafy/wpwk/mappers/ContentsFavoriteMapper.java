package com.ssafy.wpwk.mappers;


import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

@Repository
@Mapper
public interface ContentsFavoriteMapper {

    /**
     * 컨텐츠 즐겨찾기 등록
     */
    void addFavoriteContents(@Param("contentsId") Long id, @Param("userId") Long userId) throws  Exception;

    /**
     * 컨텐츠 즐겨찾기 해제
     */
    void deleteFavoriteContents(@Param("contentsId") Long id, @Param("userId") Long userId) throws  Exception;
}
