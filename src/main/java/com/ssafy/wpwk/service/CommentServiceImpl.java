package com.ssafy.wpwk.service;

import com.ssafy.wpwk.model.ContentsComment;
import com.ssafy.wpwk.service.CommentService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service

public class CommentServiceImpl implements CommentService {
    @Override
    public List<ContentsComment> allComments(Long contentsId) {
        return null;
    }

    @Override
    public void addComment(ContentsComment comment) {

    }

    @Override
    public void updateComment(ContentsComment comment) {

    }

    @Override
    public void deleteComment(ContentsComment comment) {

    }


}
