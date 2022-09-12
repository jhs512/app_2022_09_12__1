package com.ll.exam.app_2022_09_12__1.app.sendEmail.service;

import com.ll.exam.app_2022_09_12__1.app.sendEmail.exception.SendEmailException;
import com.sendgrid.Method;
import com.sendgrid.Request;
import com.sendgrid.Response;
import com.sendgrid.SendGrid;
import com.sendgrid.helpers.mail.Mail;
import com.sendgrid.helpers.mail.objects.Content;
import com.sendgrid.helpers.mail.objects.Email;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
@Slf4j
public class SendEmailService {

    @Value("${custom.sendgrid.apiKey}")
    private String sendgridApiKey;
    @Value("${custom.sendgrid.fromEmail}")
    private String sendgridFromEmail;

    public void send(String toEmail, String subject, String body) {
        Email from = new Email(sendgridFromEmail);
        Email to = new Email(toEmail);
        Content content = new Content("text/html", body);
        Mail mail = new Mail(from, subject, to, content);

        SendGrid sg = new SendGrid(sendgridApiKey);
        Request request = new Request();
        try {
            request.setMethod(Method.POST);
            request.setEndpoint("mail/send");
            request.setBody(mail.build());
            Response response = sg.api(request);

            log.debug(response.getStatusCode() + "");
            log.debug(response.getBody());
            log.debug(response.getHeaders().toString());

        } catch (IOException ex) {
            throw new SendEmailException(ex);
        }
    }
}
