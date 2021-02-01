package com.ssafy.wpwk.mappers;

import com.ssafy.wpwk.model.Tag;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface TagMapper {

    /***
     * 해시태그 추가시 입력한 단어를 통해 제시어 제공
     */
    List<Tag> getTagListByWord(String word);

    /***
     * 해당 컨텐츠 태그리스트 가져오기
     */
    List<Tag> getTagListByContentsId(Long contentsId);
}
