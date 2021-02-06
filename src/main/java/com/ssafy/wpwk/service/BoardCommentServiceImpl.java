package com.ssafy.wpwk.service;


import com.ssafy.wpwk.model.BoardComment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BoardCommentServiceImpl implements BoardCommentService {


    @Override
    public void addBoardComment(BoardComment boardComment) throws Exception {

    }

    @Override
    public List<BoardComment> allBoardComments(Long boardId) throws Exception {
        return null;
    }

    @Override
    public void updateComment(BoardComment boardComment) throws Exception {

    }

    @Override
    public void deleteComment(Long boardId, Long userId) throws Exception {

    }
}
