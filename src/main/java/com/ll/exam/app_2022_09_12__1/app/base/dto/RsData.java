package com.ll.exam.app_2022_09_12__1.app.base.dto;

import lombok.Getter;
import lombok.Setter;

public class RsData<T> {
    @Getter
    @Setter
    private String resultCode;
    @Getter
    @Setter
    private String message;

    @Getter
    @Setter
    private T body;

    public boolean isSuccess() {
        return resultCode.startsWith("S-");
    }

    private RsData(String resultCode, String message) {
        this.resultCode = resultCode;
        this.message = message;
    }

    private RsData(String resultCode, String message, T body) {
        this(resultCode, message);
        this.body = body;
    }

    public static RsData of(String resultCode, String message) {
        return new RsData(resultCode, message);
    }

    public static <T> RsData of(String resultCode, String message, T body) {
        return new RsData(resultCode, message, body);
    }
}
