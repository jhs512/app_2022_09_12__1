package com.ll.exam.app_2022_09_12__1.app.sendEmail.exception;

import java.io.IOException;

public class SendEmailException extends RuntimeException {
    public SendEmailException(IOException ex) {
        super(ex);
    }
}
