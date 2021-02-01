package com.ssafy.wpwk.service;

import com.ssafy.wpwk.mappers.TagMapper;
import com.ssafy.wpwk.model.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TagServiceImpl implements TagService{

    @Autowired
    private TagMapper tagMapper;

    @Override
    public List<Tag> getTagListByWord(String word) {
        return tagMapper.getTagListByWord(word);
    }

    @Override
    public List<Tag> getTagListByContentsId(Long contentsId) {
        return tagMapper.getTagListByContentsId(contentsId);
    }
}
