package com.ssafy.wpwk.mappers;

import com.ssafy.wpwk.model.ContentsItem;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Mapper
public interface ContentsItemMapper {

    /**
     * 페이지 번호와 컨텐츠 아이디를 통한 컨텐츠 아이템 조회
     */
    ContentsItem findByContentsIdAndPageNo(@Param("contentsId") Long contentsId, @Param("pageNo") int pageNo) throws Exception;

    /**
     * 컨텐츠 아이디를 이용한 컨텐츠 아이템 추가
     */
    void createByContentsId(@Param("contentsId") Long contentsId,
                            @Param("itemList") List<ContentsItem> contentsItemList) throws Exception;

    /**
     * 컨텐츠 아이디를 이용한 컨텐츠 아이템 삭제
     */
    void deleteByContentsId(Long contentsId) throws Exception;
}
