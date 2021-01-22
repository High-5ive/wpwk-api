package com.ssafy.wpwk.service;

import com.ssafy.wpwk.mappers.ContentsItemMapper;
import com.ssafy.wpwk.mappers.ContentsMapper;
import com.ssafy.wpwk.model.Contents;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;

@Service
public class ContentsServiceImpl implements ContentsService {

    @Autowired
    ContentsMapper contentsMapper;

    @Autowired
    ContentsItemMapper contentsItemMapper;

    /**
     * 컨텐츠 생성
     */
    @Override
    public void create(Contents contents) throws Exception {
        // 1. 컨텐츠 정보추가
        contentsMapper.create(contents);
        // 2. 컨텐츠 아이템 정보 추가
        try {
            contentsItemMapper.createByContentsId(contents.getId(), contents.getContentsItem());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * ID를 이용한 컨텐츠 조회
     */
    @Override
    public Contents findContentsById(Long contentsId) throws Exception {
        return contentsMapper.findContentsById(contentsId);
    }

    /**
     * 키워드를 이용한 컨텐츠 조회
     */
    @Override
    public List<Contents> findContentsByKeyword(HashMap<String, String> map) throws Exception {
        return contentsMapper.findContentsByKeyword(map);
    }

    /**
     * 컨텐츠 전체 조회
     */
    @Override
    public List<Contents> findAllContents() throws Exception {
        return contentsMapper.findAllContents();
    }

    /**
     * 컨텐츠 수정
     */
    @Override
    public void update(Contents contents) throws Exception {
        contentsMapper.update(contents);
    }

    /**
     * 컨텐츠 삭제
     */
    @Override
    public void delete(Long contentsId) throws Exception {
        contentsMapper.delete(contentsId);
    }

}
