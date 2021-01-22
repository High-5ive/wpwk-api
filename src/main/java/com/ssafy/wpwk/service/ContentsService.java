package com.ssafy.wpwk.service;

import com.ssafy.wpwk.model.Contents;

import java.util.HashMap;
import java.util.List;

public interface ContentsService {

    /**
     * 컨텐츠 생성
     */
    void create(Contents contents) throws Exception;

    /**
     * ID를 이용한 컨텐츠 조회
     */
    Contents findContentsById(Long contentsId) throws Exception;

    /**
     * 키워드를 이용한 컨텐츠 조회
     */
    List<Contents> findContentsByKeyword(HashMap<String, String> map) throws Exception;

    /**
     * 컨텐츠 전체 조회
     */
    List<Contents> findAllContents() throws Exception;

    /**
     * 컨텐츠 수정
     */
    void update(Contents contents) throws Exception;

    /**
     * 컨텐츠 삭제
     */
    void delete(Long contentsId) throws Exception;
}
