package com.kalayciburak.commonjpapackage.model.error;

import com.kalayciburak.commonjpapackage.model.response.BaseResponse;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class BaseError<T> extends BaseResponse {
    private String className;
    private String methodName;
    private int lineNumber;
    private String debugMessage;

    public BaseError(String type, String code, T message, Throwable cause) {
        super(type, code, message, false);
        this.className = cause.getStackTrace()[0].getClassName();
        this.methodName = cause.getStackTrace()[0].getMethodName();
        this.lineNumber = cause.getStackTrace()[0].getLineNumber();
        this.debugMessage = cause.getLocalizedMessage();
    }
}