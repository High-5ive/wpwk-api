package com.ssafy.wpwk.controller;

import com.ssafy.wpwk.model.Board;
import com.ssafy.wpwk.service.BoardServiceImpl;
import io.jsonwebtoken.Claims;
import io.swagger.annotations.ApiOperation;
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
    @PostMapping("/boards")
    public ResponseEntity<?> createBoard(@RequestBody Board resource, Authentication authentication) {

        if (isInValidAuthentication(authentication)) {
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        }

        Claims claims = (Claims) authentication.getPrincipal();
        Long userId = claims.get("userId", Long.class);
        String writer = claims.get("nickname", String.class);

        Board board = Board.builder()
                .userId(userId)
                .writer(writer)
                .category(resource.getCategory())
                .content(resource.getContent())
                .createdBy("server1")
                .updatedBy("server1")
                .build();

        try {
            boardService.create(board);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @ApiOperation(value = "게시글 전체 조회")
    @GetMapping("/boards")
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
    @GetMapping("/boards/page/{page}")
    public ResponseEntity<?> findAllByOffset(@PathVariable("page") int page,Authentication authentication) {
        int offset = (page-1)*10;
        List<Board> boardList;

        if (isInValidAuthentication(authentication)) {
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        }
        Claims claims = (Claims)authentication.getPrincipal();
        Long userId = claims.get("userId", Long.class);

        try {
            boardList = boardService.findAllByOffset(offset,userId);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(boardList, HttpStatus.OK);
    }

    @ApiOperation(value = "ID를 이용한 게시글 상세 조회")
    @GetMapping("/boards/{id}")
    public ResponseEntity<?> findBoardById(@PathVariable("id") Long id, Authentication authentication) {

        Board board;

        if (isInValidAuthentication(authentication)) {
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        }
        Claims claims = (Claims)authentication.getPrincipal();
        Long userId = claims.get("userId", Long.class);
        try {
            board = boardService.findById(id,userId);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(board, HttpStatus.OK);
    }

    @ApiOperation(value = "카테고리별 게시글 조회")
    @GetMapping("/boards/category/{category}/page/{page}")
    public ResponseEntity<?> findBoardByCategory(@PathVariable("category") String category,
                                                 @PathVariable("page") int page ,Authentication authentication) {
        int offset = (page-1)*10;
        List<Board> boardList;
        if (isInValidAuthentication(authentication)) {
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        }
        Claims claims = (Claims)authentication.getPrincipal();
        Long userId = claims.get("userId", Long.class);
        try {
            boardList = boardService.findByCategory(category, offset,userId);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(boardList, HttpStatus.OK);

    }

    @ApiOperation(value = "게시글 수정")
    @PutMapping("/boards")
    public ResponseEntity<?> updateBoard(@RequestBody Board board, Authentication authentication) {
        if (isInValidAuthentication(authentication)) {
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        }

        Claims claims = (Claims) authentication.getPrincipal();
        Long userId = claims.get("userId", Long.class);
        board.setUserId(userId);

        try {
            boardService.update(board);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @ApiOperation(value = "게시글 삭제")
    @DeleteMapping("/boards/{id}")
    public ResponseEntity<?> deleteBoard(@PathVariable("id") Long id, Authentication authentication) {
        if (isInValidAuthentication(authentication)) {
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        }

        Claims claims = (Claims) authentication.getPrincipal();
        Long userId = claims.get("userId", Long.class);

        try {
            boardService.delete(id, userId);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @ApiOperation(value = "게시글 좋아요(증가,감소)")
    @PutMapping("/boards/{id}/likes")
    public ResponseEntity<?> likesUpdateBoard(@PathVariable("id") Long id,
            @RequestBody Map<String, Object> map, Authentication authentication) {

        if (isInValidAuthentication(authentication)) {
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        }

        Claims claims = (Claims) authentication.getPrincipal();

        Long userId = claims.get("userId", Long.class);
        int likes = Integer.parseInt(map.get("likes").toString());
        try {
            boardService.updateLikes(id, userId, likes);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        return new ResponseEntity<>(HttpStatus.OK);
    }

}
