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
     * 컨텐츝성
     */
    void create(Contents contents) throws Exception;

    /**
     * �워�그,�목,�작가 �함컨텐�리스조회
     */
    List<Contents> findContentsByKeyword(@Param("keyword") String keyword, @Param("offset") int offset, @Param("userId") Long userId) throws Exception;

    /**
     * 컨텐츄체 조회
     */
    List<Contents> findAllContents() throws Exception;

    /**
     * 컨텐츘이지�조회
     */
    List<Contents> findAllContentsByPage(@Param("offset") int offset, @Param("userId") Long userId) throws Exception;

    /**
     * 컨텐츠의 조회�림차순�로 리스�공
     */
    List<Contents> findContentsByViews(@Param("offset") int offset, @Param("userId") Long userId) throws Exception;

    /**
     * 컨텐츠의 조회�림차순�로 리스�공
     */
    List<Contents>  findContentsByLikes(@Param("offset") int offset, @Param("userId") Long userId) throws Exception;

    /**
     * ID르용컨텐�조회
     */
    Contents findContentsById(Long id) throws Exception;

    /**
     * �그르용컨텐�조회
     */
    List<Contents> findContentsByTag(@Param("tag") String tag,
                                     @Param("offset") int offset, @Param("userId") Long userId) throws Exception;

    /**
     * 컨텐츘정
     */
    void update(Contents contents) throws Exception;

    /**
     * 컨텐츠�
     */
    void delete(Long id) throws Exception;

    /**
     * 컨텐�좋아카운
     */
    void countLike(@Param("id") Long id, @Param("likes") int likes);
    void updateContentsEval(@Param("id") Long contentsId, @Param("evalEdu") int evalEdu,@Param("evalFun") int evalFun,@Param("evalAcs") int evalAccess);


}