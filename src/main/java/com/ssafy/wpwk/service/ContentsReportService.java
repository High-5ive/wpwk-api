package com.ssafy.wpwk.service;

import com.ssafy.wpwk.model.ContentsReport;
import com.ssafy.wpwk.model.ReportRequsetDTO;

import java.util.List;

public interface ContentsReportService {

    /**
     *  모든 신고 정보 조회
     */
    List<ContentsReport> allReports();

    /**
     *  신고 정보 추가
     */
    void addReport(ReportRequsetDTO reportRequsetDTO, Long userId);


}
