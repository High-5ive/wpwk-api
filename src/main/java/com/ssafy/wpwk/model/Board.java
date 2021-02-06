package com.ssafy.wpwk.model;

import lombok.*;

import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@Builder
public class Board {

    /**
     * 게시글 id
     * */
    private Long id;

    /**
     * 게시글 작성자 아이디
     * */
    private Long userId;

    /**
     * 게시글 작성자 닉네임
     * */
    private String writer;

    /**
     * 게시글 좋아요 개수
     * */
    private int likes;

    /**
     * 게시글 조회수
     * */
    private int views;

    /**
     * 게시글 생성일자
     * */
    private LocalDateTime createdAt;

    /**
     * 게시글 생성자
     * */
    private String createdBy;

    /**
     * 게시글 수정일자
     * */
    private LocalDateTime updatedAt;

    /**
     * 게시글 수정자
     * */
    private String updatedBy;
}
