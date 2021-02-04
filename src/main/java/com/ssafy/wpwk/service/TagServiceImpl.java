package com.ssafy.wpwk.service;

import com.ssafy.wpwk.mappers.TagMapper;
import com.ssafy.wpwk.model.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
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

    @Override
    @Transactional
    public void createTags(Long contentsId, List<String> tags) {

        List<Long> tagIdList = new ArrayList<>();
        // 1. 주어진 태그들 분석 (이미 존재하는 태그인지 아닌지)
        for(String tag : tags) {
            Long tagId;
            if(tag.charAt(0) == ':') { // 이미 존재하는 태그인 경우 -> 태그 사용 수 +1
                tagId = Long.parseLong(tag.substring(1));
                tagMapper.updateTagCount(tagId);
            } else { // 새로 생성해야하는 태그인 경우 -> 태그 데이터 추가
                Tag newTag = Tag.builder().name(tag).build();
                tagMapper.createTag(newTag);
                tagId = newTag.getId();
            }

            tagIdList.add(tagId);
        }

        // 태그가 있는 경우
        if(tagIdList.size() != 0) {
            tagMapper.createContentsTag(contentsId, tagIdList);
        }
    }
}
