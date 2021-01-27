package com.ssafy.wpwk.service;

import com.ssafy.wpwk.mappers.ContentsReportMapper;
import com.ssafy.wpwk.model.ContentsReport;
import com.ssafy.wpwk.model.ReportRequsetDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ContentsReportServiceImpl implements ContentsReportService{

    @Autowired
    private ContentsReportMapper contentsReportMapper;

    /**
     *  모든 신고 정보 조회
     */
    @Override
    public List<ContentsReport> allReports() {
        return contentsReportMapper.allReports();
    }

    /**
     *  신고 정보 추가
     */
    @Override
    public void addReport(ReportRequsetDTO reportRequsetDTO, Long userId) {
        contentsReportMapper.addReport(reportRequsetDTO, userId);
    }
}
