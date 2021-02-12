package com.ssafy.wpwk.controller;

import com.ssafy.wpwk.model.Board;
import com.ssafy.wpwk.model.Contents;
import com.ssafy.wpwk.service.MyPageServiceImpl;
import io.jsonwebtoken.Claims;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import static com.ssafy.wpwk.utils.ExceptionUtil.isInValidAuthentication;

@CrossOrigin(origins = {"*"}, maxAge = 6000)
@RestController
public class MyPageController {

    @Autowired
    private MyPageServiceImpl myPageService;

    @ApiOperation(value = "사용자가 제작한 컨텐츠 리스트 조회", response = List.class)
    @GetMapping("/mypage/contents/page/{page}")
    public ResponseEntity<?> findMyContents(@PathVariable("page") int page, Authentication authentication) {

        if (isInValidAuthentication(authentication)) {
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        }

        List<Contents> contentsList;

        try {
            Claims claims = (Claims) authentication.getPrincipal();
            Long userId = claims.get("userId", Long.class);
            contentsList = myPageService.findMyContents(userId, page);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(contentsList, HttpStatus.OK);
    }

    @ApiOperation(value = "사용자가 작성한 게시글 리스트 조회", response = List.class)
    @GetMapping("/mypage/board/page/{page}")
    public ResponseEntity<?> findMyBoard(@PathVariable("page") int page, Authentication authentication) {

        if (isInValidAuthentication(authentication)) {
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        }

        List<Board> boardList;

        try {
            Claims claims = (Claims) authentication.getPrincipal();
            Long userId = claims.get("userId", Long.class);
            boardList = myPageService.findMyBoards(userId, page);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(boardList, HttpStatus.OK);
    }

    @ApiOperation(value = "사용자의 즐겨찾기 컨텐츠 리스트 조회", response = List.class)
    @GetMapping("/mypage/favoriteContents/page/{page}")
    public ResponseEntity<?> findContentsByFavorite(@PathVariable("page") int page, Authentication authentication) {

        if (isInValidAuthentication(authentication)) {
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        }

        List<Contents> contentsList;

        try {
            Claims claims = (Claims) authentication.getPrincipal();
            Long userId = claims.get("userId", Long.class);
            contentsList = myPageService.findContentsByFavorite(userId, page);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(contentsList, HttpStatus.OK);
    }

    @ApiOperation(value = "사용자가 댓글 단 게시글 리스트 조회", response = List.class)
    @GetMapping("/mypage/commentBoard/page/{page}")
    public ResponseEntity<?> findBoardsByComments(@PathVariable("page") int page, Authentication authentication) {

        if (isInValidAuthentication(authentication)) {
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        }

        List<Board> boardList;

        try {
            Claims claims = (Claims) authentication.getPrincipal();
            Long userId = claims.get("userId", Long.class);
            boardList = myPageService.findBoardsByComments(userId, page);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(boardList, HttpStatus.OK);
    }



}
