package com.ssafy.wpwk.model;

import lombok.*;

import java.time.LocalDateTime;


@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class ContentsComment {

    /*
     * 댓글의 id
     */
    private Long id;

    /**
     * 댓글 작성한 회원의 id
     * */
    private User user;

    /**
     * 컨텐츠 id
     * */
    private Long contentsId;

    /**
     * 컨텐츠 댓글
     * */
    private String comment;

    /**
     * 컨텐츠 생성일자
     * */
    private LocalDateTime createdAt;

    /**
     * 컨텐츠 생성자
     * */
    private String createdBy;

    /**
     * 컨텐츠 수정일자
     * */
    private LocalDateTime updatedAt;

    /**
     * 컨텐츠 수정자
     * */
    private String updatedBy;
}
