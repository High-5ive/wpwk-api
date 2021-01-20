package com.ssafy.wpwk.mappers;

import com.ssafy.wpwk.model.Contents;
import com.ssafy.wpwk.model.ContentsComment;
import org.mapstruct.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Mapper

public interface CommentMapper {

    void addComment(ContentsComment comment) throws Exception;

    List<ContentsComment> allComments(Long contentsId) throws Exception;

    void updateComment(ContentsComment comment) throws Exception;

    void deleteComment(Long contentsId) throws Exception;


}

