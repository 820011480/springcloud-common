package com.mady.common.gateway.config;

import lombok.Data;
import org.springframework.security.authentication.AbstractAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;

import java.util.ArrayList;
import java.util.Collection;

/**
 * @author mady
 * @version 1.0.0
 * @date 2020/7/15 15:25
 * @description
 */
@Data
public class AdminAuthenticationToken extends AbstractAuthenticationToken{

    private static final long serialVersionUID = -3625665688626567368L;

    private Collection<GrantedAuthority> authorities = new ArrayList<>();
    private Object principal;
    private Object password;
    private String username;



    public AdminAuthenticationToken(String username, String password) {
        super(null);
        this.username = username;
        this.password = password;
    }


    @Override
    public Object getCredentials() {
        return this.password;
    }

    @Override
    public Object getPrincipal() {
        return null;
    }

    public void setPrincipal(Object principal) {
        this.principal = principal;
    }

    @Override
    public Collection<GrantedAuthority> getAuthorities() {
        return this.authorities;
    }

    public void setAuthorities(
            Collection<? extends GrantedAuthority> authorities) {
        this.authorities.addAll(authorities);
    }

}
