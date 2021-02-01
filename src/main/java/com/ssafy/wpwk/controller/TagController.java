package com.ssafy.wpwk.controller;

import com.ssafy.wpwk.model.Tag;
import com.ssafy.wpwk.service.TagService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@CrossOrigin(origins = {"*"}, maxAge = 6000)
@RestController
public class TagController {

    @Autowired
    private TagService tagService;
    
    // 해시태그 추가시 제시어 제공
    @ApiOperation(value = "해시태그 추가시 입력한 단어를 통해 제시어 제공")
    @GetMapping("/tags/{word}")
    public ResponseEntity<?> suggest(@PathVariable("word") String word) {

        List<Tag> tagList = tagService.getTagListByWord(word);

        return new ResponseEntity<>(tagList, HttpStatus.OK);
    }

    // 컨텐츠 아이디를 이용한 해시태그 가져오기(테스트용)
    @ApiOperation(value = "컨텐츠 아이디를 이용한 해시태그 가져오기(테스트용)")
    @GetMapping("/tags/contents/{id}")
    public ResponseEntity<?> suggest(@PathVariable("id") Long contentsId) {

        List<Tag> tagList = tagService.getTagListByContentsId(contentsId);

        return new ResponseEntity<>(tagList, HttpStatus.OK);
    }

    // 컨텐츠 태그리스트 추가
    @ApiOperation(value = "컨텐츠 태그리스트 추가")
    @PostMapping("/tags/contents/{id}")
    public ResponseEntity<?> create(@PathVariable("id") Long contentsId,
                                    @RequestBody Map<String, Object> resources) {

        String tagString = resources.values().toString(); // [":1", ":3", "30분"] 형태를 String으로 저장

        StringTokenizer st = new StringTokenizer(tagString, "[],"); // '[', ']', ','으로 분리
        List<String> tagList = new ArrayList<>();

        int size = st.countTokens();
        while(size-->0) { tagList.add(st.nextToken().trim()); } // 태그에 개수만큼 저장(저장시 공백 제거)

        tagService.createTags(contentsId, tagList);

        return new ResponseEntity<>(HttpStatus.CREATED);
    }

}
