package com.phonixlab.todolist.config;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@RequiredArgsConstructor
public class JwtTokenFilter extends OncePerRequestFilter {

    private final JwtTokenProvider tokenProvider;


    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {
//        try {
//            String jwt = extractJwtFromRequest(request);
//
//            if (jwt != null && tokenProvider.validateToken(jwt)) {
//                Authentication auth = tokenProvider.getAuthentication(jwt);
//                SecurityContextHolder.getContext().setAuthentication(auth);
//            }
//        } catch (Exception e) {
//
//        }

        filterChain.doFilter(request, response);
    }

    private String extractJwtFromRequest(HttpServletRequest request) {
        return null;
    }
}
