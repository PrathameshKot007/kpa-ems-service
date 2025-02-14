package com.kpa.ems.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.kpa.ems.util.JwtAuthenticationFilter;

@Configuration
public class SecurityConfiguration {
	
	@Autowired
    private JwtAuthenticationFilter jwtAuthenticationFilter; // The filter that extracts the JWT token
	
	@Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
            .csrf(csrf -> csrf.disable()) // Disable CSRF for development (enable in production)
            .authorizeHttpRequests(auth -> auth
            	.requestMatchers("/ems/login").permitAll()
                .requestMatchers("/ems/signup").permitAll() // Allow signup API
                .requestMatchers("/event/getAll").permitAll()
                .requestMatchers("/event/addEvent").authenticated()
                .requestMatchers("/booking/**").authenticated()
                .requestMatchers("/booking/my-bookings").authenticated()
                .anyRequest().authenticated() // All other APIs require authentication
            )
            .sessionManagement(sess -> sess.sessionCreationPolicy(SessionCreationPolicy.STATELESS));
        http.addFilterBefore(jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class);
        return http.build();
    }
	
	@Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration config) throws Exception {
        return config.getAuthenticationManager();
    }

	
	@Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder(); // Ensure passwords are securely hashed
    }
}
