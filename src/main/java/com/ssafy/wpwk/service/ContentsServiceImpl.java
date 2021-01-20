package com.ssafy.wpwk.service;

import com.ssafy.wpwk.mappers.ContentsItemMapper;
import com.ssafy.wpwk.mappers.ContentsMapper;
import com.ssafy.wpwk.model.Contents;
import com.ssafy.wpwk.model.ContentsComment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.logging.Logger;

@Service
public class ContentsServiceImpl implements ContentsService {

    @Autowired
    ContentsMapper contentsMapper;

    @Autowired
    ContentsItemMapper contentsItemMapper;

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

    @Override
    public Contents findContentsById(Long contentsId) throws Exception {
        return contentsMapper.findContentsById(contentsId);
    }

    @Override
    public List<Contents> findContentsByKeyword(HashMap<String, String> map) throws Exception {
        return contentsMapper.findContentsByKeyword(map);
    }

    @Override
    public List<Contents> findAllContents() throws Exception {
        return contentsMapper.findAllContents();
    }

    @Override
    public void update(Contents contents) throws Exception {
        contentsMapper.update(contents);
    }

    @Override
    public void delete(Long contentsId) throws Exception {
        contentsMapper.delete(contentsId);
    }

}
