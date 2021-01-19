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
    ContentsMapper mapper;

    @Override
    public long create(Contents contents) throws Exception {

        return 0;
    }

    @Override
    public Contents findContents(HashMap<String, String> map) throws Exception {
        return null;
    }

    @Override
    public List<Contents> findAllContents() throws Exception {
        return null;
    }

    @Override
    public void update(long contentsId, Contents contents) throws Exception {

    }

    @Override
    public void delete(long contentsId) throws Exception {

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
