package com.ll.exam.app_2022_09_12__1.app.security.exception;

public class OAuthTypeMatchNotFoundException extends RuntimeException {
    public OAuthTypeMatchNotFoundException() {
    }

    public OAuthTypeMatchNotFoundException(String message) {
        super(message);
    }

    public OAuthTypeMatchNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }

    public OAuthTypeMatchNotFoundException(Throwable cause) {
        super(cause);
    }

    public OAuthTypeMatchNotFoundException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
