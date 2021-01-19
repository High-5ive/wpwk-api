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
    long create(Contents contents) throws Exception;

    Contents findContents(HashMap<String, String> map) throws Exception;

    List<Contents> findAllContents() throws Exception;

    void update(long contentsId, Contents contents) throws Exception;

    void delete(long contentsId) throws Exception;

    /*
    댓글 CRUD
     */
    long addComment(ContentsComment comment) throws Exception;

    List<ContentsComment> allComments(String contentsId) throws Exception;


}
