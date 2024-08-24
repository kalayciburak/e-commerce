package com.kalayciburak.commonjpapackage.advice;

import com.kalayciburak.commonjpapackage.model.error.BaseError;
import com.kalayciburak.commonjpapackage.util.constant.Codes;
import com.kalayciburak.commonjpapackage.util.constant.Messages;
import com.kalayciburak.commonjpapackage.util.constant.Profiles;
import com.kalayciburak.commonjpapackage.util.constant.Types;
import jakarta.persistence.EntityExistsException;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;

@RestControllerAdvice
public class BaseExceptionHandler {
    @Value("${spring.profiles.active:default}")
    private String activeProfile;

    /**
     * <b>Genel istisnalar için işleyici tanımlar.</b>
     * <p>
     * Bu işleyici, {@code Exception} ve {@code RuntimeException} türündeki istisnaları ve alt türlerini yakalar.
     * İşlenmemiş genel hataları ele alarak sistem genelinde tutarlı bir hata yönetimi stratejisi sağlar.
     * Bu yöntem, uygulamanın diğer bölümlerinde ayrı ayrı hata yönetimi gereksinimini azaltır.
     *
     * @param exception yakalanacak istisna.
     * @return Oluşturulan hata ile ilgili bilgileri içeren {@link ResponseEntity}.
     */
    @ExceptionHandler({Exception.class, RuntimeException.class})
    public ResponseEntity<BaseError<?>> handleGenericException(Exception exception) {
        return buildResponseEntity(new BaseError<>(exception));
    }

    /**
     * {@code IllegalArgumentException} türündeki istisnalar için özel bir işleyici tanımlar.
     * <p>
     * Bu tür istisnalar, metodlara geçersiz argümanlar gönderildiğinde ortaya çıkar.
     * İstisna bilgileri, kullanıcıya geçersiz girişin neden olduğu hata hakkında bilgi vermek için kullanılır.
     *
     * @param exception yakalanacak istisna.
     * @return Oluşturulan hata ile ilgili bilgileri içeren {@link ResponseEntity}.
     */
    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<BaseError<?>> handleIllegalArgumentException(IllegalArgumentException exception) {
        var error = new BaseError<>(
                Types.Exception.ILLEGAL_ARGUMENT,
                Codes.ILLEGAL_ARGUMENT,
                Messages.Error.INVALID_ARGUMENT,
                HttpStatus.BAD_REQUEST,
                exception);

        return buildResponseEntity(error);
    }

    /**
     * {@code MethodArgumentNotValidException} istisnasını yakalar ve bu türdeki doğrulama hatalarını işler.
     * <p>
     * Bu işleyici, genellikle Spring MVC'nin otomatik doğrulama mekanizmaları tarafından fırlatılır ve
     * kullanıcıya yapılan girişlerin neden geçersiz olduğuna dair ayrıntılı bilgiler sağlar.
     *
     * @param exception yakalanacak istisna.
     * @return Doğrulama hatalarını içeren {@link ResponseEntity}.
     */
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<BaseError<?>> handleMethodArgumentNotValidException(MethodArgumentNotValidException exception) {
        var error = new BaseError<>(
                Types.Exception.VALIDATION,
                Codes.METHOD_ARGUMENT_NOT_VALID,
                Messages.Error.VALIDATION_ERROR,
                HttpStatus.BAD_REQUEST,
                exception);
        populateValidationErrors(exception, error);

        return buildResponseEntity(error);
    }

    /**
     * {@code EntityExistsException} istisnasını yakalar ve bu tür istisnalar için özel işleme sağlar.
     * <p>
     * Bu istisna genellikle bir varlık zaten veritabanında mevcutken eklenmeye çalışıldığında fırlatılır.
     * İlgili hata bilgisi, çakışan veri nedeniyle işlemin başarısız olduğunu kullanıcıya bildirir.
     *
     * @param exception yakalanacak istisna.
     * @return Çakışan varlık bilgilerini içeren {@link ResponseEntity}.
     */
    @ExceptionHandler(EntityExistsException.class)
    public ResponseEntity<BaseError<?>> handleEntityExistsException(EntityExistsException exception) {
        var error = new BaseError<>(
                Types.Exception.ENTITY_EXISTS,
                Codes.ENTITY_EXISTS,
                Messages.Error.ENTITY_EXISTS,
                HttpStatus.CONFLICT,
                exception);

        return buildResponseEntity(error);
    }

    /**
     * {@code EntityNotFoundException} istisnasını yakalar ve bu türdeki hataları işler.
     * <p>
     * Bu istisna, istenen bir veri kaynağı veya varlık bulunamadığında fırlatılır.
     * Hata mesajı, kullanıcıya aranan verinin mevcut olmadığını açıklar.
     *
     * @param exception yakalanacak istisna.
     * @return Bulunamayan varlık bilgilerini içeren {@link ResponseEntity}.
     */
    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity<BaseError<?>> handleEntityNotFoundException(EntityNotFoundException exception) {
        var error = new BaseError<>(
                Types.Exception.ENTITY_NOT_FOUND,
                Codes.ENTITY_NOT_FOUND,
                Messages.Error.ENTITY_NOT_FOUND,
                HttpStatus.NOT_FOUND,
                exception);

        return buildResponseEntity(error);
    }

    /**
     * <b>Hata nesnesini {@code ResponseEntity} olarak oluşturur.</b>
     *
     * @param error oluşturulacak {@link BaseError} nesnesi.
     * @return Hata durumuna uygun {@link ResponseEntity} döner.
     */
    private ResponseEntity<BaseError<?>> buildResponseEntity(BaseError<?> error) {
        if (isProdLikeProfile()) return new ResponseEntity<>(new BaseError<>(error), error.getStatus());

        return new ResponseEntity<>(error, error.getStatus());
    }

    /**
     * <b>Validation hatalarını {@code BaseError} nesnesine ekler.</b>
     *
     * @param exception içerisinde validasyon hataları barındıran istisna.
     * @param error     hataların ekleneceği {@link BaseError} nesnesi.
     */
    private static void populateValidationErrors(MethodArgumentNotValidException exception, BaseError<String> error) {
        var validationErrors = new HashMap<>();
        for (FieldError fieldError : exception.getBindingResult().getFieldErrors()) {
            validationErrors.put(fieldError.getField(), fieldError.getDefaultMessage());
        }

        error.getDetail().setDebugMessage(Messages.Error.VALIDATION_ERROR);
        error.setMessage(validationErrors);
    }

    /**
     * <b>Aktif profilin prod benzeri bir profil olup olmadığını kontrol eder.</b>
     *
     * @return Aktif profil prod benzeri bir profil ise {@code true} döner.
     */
    private boolean isProdLikeProfile() {
        return Profiles.PRODUCTION.contains(activeProfile);
    }
}
