package com.ssafy.wpwk.service;

import com.ssafy.wpwk.model.Contents;
import com.ssafy.wpwk.model.ContentsComment;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;


public interface ContentsService {

    /*
    컨텐츠 CRUD
     */
    Long create(Contents contents) throws Exception;

    Contents findContentsById(Long contentsId) throws Exception;

    List<Contents> findContentsByKeyword(HashMap<String, String> map) throws Exception;

    List<Contents> findAllContents() throws Exception;

    void update(Contents contents) throws Exception;

    void delete(Long contentsId) throws Exception;


}
