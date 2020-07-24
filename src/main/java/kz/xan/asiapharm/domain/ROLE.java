package kz.xan.asiapharm.domain;

import org.springframework.security.core.GrantedAuthority;

public enum ROLE implements GrantedAuthority {
    ROLE_ADMIN, ROLE_CUSTOMER;

    @Override
    public String getAuthority() {
        return name();
    }
}
