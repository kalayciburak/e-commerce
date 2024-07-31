package com.kalayciburak.commonjpapackage.model.success;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class BaseSuccess<T> {
    @JsonFormat(pattern = "dd-MM-yyyy HH:mm:ss")
    private LocalDateTime timestamp;
    private String type;
    private String code;
    private Object message;
    private boolean success;
    private int size;
    private T data;

    public BaseSuccess(String code, Object message, int size, T data) {
        this.timestamp = LocalDateTime.now();
        this.type = "SUCCESS";
        this.code = code;
        this.message = message;
        this.success = true;
        this.size = size;
        this.data = data;
    }
}