package com.ssafy.wpwk.service;

import com.ssafy.wpwk.model.ContentsComment;
import org.springframework.stereotype.Service;

import java.util.List;

@Service

public class ContentsCommentServiceImpl implements ContentsCommentService {

    /** 컨텐츠 댓글 전체조회 */
    @Override
    public List<ContentsComment> allComments(Long contentsId) {
        return null;
    }

    /** 컨텐츠 댓글 생성 */
    @Override
    public void addComment(ContentsComment comment) {

    }

    /** 컨텐츠 댓글 수정 */
    @Override
    public void updateComment(ContentsComment comment) {

    }

    /** 컨텐츠 댓글 삭제 */
    @Override
    public void deleteComment(ContentsComment comment) {

    }
}