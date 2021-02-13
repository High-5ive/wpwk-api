package com.ssafy.wpwk.model;

import lombok.Data;

@Data
public class ContentsEndRequestDTO {

    //컨텐츠의 역량 정보
    private String ability;

    //컨텐츠 Id
    private Long contentsId;

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
