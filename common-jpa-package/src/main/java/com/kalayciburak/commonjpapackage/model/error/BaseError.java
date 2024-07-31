package com.kalayciburak.commonjpapackage.model.error;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Setter
@Getter
public class BaseError<T> {
    @JsonFormat(pattern = "dd-MM-yyyy HH:mm:ss")
    private LocalDateTime timestamp;
    private String type;
    private String code;
    private T message;
    private boolean success;
    private String className;
    private String methodName;
    private int lineNumber;
    private String debugMessage;

    public BaseError(String type, String code, T message, Throwable cause) {
        this.timestamp = LocalDateTime.now();
        this.type = type;
        this.code = code;
        this.message = message;
        this.success = false;
        this.className = cause.getStackTrace()[0].getClassName();
        this.methodName = cause.getStackTrace()[0].getMethodName();
        this.lineNumber = cause.getStackTrace()[0].getLineNumber();
        this.debugMessage = cause.getLocalizedMessage();
    }
}