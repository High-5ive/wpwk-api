package com.ssafy.wpwk.mappers;

import com.ssafy.wpwk.model.ContentsReport;
import com.ssafy.wpwk.model.ReportRequsetDTO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Mapper
public interface ContentsReportMapper {

    /**
     *  모든 신고 정보 조회
     */
    List<ContentsReport> allReports();

    /**
     *  신고 정보 추가
     */
    void addReport(@Param("reportRequestDTO") ReportRequsetDTO reportRequsetDTO,@Param("userId") Long userId);
}
