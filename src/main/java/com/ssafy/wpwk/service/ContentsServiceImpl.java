package com.ssafy.wpwk.service;

import com.ssafy.wpwk.mappers.ContentsItemMapper;
import com.ssafy.wpwk.mappers.ContentsMapper;
import com.ssafy.wpwk.mappers.TagMapper;
import com.ssafy.wpwk.model.Contents;
import com.ssafy.wpwk.model.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ContentsServiceImpl implements ContentsService {

    @Autowired
    private ContentsMapper contentsMapper;

    @Autowired
    private ContentsItemMapper contentsItemMapper;

    @Autowired
    private TagMapper tagMapper;

    static final int CNT_PAGE = 10;

    /**
     * 컨텐츠 생성
     */
    @Override
    public void create(Contents contents) throws Exception {
        // 1. 컨텐츠 정보추가
        // 1-1. 컨텐츠 아이템 첫 번째 페이지에 유튜브 썸네일이 있는 경우 -> 컨텐츠 썸네일에 활용
        String thumb = contents.getContentsItemList().get(0).getYoutubeThumbnail();
        if (thumb == null) {
            thumb = contents.getContentsItemList().get(0).getImageAddress();
        }
        contents.setThumb(thumb);
        contentsMapper.create(contents);
        // 2. 컨텐츠 아이템 정보 추가
        try {
            contentsItemMapper.createByContentsId(contents.getId(), contents.getContentsItemList());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * ID를 이용한 컨텐츠 조회
     */
    @Override
    public Contents findContentsById(Long id) throws Exception {
        Contents contents = contentsMapper.findContentsById(id);
        List<Tag> tagList = tagMapper.getTagListByContentsId(contents.getId());
        contents.setTagList(tagList);

        return contents;
    }

    /**
     * 키워드(제목,제작자)가 포함된 컨텐츠 리스트 조회
     */
    @Override
    public List<Contents> findContentsByKeyword(String keyword,int page,Long userId) throws Exception {
        int offset = (page - 1) * CNT_PAGE;
        List<Contents> contentsList = contentsMapper.findContentsByKeyword(keyword,offset,userId);

        for (Contents contents : contentsList) {
            List<Tag> tagList = tagMapper.getTagListByContentsId(contents.getId());
            contents.setTagList(tagList);
        }
        return contentsList;
    }

    /**
     * 태그가 포함된 컨텐츠 리스트 조회
     */
    @Override
    public List<Contents> findContentsByTag(String tag, int page,Long userId) throws Exception {
        int offset = (page - 1) * CNT_PAGE;
        List<Contents> contentsList = contentsMapper.findContentsByTag(tag, offset,userId);
        for (Contents contents : contentsList) {
            List<Tag> tagList = tagMapper.getTagListByContentsId(contents.getId());
            contents.setTagList(tagList);
        }

        return contentsList;
    }

    /**
     * 컨텐츠 전체 조회
     */
    @Override
    public List<Contents> findAllContents() throws Exception {
        List<Contents> contentsList = contentsMapper.findAllContents();
        for (Contents contents : contentsList) {
            List<Tag> tagList = tagMapper.getTagListByContentsId(contents.getId());
            contents.setTagList(tagList);
        }

        return contentsList;
    }

    /**
     * 페이지별 컨텐츠 조회
     */
    @Override
    public List<Contents> findAllContentsByPage(int page,Long userId) throws Exception {

        int offset = (page - 1) * CNT_PAGE;
        List<Contents> contentsList = contentsMapper.findAllContentsByPage(offset,userId);
        for (Contents contents : contentsList) {
            List<Tag> tagList = tagMapper.getTagListByContentsId(contents.getId());
            contents.setTagList(tagList);
        }

        return contentsList;
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
    public void delete(Long id) throws Exception {
        contentsMapper.delete(id);
    }


}