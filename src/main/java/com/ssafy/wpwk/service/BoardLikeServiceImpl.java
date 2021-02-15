package com.ssafy.wpwk.service;

import com.ssafy.wpwk.mappers.BoardLikeMapper;
import com.ssafy.wpwk.mappers.BoardMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
class BoardLikeServiceImpl implements BoardLikeService {

    @Autowired
    private BoardLikeMapper boardLikeMapper;

    @Autowired
    private BoardMapper boardMapper;

    @Override
    public void likeBoard(Long id, Long userId) throws Exception {
        boardLikeMapper.likeBoard(id, userId);
        boardMapper.updateLikes(id, 1);
    }

    @Override
    public void deleteLikeBoard(Long id, Long userId) throws Exception {
        boardLikeMapper.deleteLikeBoard(id, userId);
        boardMapper.updateLikes(id, -1);
    }
}
