package com.ssafy.wpwk.service;

import com.ssafy.wpwk.model.User;
import com.ssafy.wpwk.utils.MailUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.mail.MessagingException;
import java.io.UnsupportedEncodingException;

@Service
public class MailService {

    @Autowired
    private JavaMailSender mailSender;

    @Async
    @Transactional
    public void sendAuthMail(User user, String authKey) throws MessagingException, UnsupportedEncodingException {
        // mail 작성 관련
        MailUtil sendMail = new MailUtil(mailSender);

        sendMail.setSubject("[위파위키] 회원가입 이메일 인증");
        sendMail.setText(new StringBuffer().append("<h1>[이메일 인증]</h1>")
                .append("<p>아래 링크를 클릭하시면 이메일 인증이 완료됩니다.</p>")
                .append("<a href='http://localhost:8080/users/confirm?uid=")
                .append(user.getId())
                .append("&verificationKey=")
                .append(authKey)
                .append("' target='_blank'>이메일 인증 확인</a>")
                .toString());
        sendMail.setFrom("doingnow94@gmail.com ", "위파위키");
        sendMail.setTo(user.getEmail());
        sendMail.send();
    }

    @Async
    @Transactional
    public void sendResetPasswordMail(User user) throws MessagingException, UnsupportedEncodingException {
        MailUtil sendMail = new MailUtil(mailSender);

        sendMail.setSubject("[위파위키]회원의 비밀번호 찾기 인증");
        sendMail.setText(new StringBuffer().append("<h1>[회원 인증을 완료해주세요]<h1>")
                .append("<p>아래 링크를 클릭하시면 회원 인증이 완료가 됩니다.<p>")
                .append("<a href='http://localhost:8080/users/confirm?uid=")
                .append(user.getId())
                .append("&verificationKey=")
                .append(user.getVerificationKey())
                .append("' target='_blank'>회원 인증 확인</a>")
                .toString());
        sendMail.setFrom("doingnow94@gmail.com ", "위파위키");
        sendMail.setTo(user.getEmail());
        sendMail.send();
    }
}