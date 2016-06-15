package io.hbprotoss.web.auth;

import org.springframework.security.core.GrantedAuthority;

/**
 * Created by hbprotoss on 2/3/16.
 */
public class HealthUserRole implements GrantedAuthority {
    public String getAuthority() {
        return null;
    }
}
