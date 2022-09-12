package com.ll.exam.app_2022_09_12__1.app.email.service;


import com.ll.exam.app_2022_09_12__1.app.base.AppConfig;
import com.ll.exam.app_2022_09_12__1.app.base.dto.RsData;
import com.ll.exam.app_2022_09_12__1.app.email.entity.SendEmailLog;
import com.ll.exam.app_2022_09_12__1.app.email.repository.SendEmailLogRepository;
import com.ll.exam.app_2022_09_12__1.app.sendEmail.exception.SendEmailException;
import com.ll.exam.app_2022_09_12__1.app.sendEmail.service.SendEmailService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class EmailService {
    private final SendEmailLogRepository emailLogRepository;
    private final SendEmailService sendEmailService;

    @Transactional
    public RsData<Long> sendEmail(String email, String title, String body) {
        SendEmailLog sendEmailLog = emailLogRepository.save(
                SendEmailLog
                        .builder()
                        .email(email)
                        .title(title)
                        .body(body)
                        .build()
        );

        RsData trySendRsData = trySend(email, title, body);

        setCompleted(sendEmailLog, trySendRsData);

        return RsData.of("S-1", "메일이 발송되었습니다.", sendEmailLog.getId());
    }

    private RsData trySend(String email, String title, String body) {
        if (AppConfig.isNotProd()) {
            return RsData.of("S-0", "메일이 발송되었습니다.");
        }

        try {
            sendEmailService.send(email, title, body);

            return RsData.of("S-1", "메일이 발송되었습니다.");
        } catch (SendEmailException e) {
            return RsData.of("F-1", e.getMessage());
        }
    }

    private void setCompleted(SendEmailLog sendEmailLog, RsData rsData) {
        if (rsData.isSuccess()) {
            sendEmailLog.setSendEndDate(LocalDateTime.now());
        } else {
            sendEmailLog.setFailDate(LocalDateTime.now());
        }

        sendEmailLog.setResultCode(rsData.getResultCode());
        sendEmailLog.setMessage(rsData.getMessage());
    }
}
