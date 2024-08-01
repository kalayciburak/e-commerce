package com.kalayciburak.commonjpapackage.util.response;

import com.kalayciburak.commonjpapackage.model.success.BaseSuccess;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;

import java.util.List;
import java.util.Set;

import static com.kalayciburak.commonjpapackage.util.constant.Keywords.creationKeywords;
import static org.springframework.http.HttpStatus.NOT_FOUND;

public class ResponseBuilder {
    public static <T> BaseSuccess<T> createSuccessResponse(T data, String message) {
        var code = determineSuccessStatusCode(message);
        int size = getSize(data);

        return new BaseSuccess<>(code, message, size, data);
    }

    public static <T> BaseSuccess<T> createSuccessResponse(String code, T data, String message) {
        int size = getSize(data);

        return new BaseSuccess<>(code, message, size, data);
    }

    public static <T> BaseSuccess<T> createNotFoundResponse(String message) {
        return new BaseSuccess<>(NOT_FOUND.toString(), message, 0, null);
    }

    private static String determineSuccessStatusCode(String message) {
        for (String keyword : creationKeywords) {
            if (message.toLowerCase().contains(keyword)) {
                return HttpStatus.CREATED.toString();
            }
        }

        return HttpStatus.OK.toString();
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
