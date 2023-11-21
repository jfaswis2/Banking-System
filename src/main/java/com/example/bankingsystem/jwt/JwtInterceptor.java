package com.example.bankingsystem.jwt;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import java.util.Arrays;
import java.util.List;

@Component
public class JwtInterceptor implements HandlerInterceptor {

    private final JwtService jwtService;
    private final List<String> allowedPaths;

    public JwtInterceptor(JwtService jwtService) {
        this.jwtService = jwtService;
        this.allowedPaths = Arrays.asList("/auth/login", "/auth/register");
    }

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String requestURI = request.getRequestURI();
        if (allowedPaths.stream().anyMatch(requestURI::startsWith)) {
            return true;
        }

        String token = extractAuthToken(request.getHeader("Authorization"));
        String email = jwtService.getUsernameFromToken(token);
        request.setAttribute("email", email);
        return true;
    }

    public String extractAuthToken(String header) {
        if (header != null && header.startsWith("Bearer ")) {
            return header.substring(7);
        }
        throw new IllegalArgumentException("Invalid token format");
    }
}
