package com.kalayciburak.apigateway.api.controller;

import com.kalayciburak.apigateway.response.ErrorResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

import java.time.LocalDateTime;

@RestController
public class FallbackController {
    /**
     * Servis kullanılamadığında çağrılan geri dönüş (fallback) endpoint'i.
     *
     * <p>Bu endpoint, servis geçici olarak kullanılamaz olduğunda çağrılır ve bir hata yanıtı döner. Yanıt,
     * "Service Unavailable" HTTP durumu ve kullanıcıya bilgi veren bir mesaj içerir.
     *
     * @return {@link ResponseEntity} ile hata mesajını ve HTTP 503 durum kodunu döner.
     */
    @RequestMapping(value = "/fallback", method = RequestMethod.GET, produces = "application/json")
    public Mono<ErrorResponse> fallback() {
        var errorResponse = new ErrorResponse(
                HttpStatus.SERVICE_UNAVAILABLE.value(),
                LocalDateTime.now(),
                "Service is temporarily unavailable. Please try again later."
        );

        return Mono.just(errorResponse);
    }
}
