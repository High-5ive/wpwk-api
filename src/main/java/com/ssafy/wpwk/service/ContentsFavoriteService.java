package com.ssafy.wpwk.service;

public interface ContentsFavoriteService {

    /**
     * 컨텐츠 즐겨찾기 등록
     */
    void addFavoriteContents(Long id, Long userId) throws Exception;

    /**
     * 컨텐츠 즐겨찾기 해제
     */
    void deleteFavoriteContents(Long id, Long userId) throws Exception;
}
