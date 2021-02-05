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
     * 키워드를 이용한 컨텐츠 조회
     */
    List<Contents> findContentsByKeyword(HashMap<String, String> map) throws Exception;

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

    /**
     * 컨텐츠 역량 수정
     */
    void updateAbilities(@Param("id") Long id, @Param("contentsAbility") AbilityRequestDTO abilityRequestDTO);
}