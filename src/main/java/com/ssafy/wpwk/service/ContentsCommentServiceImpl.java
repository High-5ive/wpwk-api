package com.ssafy.wpwk.service;

import com.ssafy.wpwk.enums.MessageType;
import com.ssafy.wpwk.mappers.ContentsCommentMapper;
import com.ssafy.wpwk.mappers.ContentsMapper;
import com.ssafy.wpwk.model.Contents;
import com.ssafy.wpwk.model.ContentsComment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ContentsCommentServiceImpl implements ContentsCommentService {

    @Autowired
    private ContentsMapper contentsMapper;

    @Autowired
    private ContentsCommentMapper contentsCommentMapper;

    @Autowired
    private NotificationService notificationService;

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

        Contents contents = contentsMapper.findContentsById(comment.getContentsId());

        // 댓글 생성시 알림메시지 전송
        notificationService.createNotification(
                comment.getUserId(),
                contents.getUserId(),
                contents.getTitle(),
                "",
                MessageType.COMMENT
        );
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