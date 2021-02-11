package com.ssafy.wpwk.service;

import com.ssafy.wpwk.model.AbilityRequestDTO;
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
    Contents findContentsById(Long id) throws Exception;

    /**
     * 키워드(제목,제작자)가 포함된 컨텐츠 리스트 조회
     */
    List<Contents> findContentsByKeyword(String keyword,int page,Long userId) throws Exception;

    /**
     * 태그를 이용한 컨텐츠 조회
     */
    List<Contents> findContentsByTag(String tag, int page,Long userId) throws Exception;

    /**
     * 컨텐츠 전체 조회
     */
    List<Contents> findAllContents() throws Exception;

    /**
     * 페이지별 컨텐츠 조회
     */
    List<Contents> findAllContentsByPage(int page,Long userId) throws Exception;

    /**
     * 컨텐츠의 조회수 내림차순으로 리스트 제공
     */
    List<Contents> findContentsByViews(int page, Long userId) throws Exception;

    /**
     * 컨텐츠의 좋아요수 내림차순으로 리스트 제공
     */
    List<Contents>  findContentsByLikes(int page, Long userId) throws Exception;

    /**
     * 컨텐츠 수정
     */
    void update(Contents contents) throws Exception;

    /**
     * 컨텐츠 삭제
     */
    void delete(Long id) throws Exception;


}
