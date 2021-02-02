package com.ssafy.wpwk.model;


import lombok.*;

import java.time.LocalDateTime;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@Builder
public class Contents {

    /**
     * 컨텐츠 id
     * */
    private Long id;

    /**
     * 컨텐츠 제작자 id
     * */
    private User user;

    /**
     * 컨텐츠 제목
     * */
    private String title;

    /**
     * 컨텐츠 소요시간
     * */
    private String spendTime;

    /**
     * 컨텐츠 좋아요 수
     * */
    private int likes;

    /**
     * 컨텐츠 조회수
     * */
    private int views;

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

    /**
     * 컨텐츠 아이템
     * */
    private List<ContentsItem> contentsItemList;

    /**
     * 컨텐츠 해시태그 리스트
     * */
    private List<Tag> tagList;
}
