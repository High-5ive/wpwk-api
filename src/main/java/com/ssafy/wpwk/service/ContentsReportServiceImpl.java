package com.ssafy.wpwk.service;

import com.ssafy.wpwk.enums.MessageType;
import com.ssafy.wpwk.mappers.ContentsReportMapper;
import com.ssafy.wpwk.model.ContentsReport;
import com.ssafy.wpwk.model.ReportRequsetDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ContentsReportServiceImpl implements ContentsReportService {

    @Autowired
    private ContentsReportMapper contentsReportMapper;

    @Autowired
    private NotificationService notificationService;

    @Autowired
    private ContentsService contentsService;

    /**
     * 모든 신고 정보 조회
     */
    @Override
    public List<ContentsReport> allReports() {
        return contentsReportMapper.allReports();
    }

    /**
     * 신고 정보 추가
     */
    @Override
    public void addReport(ReportRequsetDTO reportRequsetDTO, Long userId) {
        contentsReportMapper.addReport(reportRequsetDTO, userId);
    }

    /**
     * 신고 처리 및 상태 수정
     */
    @Override
    public void updateStatus(Long id, String status, Long adminId) throws Exception {
        ContentsReport contentsReport = contentsReportMapper.findContentsReportById(id);
        Long contentsId = contentsReport.getContents().getId();
        String contentsTitle = contentsReport.getContents().getTitle();

        // 신고 대상자 아이디 가져오기
        Long targetUserId = contentsService.findContentsById(contentsId).getUserId();

        // 신고 처리 메시지 전송
        if(status.equals("WARN")) {
            notificationService.createNotification(adminId, targetUserId, "",
                    contentsTitle, MessageType.WARN);
        } else {
            notificationService.createNotification(adminId, targetUserId, "",
                    contentsTitle, MessageType.DELETE);
        }

        contentsReportMapper.updateStatus(id, status);
    }
}