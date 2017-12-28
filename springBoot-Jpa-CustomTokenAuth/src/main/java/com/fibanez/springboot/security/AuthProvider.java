package com.fibanez.springboot.security;

import com.fibanez.springboot.dao.NetworkTokenAuthDao;
import com.fibanez.springboot.domain.User;
import com.fibanez.springboot.domain.UserRole;
import com.fibanez.springboot.domain.dto.Account;
import com.fibanez.springboot.domain.dto.NetworkTokenAuth;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class AuthProvider implements AuthenticationProvider {

    @Autowired
    NetworkTokenAuthDao networkDao;

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {

        final AuthenticationToken tokenContainer = (AuthenticationToken) authentication;
        final Optional<String> token = tokenContainer.getToken();

        if (!token.isPresent()) {
            throw new BadCredentialsException("Invalid token - " + token);
        }

        Optional<NetworkTokenAuth> network = networkDao.findByToken(token.get());
        if (!network.isPresent()) {
            throw new BadCredentialsException("Invalid token - " + token);
        }

        Account account = network.get().getAccount();

        return new AuthenticationToken(token, new User("user","", UserRole.USER), account);
    }

    @Override
    public boolean supports(Class<?> authentication) {
        //this class is only responsible for AuthTokenContainers
        return AuthenticationToken.class.isAssignableFrom(authentication);
    }

}
