package com.ssafy.wpwk.controller;

import com.ssafy.wpwk.model.Contents;
import com.ssafy.wpwk.service.ContentsServiceImpl;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;

@CrossOrigin(origins = {"*"}, maxAge = 6000)
@RestController
public class ContentsController {

    @Autowired
    private ContentsServiceImpl contentsService;

    @ApiOperation(value = "새로운 컨텐츠 제작(등록)")
    @PostMapping("/contents")
    public ResponseEntity<Void> create(@RequestBody Contents resource) {

        Contents contents = Contents.builder()
                                    .title(resource.getTitle())
                                    .spendTime(resource.getSpendTime())
                                    .createdBy("server1")
                                    .contentsItem(resource.getContentsItem())
                                    .updatedBy("server1")
                                    .build();

        try {
            contentsService.create(contents);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @ApiOperation(value = "회원이 클릭한 컨텐츠 제공", response = Contents.class)
    @GetMapping("/contents/{contentsId}")
    public ResponseEntity<Contents> findContentsById(@PathVariable Long contentsId) {
        Contents contents;
        try {
            contents = contentsService.findContentsById(contentsId);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(contents, HttpStatus.OK);
    }

    @ApiOperation(value = "키워드(태그,제목,제작자)로 검색한 컨텐츠 리스트 제공", response = List.class)
    @GetMapping("/contents/{option}/{keyword}")
    public ResponseEntity<List<Contents>> findContents(@PathVariable String option, @PathVariable String keyword) {
        List<Contents> contentsList;
        HashMap<String, String> map = new HashMap<>();
        try {

            map.put("option", option); // option : title, keyword : 보육
            map.put("keyword", keyword); // option : title, keyword : 보육
            contentsList = contentsService.findContentsByKeyword(map);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }

        if (contentsList == null)
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        else
            return new ResponseEntity<>(contentsList, HttpStatus.OK);
    }

    @ApiOperation(value = "모든 컨텐츠 리스트 제공", response = List.class)
    @GetMapping("/contents")
    public ResponseEntity<List<Contents>> findAllContents() {
        List<Contents> contentsList;
        try {
            contentsList = contentsService.findAllContents();
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(contentsList, HttpStatus.OK);
    }

    @ApiOperation(value = "컨텐츠 수정")
    @PutMapping("/contents")
    public ResponseEntity<Void> update(@RequestBody Contents contents) {
        try {
            contents.setUpdatedAt(LocalDateTime.now());
            contentsService.update(contents);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(HttpStatus.OK);
    }


    @ApiOperation(value = "컨텐츠 삭제")
    @DeleteMapping("/contents/{contentsId}")
    public ResponseEntity<Void> delete(@PathVariable Long contentsId) {
        try {
            contentsService.delete(contentsId);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(HttpStatus.OK);
    }

}