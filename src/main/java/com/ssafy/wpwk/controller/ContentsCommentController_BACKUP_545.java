package com.ssafy.wpwk.controller;

import com.ssafy.wpwk.model.ContentsComment;
import com.ssafy.wpwk.model.User;
import com.ssafy.wpwk.service.ContentsCommentServiceImpl;
import io.jsonwebtoken.Claims;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.sql.SQLOutput;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

import static com.ssafy.wpwk.utils.ExceptionUtil.isInValidAuthentication;

@CrossOrigin(origins = "{*}.", maxAge = 6000)
@RestController
public class ContentsCommentController {

    @Autowired
    private ContentsCommentServiceImpl commentService;

    @ApiOperation(value = "컨텐츠의 모든 댓글 조회")
    @GetMapping("/contentsComments/{contentsId}")
    public ResponseEntity<?> allComments(@PathVariable Long contentsId) {

        List<ContentsComment> commentList;
        try {
            commentList = commentService.allComments(contentsId);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        return new ResponseEntity<>(commentList, HttpStatus.OK);
    }

    @ApiOperation(value = "컨텐츠 댓글 등록")
    @PostMapping("/contentsComments")
    public ResponseEntity<?> addComment(@RequestBody ContentsComment comment, Authentication authentication) {
        if (isInValidAuthentication(authentication)) {
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        }
        try {
            Claims claims = (Claims) authentication.getPrincipal();

            Long userId = claims.get("userId", Long.class);
            String nickname = claims.get("nickname", String.class);

            comment.setUserId(userId);
            comment.setNickname(nickname);

            setUpdate(comment);

            commentService.addComment(comment);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        return new ResponseEntity<>(HttpStatus.OK);
    }

    @ApiOperation(value = "컨텐츠 댓글 수정")
<<<<<<< HEAD
    @PutMapping("/contentsComments/{commentId}")
    public ResponseEntity<?> updateComment(@PathVariable("commentId") Long commentId, @RequestBody Map<String, Object> map, Authentication authentication) {
=======
    @PutMapping("/contentsComments")
    public ResponseEntity<?> updateComment(@RequestBody Map<String, Object> map, Authentication authentication) {
>>>>>>> feature-contents-evaluations
        if (isInValidAuthentication(authentication)) {
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        }

        try {
            Claims claims = (Claims) authentication.getPrincipal();

            Long userId = claims.get("userId", Long.class);
            Long commentId = Long.parseLong( map.get("commentId").toString());
            String newComment = (String) map.get("comment");

            ContentsComment contentsComment = ContentsComment.builder()
                    .id(commentId)
                    .userId((userId))
                    .comment(newComment).build();

            setUpdate(contentsComment);
            commentService.updateComment(contentsComment);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(HttpStatus.OK);

    }

    @ApiOperation(value = "컨텐츠 댓글 삭제")
    @DeleteMapping("/contentsComments/{commentId}")
    public ResponseEntity<Void> deleteComment(@PathVariable Long commentId, Authentication authentication) {
        if (isInValidAuthentication(authentication)) {
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        }
        try {
            Claims claims = (Claims) authentication.getPrincipal();

            Long userId = claims.get("userId", Long.class);
            commentService.deleteComment( commentId,userId);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(HttpStatus.OK);
    }

    /**
     * 사용자 정보 업데이트시 공통 호출 메서드
     */
    public void setUpdate(ContentsComment comment) {
        comment.setUpdatedBy("server1");
        comment.setCreatedBy("server1");
        comment.setUpdatedAt(LocalDateTime.now());
    }
}
