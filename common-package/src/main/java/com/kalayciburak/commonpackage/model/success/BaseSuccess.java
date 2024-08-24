package com.kalayciburak.commonpackage.model.success;

import com.kalayciburak.commonpackage.model.response.BaseResponse;
import lombok.Getter;
import lombok.Setter;

import static com.kalayciburak.commonpackage.util.constant.Types.Response.SUCCESS;

@Getter
@Setter
public class BaseSuccess<T> extends BaseResponse {
    private int size;
    private T data;

    public BaseSuccess(String code, String message, int size, T data) {
        super(SUCCESS, code, message, true);
        this.size = size;
        this.data = data;
    }
}