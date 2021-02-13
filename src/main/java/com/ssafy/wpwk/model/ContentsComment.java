package com.ssafy.wpwk.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;

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
    private Long userId;
    /**
     * 댓글 작성한 회원의 닉네임
     * */
    private String nickname;

    /**
     * 컨텐츠 id
     * */
    private Long contentsId;

    /**
     * 컨텐츠 댓글
     * */
    private String comment;

    /**
     * 컨텐츠 댓글 생성일자
     * */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime createdAt;

    /**
     * 컨텐츠 댓글 생성자
     * */
    private String createdBy;

    /**
     * 컨텐츠 댓글 수정일자
     * */
    private LocalDateTime updatedAt;

    /**
     * 컨텐츠 댓글 수정자
     * */
    private String updatedBy;
}
