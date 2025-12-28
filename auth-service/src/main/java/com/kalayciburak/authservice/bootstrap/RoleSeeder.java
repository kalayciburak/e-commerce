package com.kalayciburak.authservice.bootstrap;

import com.kalayciburak.authservice.service.RoleService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class RoleSeeder implements InitializingBean {
    private final RoleService service;

    @Override
    public void afterPropertiesSet() {
        service.seedRolesIfEmpty();
    }
}
