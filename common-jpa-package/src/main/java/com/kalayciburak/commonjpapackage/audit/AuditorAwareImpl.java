package com.kalayciburak.commonjpapackage.audit;

import org.springframework.data.domain.AuditorAware;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class AuditorAwareImpl implements AuditorAware<String> {
    @Override
    public Optional<String> getCurrentAuditor() {
        // TODO: Security eklendikten sonra gerçek bir kullanıcı dönecek
        return Optional.of("kalayciburak");
    }
}