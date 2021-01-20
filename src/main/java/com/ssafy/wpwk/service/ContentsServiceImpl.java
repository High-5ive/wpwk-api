package com.ssafy.wpwk.service;

import com.ssafy.wpwk.mappers.ContentsMapper;
import com.ssafy.wpwk.model.Contents;
import com.ssafy.wpwk.model.ContentsComment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;

@Service
public class ContentsServiceImpl implements ContentsService {

    @Autowired
    ContentsMapper contentsMapper;

    @Override
    public long create(Contents contents) throws Exception {

        return contentsMapper.create(contents);
    }

    @Override
    public Contents findContentsById(Long contentsId) throws Exception {
        return null;
    }

    @Override
    public List<Contents> findContentsByKeyword(HashMap<String, String> map) throws Exception {
    }

    @Override
    public List<Contents> findAllContents() throws Exception {
        return contentsMapper.findAllContents();
    }

    @Override
    public void update(Long contentsId, Contents contents) throws Exception {
        contentsMapper.update(contentsId, contents);
    }

    @Override
    public void delete(Long contentsId) throws Exception {
        contentsMapper.delete(contentsId);
    }

    @Override
    public long addComment(ContentsComment comment) throws Exception {

        return 0;
    }

    @Override
    public List<ContentsComment> allComments(String contentsId) throws Exception {
        return null;
    }
}
