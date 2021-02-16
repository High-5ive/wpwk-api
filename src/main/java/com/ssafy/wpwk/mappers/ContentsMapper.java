package com.ssafy.wpwk.mappers;

import com.ssafy.wpwk.model.Contents;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

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
    List<Contents> findContentsByKeyword(@Param("keyword") String keyword, @Param("offset") int offset, @Param("userId") Long userId) throws Exception;

    /**
     * 컨텐츠 전체 조회
     */
    List<Contents> findAllContents() throws Exception;

    /**
     * 컨텐츠 페이지별 조회
     */
    List<Contents> findAllContentsByPage(@Param("offset") int offset, @Param("userId") Long userId) throws Exception;

    /**
     * 컨텐츠의 조회수 내림차순으로 리스트 제공
     */
    List<Contents> findContentsByViews(@Param("offset") int offset, @Param("userId") Long userId) throws Exception;

    /**
     * 컨텐츠의 조회수 내림차순으로 리스트 제공
     */
    List<Contents>  findContentsByLikes(@Param("offset") int offset, @Param("userId") Long userId) throws Exception;

    /**
     * ID를 이용한 컨텐츠 조회
     */
    Contents findContentsById(Long id) throws Exception;

    /**
     * 태그를 이용한 컨텐츠 조회
     */
    List<Contents> findContentsByTag(@Param("tag") String tag,
                                     @Param("offset") int offset, @Param("userId") Long userId) throws Exception;

    /**
     * 카테고리를 이용한 컨텐츠 검색
     */
    List<Contents> findContentsByCategory(@Param("category")int categoryToSquareOfTwo, @Param("offset")int offset, @Param("userId")Long userId);

    /**
     * 컨텐츠 수정
     */
    void update(Contents contents) throws Exception;

    /**
     * 컨텐츠 삭제
     */
    void delete(Long id) throws Exception;

    /**
     * 컨텐츠 좋아요 카운트
     */
    void countLike(@Param("id") Long id, @Param("likes") int likes);

    /**
     * 컨텐츠 조회수 카운트
     */
    void countViews(Long id);
    
    /**
     * 컨텐츠 역량 업데이트
     */
    void updateContentsEval(@Param("id") Long contentsId, @Param("evalEdu") int evalEdu,@Param("evalFun") int evalFun,@Param("evalAcs") int evalAccess);
}