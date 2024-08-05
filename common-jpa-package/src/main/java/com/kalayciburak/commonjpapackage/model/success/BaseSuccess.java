package com.kalayciburak.commonjpapackage.model.success;

import com.kalayciburak.commonjpapackage.model.response.BaseResponse;
import com.kalayciburak.commonjpapackage.util.constant.Types;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class BaseSuccess<T> extends BaseResponse {
    private int size;
    private T data;

    public BaseSuccess(String code, String message, int size, T data) {
        super(Types.Response.SUCCESS, code, message, true);
        this.size = size;
        this.data = data;
    }
}