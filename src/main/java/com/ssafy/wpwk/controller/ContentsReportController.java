package com.ssafy.wpwk.controller;

import com.ssafy.wpwk.model.Contents;
import com.ssafy.wpwk.model.ContentsReport;
import com.ssafy.wpwk.model.ReportRequsetDTO;
import com.ssafy.wpwk.model.User;
import com.ssafy.wpwk.service.ContentsReportService;
import com.ssafy.wpwk.service.ContentsReportServiceImpl;
import com.ssafy.wpwk.service.UserService;
import com.ssafy.wpwk.service.UserServiceImpl;
import io.jsonwebtoken.Claims;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

import static com.ssafy.wpwk.utils.ExceptionUtil.isInValidAuthentication;

@CrossOrigin(origins = {"*"}, maxAge = 6000)
@RestController
public class ContentsReportController {


    @Autowired
    private ContentsReportServiceImpl contentsReportService;

    @Autowired
    private UserServiceImpl userService;

    @ApiOperation(value = "관리자에게 온 모든 신고 정보 조회")
    @GetMapping("/contentsReport")
    public ResponseEntity<?> allReports(Authentication authentication) {

        if (isInValidAuthentication(authentication)) {
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        }

        List<ContentsReport> contentsReportList;

        Claims claims = (Claims) authentication.getPrincipal();
        //관리자�� 체크
        Long id = claims.get("userId", Long.class);
        User admin = userService.findUserById(id);
        if (admin.getStatus() != 2) {
            return new ResponseEntity<>(HttpStatus.FORBIDDEN);
        }

        try {
            contentsReportList = contentsReportService.allReports();
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(contentsReportList, HttpStatus.OK);
    }

    @ApiOperation(value = "컨텐츠 신고 등록")
    @PostMapping("/contentsReport")
    public ResponseEntity<?> contentsReport(@RequestBody ReportRequsetDTO reportRequsetDTO, Authentication authentication) {
        if (isInValidAuthentication(authentication)) {
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        }
        try {
            Claims claims = (Claims) authentication.getPrincipal();
            Long userId = claims.get("userId", Long.class);
            contentsReportService.addReport(reportRequsetDTO, userId);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        return new ResponseEntity<>(HttpStatus.OK);
    }

    @ApiOperation(value = "컨텐츠 신고 처리")
    @PutMapping("/contentsReport")
    public ResponseEntity<?> contentsReportUpdate(@RequestBody Map<String, Object> map,
                                                  Authentication authentication) throws Exception {
        if (isInValidAuthentication(authentication)) {
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        }
        Claims claims = (Claims) authentication.getPrincipal();
        
        Long userId = claims.get("userId", Long.class);
        int userStatus = claims.get("status", Integer.class);
        
        // 관리자 여부 체크
        if (userStatus != 2) {
            return new ResponseEntity<>(HttpStatus.FORBIDDEN);
        }
        Long id = Long.parseLong(map.get("id").toString());
        String status = map.get("status").toString();

        contentsReportService.updateStatus(id, status, userId);

        return new ResponseEntity<>(HttpStatus.OK);
    }
}
