package com.ssafy.wpwk.mappers;

import com.ssafy.wpwk.model.Contents;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Mapper
public interface ContentsMapper {

    Long create(Contents contents) throws Exception;

    Contents findContents(Long id) throws Exception;

    List<Contents> findAllContents() throws Exception;

    void update(@Param("id")Long id,
                @Param("contents")Contents contents) throws Exception;

    void delete(Long id) throws Exception;
}