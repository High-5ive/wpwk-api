package com.ssafy.wpwk.controller;

import com.ssafy.wpwk.model.Contents;
import com.ssafy.wpwk.model.ContentsItem;
import com.ssafy.wpwk.service.ContentsServiceImpl;
import io.jsonwebtoken.Claims;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static com.ssafy.wpwk.utils.ExceptionUtil.isInValidAuthentication;

@CrossOrigin(origins = {"*"}, maxAge = 6000)
@RestController
public class ContentsController {

    Logger logger = LoggerFactory.getLogger(UserController.class);

    @Autowired
    private ContentsServiceImpl contentsService;

    @ApiOperation(value = "새로운 컨텐츠 제작(등록)")
    @PostMapping("/contents")
    public ResponseEntity<?> create(@RequestBody Contents resource, Authentication authentication) {

        if (isInValidAuthentication(authentication)) {
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        }

        Claims claims = (Claims) authentication.getPrincipal();
        Long userId = claims.get("userId", Long.class);

        Contents contents = Contents.builder()
                .title(resource.getTitle())
                .spendTime(resource.getSpendTime())
                .ability(resource.getAbility())
                .userId(userId)
                .createdBy("server1")
                .contentsItemList(resource.getContentsItemList())
                .updatedBy("server1")
                .build();

        try {
            contentsService.create(contents);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(contents.getId(), HttpStatus.OK);
    }

    @ApiOperation(value = "회원이 클릭한 컨텐츠 제공", response = Contents.class)
    @GetMapping("/contents/{id}")
    public ResponseEntity<?> findContentsById(@PathVariable Long id) {
        Contents contents;
        try {
            contents = contentsService.findContentsById(id);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        if (contents == null) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(contents, HttpStatus.OK);
    }

    @ApiOperation(value = "키워드(태그,제목,제작자)가 포함된 컨텐츠 리스트 제공", response = List.class)
    @GetMapping("/contents/keyword/{keyword}/page/{page}")
    public ResponseEntity<?> findContents(@PathVariable("keyword") String keyword, @PathVariable("page") int page, Authentication authentication) {

        if (isInValidAuthentication(authentication)) {
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        }

        List<Contents> contentsList;

        try {
            Claims claims = (Claims) authentication.getPrincipal();
            Long userId = claims.get("userId", Long.class);
            contentsList = contentsService.findContentsByKeyword(keyword, page, userId);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        if (contentsList == null)
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        else
            return new ResponseEntity<>(contentsList, HttpStatus.OK);
    }

    @ApiOperation(value = "태그가 포함된 컨텐츠 리스트 제공", response = List.class)
    @GetMapping("/contents/tags/{tag}/page/{page}")
    public ResponseEntity<?> findContentsByTag(@PathVariable("tag") String tag,
                                               @PathVariable("page") int page, Authentication authentication) {
        List<Contents> contentsList;

        if (isInValidAuthentication(authentication)) {
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        }

        try {
            Claims claims = (Claims) authentication.getPrincipal();
            Long userId = claims.get("userId", Long.class);
            contentsList = contentsService.findContentsByTag(tag, page, userId);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        if (contentsList == null)
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        else
            return new ResponseEntity<>(contentsList, HttpStatus.OK);
    }

    @ApiOperation(value = "카테고리 검색 컨텐츠 리스트 제공", response = List.class)
    @GetMapping("/contents/category/{category}/page/{page}")
    public ResponseEntity<?> findContentsByTag(@PathVariable("category") int category,
                                               @PathVariable("page") int page, Authentication authentication) {
        List<Contents> contentsList;

        if (isInValidAuthentication(authentication)) {
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        }

        try {
            Claims claims = (Claims) authentication.getPrincipal();
            Long userId = claims.get("userId", Long.class);
            contentsList = contentsService.findContentsByCategory(category, page, userId);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        if (contentsList == null)
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        else
            return new ResponseEntity<>(contentsList, HttpStatus.OK);
    }

    @ApiOperation(value = "모든 컨텐츠 리스트 제공", response = List.class)
    @GetMapping("/contents")
    public ResponseEntity<?> findAllContents() {
        List<Contents> contentsList;
        try {
            contentsList = contentsService.findAllContents();
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        if (contentsList == null)
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        else
            return new ResponseEntity<>(contentsList, HttpStatus.OK);
    }

    @ApiOperation(value = "페이지별 컨텐츠 리스트 제공", response = List.class)
    @GetMapping("/contents/page/{page}")
    public ResponseEntity<?> findAllContentsByPage(@PathVariable("page") int page, Authentication authentication) {

        if (isInValidAuthentication(authentication)) {
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        }

        List<Contents> contentsList;

        try {
            Claims claims = (Claims) authentication.getPrincipal();
            Long userId = claims.get("userId", Long.class);
            contentsList = contentsService.findAllContentsByPage(page, userId);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        if (contentsList == null)
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        else
            return new ResponseEntity<>(contentsList, HttpStatus.OK);
    }

    @ApiOperation(value = "컨텐츠의 조회수 내림차순으로 리스트 제공", response = List.class)
    @GetMapping("/contents/views/page/{page}")
    public ResponseEntity<?> findContentsByViews(@PathVariable("page") int page, Authentication authentication) {

        if (isInValidAuthentication(authentication)) {
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        }

        List<Contents> contentsList;

        try {
            Claims claims = (Claims) authentication.getPrincipal();
            Long userId = claims.get("userId", Long.class);
            contentsList = contentsService.findContentsByViews(page, userId);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(contentsList, HttpStatus.OK);
    }

    @ApiOperation(value = "컨텐츠의 좋아요수 내림차순으로 리스트 제공", response = List.class)
    @GetMapping("/contents/likes/page/{page}")
    public ResponseEntity<?> findContentsByLikes(@PathVariable("page") int page, Authentication authentication) {

        if (isInValidAuthentication(authentication)) {
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        }

        List<Contents> contentsList;

        try {
            Claims claims = (Claims) authentication.getPrincipal();
            Long userId = claims.get("userId", Long.class);
            contentsList = contentsService.findContentsByLikes(page, userId);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(contentsList, HttpStatus.OK);
    }


    @ApiOperation(value = "컨텐츠 수정")
    @PutMapping("/contents")
    public ResponseEntity<?> update(@RequestBody Contents resource, Authentication authentication) {

        if (isInValidAuthentication(authentication)) {
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        }

        try {

            Claims claims = (Claims) authentication.getPrincipal();
            Long userId = claims.get("userId", Long.class);
            int status = claims.get("status", Integer.class);
            
            if(status != 2 && !resource.getUserId().equals(userId)) { // 관리자도 아니고 제작자가 아닌 경우
                return new ResponseEntity<>(HttpStatus.FORBIDDEN); // 권한이 없는 경우
            }
            contentsService.update(resource);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        return new ResponseEntity<>(HttpStatus.OK);
    }

    @ApiOperation(value = "컨텐츠 삭제")
    @DeleteMapping("/contents/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id) {
        try {
            contentsService.delete(id);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        return new ResponseEntity<>(HttpStatus.OK);
    }


}