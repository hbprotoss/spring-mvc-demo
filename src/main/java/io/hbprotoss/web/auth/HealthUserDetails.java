package io.hbprotoss.web.auth;

import org.springframework.security.core.userdetails.UserDetails;

import java.util.List;

/**
 * Created by hbprotoss on 2/3/16.
 */
public class HealthUserDetails implements UserDetails {
    private List<HealthUserRole> authorities;
    private String password;
    private String username;

    public List<HealthUserRole> getAuthorities() {
        return authorities;
    }

    public String getPassword() {
        return password;
    }

    public String getUsername() {
        return username;
    }

    public boolean isAccountNonExpired() {
        // account never expire for now
        return true;
    }

    public boolean isAccountNonLocked() {
        // account never locked for now
        return true;
    }

    public boolean isCredentialsNonExpired() {
        // password never expire for now
        return true;
    }

    public boolean isEnabled() {
        // user always enabled for now
        return true;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setAuthorities(List<HealthUserRole> authorities) {
        this.authorities = authorities;
    }

}
