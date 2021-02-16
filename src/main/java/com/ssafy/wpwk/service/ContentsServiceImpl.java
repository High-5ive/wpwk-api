package com.ssafy.wpwk.service;

import com.ssafy.wpwk.mappers.ContentsItemMapper;
import com.ssafy.wpwk.mappers.ContentsMapper;
import com.ssafy.wpwk.mappers.TagMapper;
import com.ssafy.wpwk.model.Contents;
import com.ssafy.wpwk.model.ContentsItem;
import com.ssafy.wpwk.model.Tag;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static com.ssafy.wpwk.utils.ConversionUtil.decimalToBinary;

@Service
public class ContentsServiceImpl implements ContentsService {

    Logger logger = LoggerFactory.getLogger(ContentsServiceImpl.class);
    
    private static final int CATEGORY_CNT = 8; // 카테고리 개수

    @Autowired
    private ContentsMapper contentsMapper;

    @Autowired
    private ContentsItemMapper contentsItemMapper;

    @Autowired
    private ContentsItemService contentsItemService;

    @Autowired
    private TagMapper tagMapper;

    static final int CNT_PAGE = 10;

    /**
     * 컨텐츠 생성
     */
    @Override
    public void create(Contents contents) throws Exception {
        preProcess(contents);
        logger.info(contents.getAbility());
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
    @Transactional
    public Contents findContentsById(Long id) throws Exception {
        Contents contents = contentsMapper.findContentsById(id);
        List<Tag> tagList = tagMapper.getTagListByContentsId(contents.getId());
        contents.setTagList(tagList);

        contents.setAbility(decimalToBinary(contents.getAbility()));

        // 해당 컨텐츠 조회수 증가
        contentsMapper.countViews(id);

        return contents;
    }

    /**
     * 키워드(제목,제작자)가 포함된 컨텐츠 리스트 조회
     */
    @Override
    public List<Contents> findContentsByKeyword(String keyword, int page, Long userId) throws Exception {
        int offset = (page - 1) * CNT_PAGE;
        List<Contents> contentsList = contentsMapper.findContentsByKeyword(keyword, offset, userId);

        for (Contents contents : contentsList) {
            List<Tag> tagList = tagMapper.getTagListByContentsId(contents.getId());
            contents.setTagList(tagList);
            contents.setAbility(decimalToBinary(contents.getAbility()));
        }
        return contentsList;
    }

    /**
     * 태그가 포함된 컨텐츠 리스트 조회
     */
    @Override
    public List<Contents> findContentsByTag(String tag, int page, Long userId) throws Exception {
        int offset = (page - 1) * CNT_PAGE;
        List<Contents> contentsList = contentsMapper.findContentsByTag(tag, offset, userId);
        for (Contents contents : contentsList) {
            List<Tag> tagList = tagMapper.getTagListByContentsId(contents.getId());
            contents.setTagList(tagList);
            contents.setAbility(decimalToBinary(contents.getAbility()));
        }

        return contentsList;
    }

    /**
     * 카테고리가 포함된 컨텐츠 리스트 조회
     */
    @Override
    public List<Contents> findContentsByCategory(int category, int page, Long userId) throws Exception {
        int offset = (page - 1) * CNT_PAGE;

        // category 수를 2의 제곱 승 형태로 변형
        int categoryToSquareOfTwo = (int) Math.pow(2, CATEGORY_CNT-category);

        List<Contents> contentsList = contentsMapper.findContentsByCategory(categoryToSquareOfTwo, offset, userId);
        for (Contents contents : contentsList) {
            List<Tag> tagList = tagMapper.getTagListByContentsId(contents.getId());
            contents.setTagList(tagList);
            contents.setAbility(decimalToBinary(contents.getAbility()));
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
            contents.setAbility(decimalToBinary(contents.getAbility()));
        }

        return contentsList;
    }

    /**
     * 페이지별 컨텐츠 조회
     */
    @Override
    public List<Contents> findAllContentsByPage(int page, Long userId) throws Exception {

        int offset = (page - 1) * CNT_PAGE;
        List<Contents> contentsList = contentsMapper.findAllContentsByPage(offset, userId);
        for (Contents contents : contentsList) {
            List<Tag> tagList = tagMapper.getTagListByContentsId(contents.getId());
            contents.setTagList(tagList);
            contents.setAbility(decimalToBinary(contents.getAbility()));
        }

        return contentsList;
    }

    /**
     * 컨텐츠의 조회수 내림차순으로 리스트 제공
     */
    @Override
    public List<Contents> findContentsByViews(int page, Long userId) throws Exception {
        int offset = (page - 1) * CNT_PAGE;
        List<Contents> contentsList = contentsMapper.findContentsByViews(offset, userId);
        for (Contents contents : contentsList) {
            List<Tag> tagList = tagMapper.getTagListByContentsId(contents.getId());
            contents.setTagList(tagList);
            contents.setAbility(decimalToBinary(contents.getAbility()));
        }
        return contentsList;
    }

    /**
     * 컨텐츠의 좋아요 내림차순으로 리스트 제공
     */
    @Override
    public List<Contents> findContentsByLikes(int page, Long userId) throws Exception {
        int offset = (page - 1) * CNT_PAGE;
        List<Contents> contentsList = contentsMapper.findContentsByLikes(offset, userId);
        for (Contents contents : contentsList) {
            List<Tag> tagList = tagMapper.getTagListByContentsId(contents.getId());
            contents.setTagList(tagList);
            contents.setAbility(decimalToBinary(contents.getAbility()));
        }
        return contentsList;
    }

    /**
     * 컨텐츠 수정
     */
    @Override
    public void update(Contents contents) throws Exception {

        preProcess(contents);

        // 1. 컨텐츠 수정
        contentsMapper.update(contents);

        // 2. 콘텐츠 아이템 수정(기존 아이템 삭제 및 새로 생성)
        contentsItemService.updateByContentsId(contents.getId(), contents.getContentsItemList());
    }

    /**
     * 컨텐츠 삭제
     */
    @Override
    public void delete(Long id) throws Exception {
        contentsMapper.delete(id);
    }

    /**
     * 콘텐츠 생성 시 전처리 작업
     */
    public void preProcess(Contents contents) throws Exception {
        String thumb = contents.getContentsItemList().get(0).getYoutubeThumbnail();
        if (thumb == null) {
            thumb = contents.getContentsItemList().get(0).getImageAddress();
        }

        contents.setThumb(thumb);

        // 2진수 형태로 들어온 ability -> 10진수 형태로 변환
        int makeAbility = Integer.parseInt(contents.getAbility(), 2);
        contents.setAbility(String.valueOf(makeAbility));
    }
}