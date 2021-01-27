package com.ssafy.wpwk.service;

import com.ssafy.wpwk.mappers.ContentsCommentMapper;
import com.ssafy.wpwk.model.ContentsComment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ContentsCommentServiceImpl implements ContentsCommentService {

    @Autowired
    private ContentsCommentMapper contentsCommentMapper;

    /**
     * 컨텐츠 댓글 전체조회
     */
    @Override
    public List<ContentsComment> allComments(Long contentsId) throws Exception {
        return contentsCommentMapper.allComments(contentsId);
    }

    /**
     * 컨텐츠 댓글 생성
     */
    @Override
    public void addComment(ContentsComment comment) throws Exception {
        contentsCommentMapper.addComment(comment);
    }

    /**
     * 컨텐츠 댓글 수정
     */
    @Override
    public void updateComment(ContentsComment comment) throws Exception {
        contentsCommentMapper.updateComment(comment);
    }

    /**
     * 컨텐츠 댓글 삭제
     */
    @Override
    public void deleteComment( Long commentId ,Long userId) throws Exception {
        contentsCommentMapper.deleteComment( commentId,userId);
    }
}