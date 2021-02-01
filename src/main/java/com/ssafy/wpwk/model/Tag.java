package com.ssafy.wpwk.model;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@Builder
public class Tag {

    /**
     * 태그 기본키 id
     * */
    private Long id;

    /**
     * 태그 이름
     * */
    private String name;

    /**
     * 태그 사용 수
     * */
    private int count;
}
