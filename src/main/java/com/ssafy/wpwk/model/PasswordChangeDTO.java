package com.ssafy.wpwk.model;

import lombok.Data;

@Data
public class PasswordChangeDTO {
    
    private String curPassword; // 현재 비밀번호
    
    private String newPassword; // 변경할 비밀번호
}
