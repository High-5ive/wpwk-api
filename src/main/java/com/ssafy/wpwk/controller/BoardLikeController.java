package com.ssafy.wpwk.controller;

import com.ssafy.wpwk.service.BoardLikeService;
import com.ssafy.wpwk.service.ContentsFavoriteServiceImpl;
import io.jsonwebtoken.Claims;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

import static com.ssafy.wpwk.utils.ExceptionUtil.isInValidAuthentication;

@CrossOrigin(origins = {"*"}, maxAge = 6000)
@RestController
public class BoardLikeController {

    @Autowired
    private BoardLikeService boardLikeService;

    @ApiOperation(value = "게시 글 좋아요 등록")
    @PostMapping("/boards/like")
    public ResponseEntity<?> likeBoard(@RequestBody Map<String, Object> resource,
                                                 Authentication authentication) {

        if (isInValidAuthentication(authentication)) {
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        }
        Claims claims = (Claims)authentication.getPrincipal();
        Long userId = claims.get("userId", Long.class);
        try {
            Long id = Long.parseLong(resource.get("id").toString());
            boardLikeService.likeBoard(id, userId);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @ApiOperation(value = "게시 글 좋아요 해제")
    @DeleteMapping("/boards/like/{id}")
    public ResponseEntity<?> deleteLikeBoard(@PathVariable("id") Long id, Authentication authentication) {

        if (isInValidAuthentication(authentication)) {
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        }
        Claims claims = (Claims)authentication.getPrincipal();
        Long userId = claims.get("userId", Long.class);
        try {
            boardLikeService.deleteLikeBoard(id, userId);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        return new ResponseEntity<>(HttpStatus.OK);
    }
}
