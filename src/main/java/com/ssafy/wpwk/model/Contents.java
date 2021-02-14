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
     */
    private Long id;

    /**
     * 컨텐츠 제작자 id
     */
    private Long userId;

    /**
     * 컨텐츠 제작자 닉네임
     */
    private String nickname;

    /**
     * 컨텐츠 제목
     */
    private String title;

    /**
     * 컨텐츠 소요시간
     */
    private String spendTime;

    /**
     * 컨텐츠 좋아요 수
     */
    private int likes;

    /**
     * 컨텐츠 조회수
     */
    private int views;

    /**
     * 컨텐츠 역량
     */
    private String ability;

    /**
     * 컨텐츠 썸네일
     */
    private String thumb;

    /**
     * 컨텐츠 생성일자
     */
    private LocalDateTime createdAt;

    /**
     * 컨텐츠 생성자
     */
    private String createdBy;

    /**
     * 컨텐츠 수정일자
     */
    private LocalDateTime updatedAt;

    /**
     * 컨텐츠 수정자
     */
    private String updatedBy;

    /**
     * 컨텐츠 아이템
     */
    private List<ContentsItem> contentsItemList;

    /**
     * 컨텐츠 해시태그 리스트
     */
    private List<Tag> tagList;

    /**
     * 컨텐츠 즐겨찾기 여부
     */
    private boolean isFavorite;

    /**
     * 컨텐츠 평가 Education Point
     */
    private int evalEdu;

    /**
     * 컨텐츠 평가 Funny Point
     */
    private int evalFun;

    /**
     * 컨텐츠 평가 Access Point
     */
    private int evalAcs;

}
