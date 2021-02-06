package com.ssafy.wpwk.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class BoardComment {

    /**
     * 댓글 아이디
     */
    private Long id;

    /**
     * 댓글 작성자
     */
    private String writer;

    /**
     * 댓글 작성한 회원의 id
     */
    private Long userId;

    /**
     * 게시글 id
     */
    private Long boardId;

    /**
     * 게시글 댓글
     */
    private String comment;

    /**
     * 게시글 생성일자
     */
    private LocalDateTime createdAt;

    /**
     * 게시글 생성자
     */
    private String createdBy;

    /**
     * 게시글 수정일자
     */
    private LocalDateTime updatedAt;

    /**
     * 게시글 수정자
     */
    private String updatedBy;
}