package com.sysone.ddogdog.common.config.mail.service;

import com.sysone.ddogdog.common.config.mail.model.RequestEmailContentDTO;
import jakarta.mail.internet.MimeMessage;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Slf4j
@Transactional(readOnly = true)
public class EmailService {
    private final JavaMailSender javaMailSender;
    @Async
    public void sendEmailNotice(RequestEmailContentDTO dto){
        MimeMessage mimeMessage = javaMailSender.createMimeMessage();
        try {
            MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(mimeMessage, false, "UTF-8");
            mimeMessageHelper.setTo(dto.getEmail()); // 메일 수신자
            mimeMessageHelper.setSubject("Today's Overview on NESS"); // 메일 제목
            mimeMessageHelper.setText(buildEmailBody(dto), true); // 메일 본문 내용, HTML 여부
            javaMailSender.send(mimeMessage);

            log.info("Succeeded to send Email");
        } catch (Exception e) {
            log.info("Failed to send Email");
            throw new RuntimeException(e);
        }
    }

    public String todayDate(){
        ZonedDateTime todayDate = LocalDateTime.now(ZoneId.of("Asia/Seoul")).atZone(ZoneId.of("Asia/Seoul"));
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("M월 d일");
        return todayDate.format(formatter);
    }

    private String buildEmailBody(RequestEmailContentDTO dto) {
        // 메일 본문을 DTO에서 가져온 값으로 구성
        StringBuilder body = new StringBuilder();
        body.append("<h1>").append(dto.getUserName()).append("님 예약 확인 메일입니다").append("</h1>");
        body.append("<p>").append("예약 일자 : " + dto.getCreateDate()).append("</p>");
        body.append("<h1>").append("예약 기간 : " + dto.getStartDate() + " - " + dto.getEndDate()).append("</h1>");
        body.append("<p>").append("방 개수 : " + dto.getCount()).append("</p>");
        body.append("<p>").append("총 금액 : " + dto.getPrice()).append("</p>");
        return body.toString();
    }

}

