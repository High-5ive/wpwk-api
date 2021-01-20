package com.ssafy.wpwk.controller;

import com.ssafy.wpwk.model.Contents;
import com.ssafy.wpwk.model.ContentsComment;
import com.ssafy.wpwk.service.ContentsServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;

@RestController
public class ContentsController {

    @Autowired
    ContentsServiceImpl contentsService;

    @PostMapping("/contents")
    public ResponseEntity<Void> create(@RequestBody Contents contents) {
        //todo
        try {
            contentsService.create(contents);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("/contents/{contentsId}")
    public ResponseEntity<Contents> findContentsById(@PathVariable Long contentsId) {
        Contents contents = null;
        try {
            contents = contentsService.findContentsById(contentsId);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(contents, HttpStatus.OK);
    }

    @GetMapping("/contents/{option}/{keyWord}")
    public ResponseEntity<List<Contents>> findContents(@PathVariable String option, @PathVariable String keyWord) {
        List<Contents> contentsList = null;
        HashMap<String, String> map = new HashMap<>();
        try {
            // hash
            // 이름 map.put("name",keyword);
            // 제작자 map.put("creator", keyword);
            // 해시태그 map.put("tag" ,keyword);
            map.put(option, keyWord);
            contentsList = contentsService.findContentsByKeyword(map);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }

        if (contentsList == null)
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        else
            return new ResponseEntity<List<Contents>>(contentsList, HttpStatus.OK);
    }

    @GetMapping("/contents")
    public ResponseEntity<List<Contents>> findAllContents() {
        List<Contents> contentsList = null;
        try {
            contentsList = contentsService.findAllContents();
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<List<Contents>>(contentsList, HttpStatus.OK);
    }

    @PutMapping("/contents/{contentsId}")
    public ResponseEntity<Void> update(@PathVariable Long contentsId, @RequestBody Contents contents) {
        try {
            contentsService.update(contentsId, contents);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping("/contents/{contentsId}")
    public ResponseEntity<Void> delete(@PathVariable Long contentsId) {
        try {
            contentsService.delete(contentsId);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PostMapping("/contents/comment")
    public ResponseEntity<Void> addComment(@RequestBody ContentsComment comment) {
        try {
            contentsService.addComment(comment);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }

        return new ResponseEntity<>(HttpStatus.OK);
    }


}
