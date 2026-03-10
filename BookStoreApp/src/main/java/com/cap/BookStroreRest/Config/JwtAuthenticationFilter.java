package com.cap.BookStroreRest.Config;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
public class JwtAuthenticationFilter extends OncePerRequestFilter {

    // Utility class for JWT operations (generate, validate, extract claims)
    @Autowired
    private JwtUtil jwtUtil;

    // Spring Security service to load user details from database
    @Autowired
    private UserDetailsService userDetailsService;

    /**
     * Main filter method - called for every HTTP request
     *
     * @param request - HTTP request containing headers (including Authorization)
     * @param response - HTTP response (not modified in this filter)
     * @param filterChain - Chain of filters to continue processing
     */
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {

        // Step 1: Extract Authorization header from request
        // Expected format: "Authorization: Bearer eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9..."
        final String authorizationHeader = request.getHeader("Authorization");

        // Initialize variables to store extracted data
        String email = null;  // User email extracted from JWT
        String jwt = null;    // JWT token without "Bearer " prefix

        // Step 2: Check if Authorization header exists and starts with "Bearer "
        if (authorizationHeader != null && authorizationHeader.startsWith("Bearer ")) {
            // Extract JWT token by removing "Bearer " prefix (first 7 characters)
            jwt = authorizationHeader.substring(7);

            // Extract user email from JWT token using JwtUtil
            email = jwtUtil.extractEmail(jwt);
        }

        // Step 3: Validate token and set authentication if valid
        // Check: email exists AND no authentication is already set in SecurityContext
        if (email != null && SecurityContextHolder.getContext().getAuthentication() == null) {

            // Load user details from database using email
            UserDetails userDetails = this.userDetailsService.loadUserByUsername(email);

            // Validate JWT token (check signature, expiration, and email match)
            if (jwtUtil.validateToken(jwt, email)) {

                // Create authentication token for Spring Security
                // Parameters: principal (user), credentials (null for JWT), authorities (user roles)
                UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(
                        userDetails, null, userDetails.getAuthorities());

                // Set additional details (IP address, session ID, etc.)
                authToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));

                // Set authentication in SecurityContext - this tells Spring Security user is authenticated
                SecurityContextHolder.getContext().setAuthentication(authToken);
            }
        }

        // Step 4: Continue filter chain (proceed to next filter or controller)
        // This happens regardless of authentication success/failure
        filterChain.doFilter(request, response);
    }
}