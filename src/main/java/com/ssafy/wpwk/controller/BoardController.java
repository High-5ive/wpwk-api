package com.ssafy.wpwk.controller;

import com.ssafy.wpwk.model.Board;
import com.ssafy.wpwk.service.BoardServiceImpl;
import io.jsonwebtoken.Claims;
import io.swagger.annotations.ApiOperation;
import org.springframework.aop.AfterReturningAdvice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

import static com.ssafy.wpwk.utils.ExceptionUtil.isInValidAuthentication;

@CrossOrigin(origins = {"*"}, maxAge = 6000)
@RestController
public class BoardController {

    @Autowired
    private BoardServiceImpl boardService;

    @ApiOperation(value = "게시글 생성")
    @PostMapping("/board")
    public ResponseEntity<?> createBoard(@RequestBody Board board, Authentication authentication) {

        if (isInValidAuthentication(authentication)) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        try {
            boardService.create(board);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        return new ResponseEntity<>(HttpStatus.OK);
    }

    @ApiOperation(value = "게시글 전체 조회")
    @GetMapping("/board")
    public ResponseEntity<?> findAllBoard() {
        List<Board> boardList;
        try {
            boardList = boardService.findAll();
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(boardList, HttpStatus.OK);
    }

    @ApiOperation(value = "페이지별 게시글 조회")
    @GetMapping("/board/page/{page}")
    public ResponseEntity<?> findAllByOffset(@PathVariable("page") int page) {
        List<Board> boardList;
        try {
            boardList = boardService.findAllByOffset(page);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(boardList, HttpStatus.OK);
    }

    @ApiOperation(value = "ID를 이용한 게시글 상세 조회")
    @GetMapping("/board/{id}")
    public ResponseEntity<?> findBoardById(@PathVariable("id") Long id, Authentication authentication) {

        Board board;

        if (isInValidAuthentication(authentication)) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        try {
            board = boardService.findById(id);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(board, HttpStatus.OK);
    }

    @ApiOperation(value = "카테고리별 게시글 조회")
    @GetMapping("/board/{category}")
    public ResponseEntity<?> findBoardByCategory(@PathVariable("category") String category) {
        Board board;
        try {
            board = boardService.findByCategory(category);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(board, HttpStatus.OK);

    }

    @ApiOperation(value = "게시글 수정")
    @PutMapping("/board")
    public ResponseEntity<?> updateBoard(@RequestBody Board board, Authentication authentication) {
        if (isInValidAuthentication(authentication)) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        try {
            boardService.update(board);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @ApiOperation(value = "게시글 삭제")
    @DeleteMapping("/board/{id}")
    public ResponseEntity<?> deleteBoard(@PathVariable("id") Long id, Authentication authentication) {
        if (isInValidAuthentication(authentication)) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        try {
            boardService.delete(id);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @ApiOperation(value = "게시글 좋아요(증가,감소)")
    @PutMapping("/board/likes")
    public ResponseEntity<?> likesUpdateBoard(@RequestBody Map<String, Object> map, Authentication authentication) {

        if (isInValidAuthentication(authentication)) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        Claims claims = (Claims) authentication.getPrincipal();

        Long userId = claims.get("userId", Long.class);
        int likes = (int) map.get("likes");
        try {
            boardService.updateLikes(userId, likes);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        return new ResponseEntity<>(HttpStatus.OK);
    }

}
