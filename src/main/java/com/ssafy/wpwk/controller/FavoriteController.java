package com.ssafy.wpwk.controller;

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
public class FavoriteController {

    @Autowired
    private ContentsFavoriteServiceImpl contentsFavoriteService;

    @ApiOperation(value = "컨텐츠 즐겨찾기 등록")
    @PostMapping("/contents/favorite")
    public ResponseEntity<?> addFavoriteContents(@RequestBody Map<String, Object> resource,
                                                 Authentication authentication) {
        if (isInValidAuthentication(authentication)) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        Claims claims = (Claims)authentication.getPrincipal();
        Long userId = claims.get("userId", Long.class);
        try {
            Long id = Long.parseLong(resource.get("id").toString());
            contentsFavoriteService.addFavoriteContents(id,userId);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @ApiOperation(value = "컨텐츠 즐겨찾기 해제")
    @DeleteMapping("/contents/favorite/{id}")
    public ResponseEntity<?> deleteFavoriteContents(@PathVariable("id") Long id, Authentication authentication) {
        if (isInValidAuthentication(authentication)) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        Claims claims = (Claims)authentication.getPrincipal();
        Long userId = claims.get("userId", Long.class);
        try {
            contentsFavoriteService.deleteFavoriteContents(id,userId);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        return new ResponseEntity<>(HttpStatus.OK);
    }

}
