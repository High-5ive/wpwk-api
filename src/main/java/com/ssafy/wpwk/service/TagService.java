package com.ssafy.wpwk.service;

import com.ssafy.wpwk.model.Tag;

import java.util.List;

public interface TagService {

    /***
     * 해시태그 추가시 입력한 단어를 통해 제시어 제공
     */
    List<Tag> getTagListByWord(String word);
    
    /***
     * 해당 컨텐츠 태그리스트 가져오기
     */
    List<Tag> getTagListByContentsId(Long contentsId);
}
