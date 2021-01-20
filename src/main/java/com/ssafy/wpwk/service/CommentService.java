package com.ssafy.wpwk.service;

import com.ssafy.wpwk.model.Contents;
import com.ssafy.wpwk.model.ContentsComment;

import java.util.List;

public interface CommentService {

    List<ContentsComment> allComments(Long contentsId);


    void addComment(ContentsComment comment);

    /*
    댓글 작성자와 수정요청한 회원의 아이디가 같아야하므로
    아이디가 같은지 검증 필요
     */

    void updateComment(ContentsComment comment);


    /*
    댓글 작성자와 삭제 요청한 회원의 아이디가 같아야하므로
    아이디가 같은지 검증 필요
    */
    void deleteComment(ContentsComment comment);


}
