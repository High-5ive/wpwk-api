package com.ssafy.wpwk.service;

import com.ssafy.wpwk.model.ContentsItem;

import java.util.List;

public interface ContentsItemService {

    /**
     * 페이지 번호와 컨텐츠 아이디를 이용한 컨텐츠 아이템 조회
     */
    ContentsItem findByContentsIdAndPageNo(Long contentsId, int pageNo) throws Exception;

    /**
     * 컨텐츠 아이디를 이용한 컨텐츠 아이템 추가
     */
    void createByContentsId(Long contentsId, List<ContentsItem> contentsItemList) throws Exception;

    /**
     * 컨텐츠 아이디를 이용한 컨텐츠 아이템 삭제
     */
    void deleteByContentsId(Long contentsId) throws Exception;

    /**
     * 컨텐츠 아이디를 이용한 컨텐츠 아이템 수정
     */
    void updateByContentsId(Long contentsId, List<ContentsItem> contentsItem) throws Exception;
}