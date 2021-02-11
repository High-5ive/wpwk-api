package com.ssafy.wpwk.service;

import com.ssafy.wpwk.model.Board;
import com.ssafy.wpwk.model.Contents;

import java.util.List;

public interface MypageService {


    /**
     *사용자가 제작한 컨텐츠 리스트 조회
     */
    List<Contents> findMyContents(Long userId, int page);

    /**
     *사용자가 제작한 게시글 리스트 조회
     */
    List<Board> findMyBoards(Long userId, int page);


    /**
     *사용자의 즐겨찾기 컨텐츠 리스트 조회
     */
    List<Contents> findContentsByFavorite(Long userId, int page) throws Exception;

    /**
     * 사용자가 댓글 단 게시글 리스트 조회
     */
    List<Board> findBoardsByComments(Long userId, int page);


}
