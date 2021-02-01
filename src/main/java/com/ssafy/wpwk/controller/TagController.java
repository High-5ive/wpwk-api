package com.ssafy.wpwk.controller;

import com.ssafy.wpwk.model.Tag;
import com.ssafy.wpwk.service.TagService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@CrossOrigin(origins = {"*"}, maxAge = 6000)
@RestController
public class TagController {

    @Autowired
    private TagService tagService;
    
    // 1. 해시태그 추가시 제시어 제공
    @ApiOperation(value = "해시태그 추가시 입력한 단어를 통해 제시어 제공")
    @GetMapping("/tags/{word}")
    public ResponseEntity<?> suggest(@PathVariable("word") String word) {

        List<Tag> tagList = tagService.getTagListByWord(word);

        return new ResponseEntity<>(tagList, HttpStatus.OK);
    }

    // 1. 컨텐츠 아이디를 이용한 해시태그 가져오기(테스트용)
    @ApiOperation(value = "컨텐츠 아이디를 이용한 해시태그 가져오기(테스트용)")
    @GetMapping("/tags/contents/{id}")
    public ResponseEntity<?> suggest(@PathVariable("id") Long contentsId) {

        List<Tag> tagList = tagService.getTagListByContentsId(contentsId);

        return new ResponseEntity<>(tagList, HttpStatus.OK);
    }

}
