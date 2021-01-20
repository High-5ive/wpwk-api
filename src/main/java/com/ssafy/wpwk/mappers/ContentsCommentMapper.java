package com.ssafy.wpwk.mappers;

import com.ssafy.wpwk.model.Contents;
import com.ssafy.wpwk.model.ContentsComment;
import org.mapstruct.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Mapper

public interface ContentsCommentMapper {

    /** 컨텐츠 댓글 생성 */
    void addComment(ContentsComment comment) throws Exception;

    /** 컨텐츠 댓글 전체조회 */
    List<ContentsComment> allComments(Long contentsId) throws Exception;

    /** 컨텐츠 댓글 수정 */
    void updateComment(ContentsComment comment) throws Exception;

    /** 컨텐츠 댓글 삭제 */
    void deleteComment(Long contentsId) throws Exception;


}

