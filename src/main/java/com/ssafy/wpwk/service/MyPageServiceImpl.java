package com.ssafy.wpwk.service;

import com.ssafy.wpwk.mappers.MyPageMapper;
import com.ssafy.wpwk.mappers.TagMapper;
import com.ssafy.wpwk.model.Board;
import com.ssafy.wpwk.model.Contents;
import com.ssafy.wpwk.model.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MyPageServiceImpl implements MypageService {
    @Autowired
    private MyPageMapper myPageMapper;

    @Autowired
    private TagMapper tagMapper;

    static final int CNT_PAGE = 10;


    /**
     * 사용자가 제작한 컨텐츠 리스트 조회
     */
    @Override
    public List<Contents> findMyContents(Long userId, int page) {
        int offset = (page - 1) * CNT_PAGE;
        List<Contents> contentsList = myPageMapper.findMyContents(userId,offset);
        for (Contents contents : contentsList) {
            List<Tag> tagList = tagMapper.getTagListByContentsId(contents.getId());
            contents.setTagList(tagList);
        }
        return contentsList;
    }

    /**
     * 사용자가 제작한 게시글 리스트 조회
     */
    @Override
    public List<Board> findMyBoards(Long userId, int page) {
        int offset = (page - 1) * CNT_PAGE;

        return myPageMapper.findMyBoard(userId,offset);
    }

    /**
     * 사용자의 즐겨찾기 컨텐츠 리스트 조회
     */
    public List<Contents> findContentsByFavorite(Long userId, int page) {
        int offset = (page - 1) * CNT_PAGE;
        List<Contents> contentsList = myPageMapper.findContentsByFavorite(userId,offset);
        for (Contents contents : contentsList) {
            List<Tag> tagList = tagMapper.getTagListByContentsId(contents.getId());
            contents.setTagList(tagList);
        }
        return contentsList;
    }

    /**
     * 사용자가 댓글 단 게시글 리스트 조회
     */
    @Override
    public List<Board> findBoardsByComments(Long userId, int page) {
        int offset = (page - 1) * CNT_PAGE;

        return myPageMapper.findBoardsByComments(userId, offset);
    }


}
