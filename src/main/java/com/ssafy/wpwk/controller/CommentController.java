package com.ssafy.wpwk.controller;


import com.ssafy.wpwk.model.ContentsComment;
import com.ssafy.wpwk.service.CommentServiceImpl;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@CrossOrigin(origins = "{*}", maxAge = 6000)
public class CommentController {

    @Autowired
    private CommentServiceImpl commentService;

    @ApiOperation(value = "컨텐츠의 모든 댓글 조회")
    @GetMapping("/comment/{contentsId}")
    public ResponseEntity<List<ContentsComment>> allComments(@PathVariable Long contentsId) {
        List<ContentsComment> commentList = null;
        try {

            commentList = commentService.allComments(contentsId);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }

        return new ResponseEntity<>(commentList, HttpStatus.OK);
    }

    @ApiOperation(value = "컨텐츠 댓글 등록")
    @PostMapping("/comment")
    public ResponseEntity<Void> addComment(@RequestBody ContentsComment comment) {
        try {
            //회원인지 검증 필요함
            // comment.user.nickname ? jwt.claim.get(nickname)
            // commentService.addComment(comment);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        return new ResponseEntity<>(HttpStatus.OK);
    }

    @ApiOperation(value = "컨텐츠 댓글 수정")
    @PutMapping("/comment/{contentsId}")
    public ResponseEntity<Void> updateComment(@PathVariable Long contentsId, @RequestBody ContentsComment comment) {

        try {
            //회원인지 검증 필요함
            // comment.user.nickname ? jwt.claim.get(nickname)
            //commentService.updateComment(comment);
            comment.setUpdatedAt(LocalDateTime.now());
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(HttpStatus.OK);

    }

    @ApiOperation(value = "컨텐츠 댓글 삭제")
    @DeleteMapping("/comment/{contentsId}")
    public ResponseEntity<Void> deleteComment(@PathVariable Long contentsId,@RequestBody Long commentId) {
        try {
            //회원인지 검증 필요함
            // comment.user.nickname ? jwt.claim.get(nickname)
            //3개의 매개변수 들어가야함
            //
            //commentService.deleteComment(contentsId,commentId,userid);

        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
