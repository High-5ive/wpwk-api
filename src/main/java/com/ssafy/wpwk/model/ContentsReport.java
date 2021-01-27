package com.ssafy.wpwk.model;

import lombok.*;

import java.time.LocalDateTime;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
@Setter
public class ContentsReport {

    /*
     * 컨텐츠 신고건의 id
     */
    private Long id;

    /*
     * 컨텐츠 신고자의 id
     */
    private User user;

    /*
     * 신고당한 컨텐츠의 id
     */
    private Contents contents;

    /*
     * 컨텐츠 신고 내역
     */
    private String comment;

    /**
     * 컨텐츠 신고 일자
     * */
    private LocalDateTime createdAt;

    /**
     * 컨텐츠 신고 생성자
     * */
    private String createdBy;

}
