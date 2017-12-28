package com.fibanez.springboot.security;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Optional;

public class TokenAuthFilter extends OncePerRequestFilter {

    private static final String TOKEN_HEADER = "Authorization";
    private static final String BEARER = "Bearer";

    @Override
    protected void doFilterInternal(HttpServletRequest request
            , HttpServletResponse response
            , FilterChain filterChain) throws ServletException, IOException {

        Optional<String> token = getTokenString(request.getHeader(TOKEN_HEADER));


        if (token.isPresent()) {
            AuthenticationToken auth = new AuthenticationToken(token);
            SecurityContextHolder.getContext().setAuthentication(auth);
        } else {
//            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
//            PrintWriter writer = response.getWriter();
//            writer.println("HTTP Status 401 - UNAUTHORIZED");
//            return;
        }

        filterChain.doFilter(request, response);
    }

    private Optional<String> getTokenString(String header) {
        if (header == null || !header.startsWith(BEARER)) {
            return Optional.empty();
        } else {
            return Optional.ofNullable(header.substring(BEARER.length() + 1));
        }
    }
}