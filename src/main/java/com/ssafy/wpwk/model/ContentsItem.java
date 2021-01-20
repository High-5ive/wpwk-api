package com.ssafy.wpwk.model;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@Builder
public class ContentsItem {

    /**
     * 컨텐츠 아이템 id
     * */
    private Long id;

    /**
     * 컨텐츠 유튜브 id
     * */
    private String youtubeId;

    /**
     * 컨텐츠 이미지 주소
     * */
    private String imageAddress;

    /**
     * 컨텐츠 동영상 주소
     * */
    private String videoAddress;

    /**
     * 컨텐츠 설명
     * */
    private String description;

    /**
     * 컨텐츠 페이지 번호
     * */
    private int pageNo;

    /**
     * 컨텐츠 아이디
     * */
    private Long contentsId;
}
