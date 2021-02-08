package com.ssafy.wpwk.mappers;

import com.ssafy.wpwk.model.AbilityRequestDTO;
import com.ssafy.wpwk.model.Contents;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.List;

@Repository
@Mapper
public interface ContentsMapper {

    /**
     * 컨텐츠 생성
     */
    void create(Contents contents) throws Exception;

    /**
     * 키워드(태그,제목,제작자)가 포함된 컨텐츠 리스트 조회
     */
    List<Contents> findContentsByKeyword(@Param("keyword") String keyword) throws Exception;

    /**
     * 컨텐츠 전체 조회
     */
    List<Contents> findAllContents() throws Exception;

    /**
     * 컨텐츠 페이지별 조회
     */
    List<Contents> findAllContentsByPage(int offset) throws Exception;

    /**
     * ID를 이용한 컨텐츠 조회
     */
    Contents findContentsById(Long id) throws Exception;

    /**
     * ID를 이용한 컨텐츠 조회
     */
    List<Contents> findContentsByTagName(String tag) throws Exception;

    /**
     * 컨텐츠 수정
     */
    void update(Contents contents) throws Exception;

    /**
     * 컨텐츠 삭제
     */
    void delete(Long id) throws Exception;

}