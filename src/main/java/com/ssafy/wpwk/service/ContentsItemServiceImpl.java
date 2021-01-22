package com.ssafy.wpwk.service;

import com.ssafy.wpwk.mappers.ContentsItemMapper;
import com.ssafy.wpwk.model.ContentsItem;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ContentsItemServiceImpl implements ContentsItemService {

    @Autowired
    private ContentsItemMapper contentsItemMapper;

    /**
     * 페이지 번호와 컨텐츠 아이디를 통한 컨텐츠 아이템 조회
     */
    @Override
    public ContentsItem findByContentsIdAndPageNo(Long contentsId, int pageNo) throws Exception {
        return contentsItemMapper.findByContentsIdAndPageNo(contentsId, pageNo);
    }

    /**
     * 컨텐츠 아이디를 이용한 컨텐츠 아이템 추가
     */
    @Override
    public void createByContentsId(Long contentsId, List<ContentsItem> contentsItemList) throws Exception {
        contentsItemMapper.createByContentsId(contentsId, contentsItemList);
    }


    /**
     * 컨텐츠 아이디를 이용한 컨텐츠 아이템 삭제
     */
    @Override
    public void deleteByContentsId(Long contentsId) throws Exception {
        contentsItemMapper.deleteByContentsId(contentsId);
    }

    /**
     * 컨텐츠 아이디를 이용한 컨텐츠 아이템 수정
     */
    @Override
    public void updateByContentsId(Long contentsId,
                                   List<ContentsItem> contentsItemList) throws Exception {
        // 기존 컨텐츠 삭제 후 추가
        // 1. 기존 컨텐츠 삭제
        deleteByContentsId(contentsId);
        // 2. 수정할 컨텐츠 추가
        createByContentsId(contentsId, contentsItemList);
    }
}
