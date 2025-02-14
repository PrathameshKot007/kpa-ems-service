package com.kpa.ems.util;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
public class JwtAuthenticationFilter extends OncePerRequestFilter{
	 @Autowired
	    private JwtUtil jwtUtil;

	    @Autowired
	    private UserDetailsService userDetailsService;

	    @Override
	    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
	            throws ServletException, IOException {

	        String token = extractTokenFromHeader(request);
	        System.out.println("Extracted Token: "+token);

	        if (token != null && jwtUtil.validateToken(token,jwtUtil.extractUsername(token))) {
	            String username = jwtUtil.extractUsername(token);

	            // Load the user details from the database
	            UserDetails userDetails = userDetailsService.loadUserByUsername(username);

	            // Create the authentication object
	            UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(
	                    userDetails, null, userDetails.getAuthorities());

	            // Set the authentication in the SecurityContext
	            SecurityContextHolder.getContext().setAuthentication(authentication);
	        }

	        filterChain.doFilter(request, response);
	    }

	    private String extractTokenFromHeader(HttpServletRequest request) {
	        String header = request.getHeader("Authorization");
	        if (header != null && header.startsWith("Bearer ")) {
	            return header.substring(7);
	        }
	        return null;
	    }
	    
}
