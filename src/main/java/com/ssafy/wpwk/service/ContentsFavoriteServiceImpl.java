package com.ssafy.wpwk.service;

import com.ssafy.wpwk.mappers.ContentsFavoriteMapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ContentsFavoriteServiceImpl implements ContentsFavoriteService{

    @Autowired
    private ContentsFavoriteMapper contentsFavoriteMapper;
    /**
     * 컨텐츠 즐겨찾기 등록
     */
    @Override
    public void addFavoriteContents(Long id, Long userId) throws Exception {
        contentsFavoriteMapper.addFavoriteContents(id,  userId);
    }

    /**
     * 컨텐츠 즐겨찾기 해제
     */
    @Override
    public void deleteFavoriteContents(Long id, Long userId) throws Exception {
        contentsFavoriteMapper.deleteFavoriteContents(id,userId);
    }
}
