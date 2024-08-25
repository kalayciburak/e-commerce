package com.kalayciburak.commonpackage.util.response;

import com.kalayciburak.commonpackage.model.Sizeable;
import com.kalayciburak.commonpackage.model.success.BaseSuccess;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;

import java.util.Collection;
import java.util.List;
import java.util.Set;

import static com.kalayciburak.commonpackage.util.constant.Keywords.creationKeywords;
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
        return creationKeywords.stream()
                .map(String::toLowerCase)
                .anyMatch(message.toLowerCase()::contains)
                ? HttpStatus.CREATED.toString()
                : HttpStatus.OK.toString();
    }

    private static int getSize(Object data) {
        return switch (data) {
            case List<?> list -> list.size();
            case Page<?> page -> page.getSize();
            case Set<?> set -> set.size();
            case Collection<?> collection -> collection.size();
            case Object[] array -> array.length;
            case Sizeable sizeable -> sizeable.size();
            case null, default -> 1;
        };
    }
}