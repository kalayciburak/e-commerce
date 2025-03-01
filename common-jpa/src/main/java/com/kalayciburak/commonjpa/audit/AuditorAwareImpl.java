package com.kalayciburak.commonjpa.audit;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.AuditorAware;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
@RequiredArgsConstructor
public class AuditorAwareImpl implements AuditorAware<String> {
    private final AuditorProvider auditorProvider;

    @Override
    public Optional<String> getCurrentAuditor() {
        return Optional.ofNullable(auditorProvider.getCurrentAuditor());
    }
}