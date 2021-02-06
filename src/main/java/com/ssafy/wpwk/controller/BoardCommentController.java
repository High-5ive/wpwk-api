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
    @PostMapping("/boardComments/{boardId}")
    public ResponseEntity<?> addBoardComment(
            @PathVariable("boardId") Long boardId,
            @RequestBody BoardComment resource,
            Authentication authentication) {

        if (isInValidAuthentication(authentication)) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        Claims claims = (Claims) authentication.getPrincipal();
        Long userId = claims.get("userId", Long.class);
        String writer = claims.get("nickname", String.class);

        BoardComment boardComment = BoardComment
                .builder()
                .comment(resource.getComment())
                .boardId(boardId)
                .userId(userId)
                .writer(writer)
                .build();

        try {
            boardCommentService.addBoardComment(boardComment);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @ApiOperation(value = "게시글 댓글 전체 조회")
    @GetMapping("/boardComments")
    public ResponseEntity<?> findAll() {
        List<BoardComment> boardCommentList;

        try {
            boardCommentList = boardCommentService.allBoardComments();
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(boardCommentList, HttpStatus.OK);
    }

    @ApiOperation(value = "게시글 ID를 이용한 게시글 댓글 조회")
    @GetMapping("/boardComments/boards/{boardId}/page/{page}")
    public ResponseEntity<?> findByBoardIdAndOffset(
            @PathVariable("boardId") Long boardId,
            @PathVariable("page") int page
    ) {
        int offset = (page-1) * 15;
        List<BoardComment> boardCommentList;

        try {
            boardCommentList = boardCommentService.findByBoardIdAndOffset(boardId, offset);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(boardCommentList, HttpStatus.OK);
    }

    @ApiOperation(value = "게시글 댓글 수정")
    @PutMapping("/boardComments")
    public ResponseEntity<?> updateBoardComment(@RequestBody BoardComment boardComment, Authentication authentication) {
        if (isInValidAuthentication(authentication)) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        Claims claims = (Claims) authentication.getPrincipal();
        Long userId = claims.get("userId", Long.class);
        boardComment.setUserId(userId);

        try {
            boardCommentService.updateComment(boardComment);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @ApiOperation(value = "게시글 댓글 삭제")
    @DeleteMapping("/boardComments/{id}")
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
