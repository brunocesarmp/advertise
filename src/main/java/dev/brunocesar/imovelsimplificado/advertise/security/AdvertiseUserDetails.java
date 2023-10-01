package dev.brunocesar.imovelsimplificado.advertise.security;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;

public class AdvertiseUserDetails implements UserDetails {

    private String uuid;
    private String email;
    private String password;
    private Collection<? extends GrantedAuthority> authorities;

    public AdvertiseUserDetails() {
    }

    public AdvertiseUserDetails(String uuid, String email, String password, Collection<? extends GrantedAuthority> authorities) {
        super();
        this.uuid = uuid;
        this.email = email;
        this.password = password;
        this.authorities = authorities;
    }

    public String getUuid() {
        return uuid;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return email;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
