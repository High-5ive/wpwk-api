package com.ssafy.wpwk.controller;

import com.ssafy.wpwk.model.ContentsItem;
import com.ssafy.wpwk.service.ContentsItemServiceImpl;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = {"*"}, maxAge = 6000)
@RestController
public class ContentsItemController {

    @Autowired
    ContentsItemServiceImpl contentsItemService;

    @ApiOperation(value = "컨텐츠 아이템 정보 가져오기")
    @GetMapping("/contentsItem/{id}")
    public ResponseEntity<?> readByPage(@PathVariable("id") Long id) {

        List<ContentsItem> contentsItemList;

        try {
            contentsItemList = contentsItemService.findByContentsItemList(id);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        if (contentsItemList == null) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT); // 해당 아이템이 없기때문에 NO CONTENT 전닫
        }
        return new ResponseEntity<>(contentsItemList, HttpStatus.OK);
    }

    @ApiOperation(value = "컨텐츠 아이템 수정")
    @PutMapping("/contentsItem/{contentsId}")
    public ResponseEntity<Void> update(@PathVariable("contentsId") Long contentsId,
                                       @RequestBody List<ContentsItem> contentsItemList) {

        try {
            contentsItemService.updateByContentsId(contentsId, contentsItemList);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
