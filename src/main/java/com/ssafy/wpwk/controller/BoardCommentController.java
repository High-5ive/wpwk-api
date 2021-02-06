package com.ssafy.wpwk.controller;

import com.ssafy.wpwk.model.BoardComment;
import com.ssafy.wpwk.service.BoardCommentServiceImpl;
import io.jsonwebtoken.Claims;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.parameters.P;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static com.ssafy.wpwk.utils.ExceptionUtil.isInValidAuthentication;

@CrossOrigin(origins = {"*"}, maxAge = 6000)
@RestController
public class BoardCommentController {

    @Autowired
    private BoardCommentServiceImpl boardCommentService;

    @ApiOperation(value = "게시글 댓글 작성")
    @PostMapping("/board/comment")
    public ResponseEntity<?> addBoardComment(@RequestBody BoardComment comment, Authentication authentication) {

        if (isInValidAuthentication(authentication)) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        try {
            boardCommentService.addBoardComment(comment);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @ApiOperation(value = "게시글 댓글 전체 조회")
    @GetMapping("/board")
    public ResponseEntity<?> findALl() {
        List<BoardComment> boardCommentList;

        try {
            boardCommentList = boardCommentService.allBoardComments();
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(boardCommentList, HttpStatus.OK);
    }

    @ApiOperation(value = "게시글 댓글 수정")
    @PutMapping("/board")
    public ResponseEntity<?> updateBoardComment(@RequestBody BoardComment boardComment, Authentication authentication) {
        if (isInValidAuthentication(authentication)) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        try {
            boardCommentService.updateComment(boardComment);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @ApiOperation(value = "게시글 댓글 삭제")
    @DeleteMapping("/board/{id}")
    public ResponseEntity<?> deleteBoardComment(@PathVariable("id") Long id, Authentication authentication) {
        if (isInValidAuthentication(authentication)) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        Claims claims = (Claims) authentication.getPrincipal();
        Long userId = claims.get("userId", Long.class);
        try {
            boardCommentService.deleteComment(id, userId);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(HttpStatus.OK);
    }

}
