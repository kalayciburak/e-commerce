package com.kalayciburak.commonjpapackage.model.response;

import com.kalayciburak.commonjpapackage.model.success.BaseSuccess;
import com.kalayciburak.commonjpapackage.util.constant.Messages;
import org.springframework.data.domain.Page;

import java.util.List;
import java.util.Set;

import static org.springframework.http.HttpStatus.*;

public class BaseResponse {
    public static <T> BaseSuccess<T> createSuccessResponse(T data, String message) {
        var code = determineSuccessStatusCode(message);
        int size = getSize(data);

        return new BaseSuccess<>(code, message, size, data);
    }

    public static <T> BaseSuccess<T> createNotFoundResponse(String message) {
        return new BaseSuccess<>(NOT_FOUND.toString(), message, 0, null);
    }

    private static String determineSuccessStatusCode(String message) {
        return (message.equals(Messages.Entity.SAVED) || message.equals(Messages.Entities.SAVED))
                ? CREATED.toString()
                : OK.toString();
    }

    private static int getSize(Object data) {
        return switch (data) {
            case List<?> objects -> objects.size();
            case Page<?> objects -> objects.getSize();
            case Set<?> objects -> objects.size();
            case null, default -> 1;
        };
    }
}
