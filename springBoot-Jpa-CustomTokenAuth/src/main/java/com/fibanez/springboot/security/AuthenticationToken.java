package com.fibanez.springboot.security;

import com.fibanez.springboot.domain.User;
import com.fibanez.springboot.domain.dto.Account;
import org.springframework.security.authentication.AbstractAuthenticationToken;

import java.util.Collections;
import java.util.Optional;

public class AuthenticationToken extends AbstractAuthenticationToken {

    private final User user;
    private final Long accountId;
    private final Optional<String> token;

    public AuthenticationToken(Optional<String> token){
        super(Collections.emptyList());
        this.token = token;
        this.user = null;
        this.accountId = null;
        setAuthenticated(false);
    }

    public AuthenticationToken(Optional<String> token, User user, Account account) {
        //note that the constructor needs a collection of GrantedAuthority
        //but our User have a collection of our UserRole's
        super(user.getRoles());

        this.token = token;
        this.user = user;
        this.accountId = account.getId();
        setAuthenticated(true);
    }

    @Override
    public Object getCredentials() {
        return getToken();
    }

    @Override
    public Object getPrincipal() {
        return getAccountId();
    }

    public Optional<String> getToken() {
        return token;
    }

    public User getUser() {
        return user;
    }

    public Long getAccountId() {
        return accountId;
    }
}