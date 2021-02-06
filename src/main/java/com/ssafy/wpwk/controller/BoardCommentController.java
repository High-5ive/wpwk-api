package com.ssafy.wpwk.controller;

import com.ssafy.wpwk.model.BoardComment;
import com.ssafy.wpwk.service.BoardCommentServiceImpl;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import static com.ssafy.wpwk.utils.ExceptionUtil.isInValidAuthentication;

@CrossOrigin(origins = {"*"}, maxAge = 6000)
@RestController
public class BoardCommentController {

    @Autowired
    private BoardCommentServiceImpl boardCommentService;

    @ApiOperation(value = "게시글 댓글 작성")
    @PostMapping("/board/comment")
    public ResponseEntity<?> addBoardComment(@RequestBody BoardComment comment, Authentication authentication) {

        if (isInValidAuthentication(authentication)) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        return new ResponseEntity<>(HttpStatus.OK);
    }

}
