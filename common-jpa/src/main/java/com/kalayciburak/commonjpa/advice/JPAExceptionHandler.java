package com.kalayciburak.commonjpa.advice;

import com.kalayciburak.commonpackage.core.advice.BaseExceptionHandler;
import com.kalayciburak.commonpackage.core.constant.Codes;
import com.kalayciburak.commonpackage.core.constant.Messages;
import com.kalayciburak.commonpackage.core.constant.Types;
import com.kalayciburak.commonpackage.core.response.error.ErrorResponse;
import jakarta.persistence.EntityExistsException;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class JPAExceptionHandler extends BaseExceptionHandler {
    /**
     * {@code EntityExistsException} istisnasını yakalar ve bu tür istisnalar için özel işleme sağlar.
     * <p>
     * Bu istisna genellikle bir varlık zaten veritabanında mevcutken eklenmeye çalışıldığında fırlatılır. İlgili hata
     * bilgisi, çakışan veri nedeniyle işlemin başarısız olduğunu kullanıcıya bildirir.
     *
     * @param exception yakalanacak istisna.
     * @return Çakışan varlık bilgilerini içeren {@link ResponseEntity}.
     */
    @ExceptionHandler(EntityExistsException.class)
    public ResponseEntity<ErrorResponse<?>> handleEntityExistsException(EntityExistsException exception) {
        var error = new ErrorResponse<>(
                Types.Exception.ENTITY_EXISTS,
                Codes.ENTITY_EXISTS,
                Messages.Error.ENTITY_EXISTS,
                HttpStatus.CONFLICT,
                exception);

        return buildResponseEntity(error);
    }

    /**
     * JPA {@code EntityNotFoundException} istisnasını yakalar ve bu türdeki hataları işler.
     * <p>
     * Bu istisna, istenen bir veri kaynağı veya varlık bulunamadığında fırlatılır. Hata mesajı, kullanıcıya aranan verinin
     * mevcut olmadığını açıklar.
     *
     * @param exception yakalanacak istisna.
     * @return Bulunamayan varlık bilgilerini içeren {@link ResponseEntity}.
     */
    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity<ErrorResponse<?>> handleEntityNotFoundException(EntityNotFoundException exception) {
        var error = new ErrorResponse<>(
                Types.Exception.ENTITY_NOT_FOUND,
                Codes.ENTITY_NOT_FOUND,
                Messages.Error.ENTITY_NOT_FOUND,
                HttpStatus.NOT_FOUND,
                exception);

        return buildResponseEntity(error);
    }
}
