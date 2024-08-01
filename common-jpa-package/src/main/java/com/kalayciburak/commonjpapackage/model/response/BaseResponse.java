package com.kalayciburak.commonjpapackage.model.response;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public abstract class BaseResponse {
    @JsonFormat(pattern = "dd-MM-yyyy HH:mm:ss")
    protected LocalDateTime timestamp;
    protected String type;
    protected String code;
    protected Object message;
    protected boolean success;

    public BaseResponse(String type, String code, Object message, boolean success) {
        this.timestamp = LocalDateTime.now();
        this.type = type;
        this.code = code;
        this.message = message;
        this.success = success;
    }
}