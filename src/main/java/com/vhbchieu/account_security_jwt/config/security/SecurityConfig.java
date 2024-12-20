package com.vhbchieu.account_security_jwt.config.security;

import com.vhbchieu.account_security_jwt.sys.domain.dto.AccountAuthDto;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig {

    private final String[] PUBLIC_ENDPOINT = {
            "/auth/**",
    };

    private final JWTAuthenticationFilter jwtAuthenticationFilter;
    private final CustomEntryPoint customEntryPoint;

    //
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .authorizeHttpRequests(authRequest -> authRequest
                        .requestMatchers(PUBLIC_ENDPOINT).permitAll()
                        .requestMatchers("/user/info").hasRole("USER")
                        .requestMatchers("/user/**").hasRole("ADMIN")
                        .anyRequest().authenticated())
                .addFilterBefore(jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class)   //add custom filter
                .exceptionHandling(exHandler -> exHandler
                        .authenticationEntryPoint(customEntryPoint))
        ;
        //
        http.csrf().disable();
        return http.build();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    //Hủy tạo 1 người dùng mặc định của Security 6
    @Bean
    public UserDetailsService userDetailsService() {
        return new InMemoryUserDetailsManager();
    }

}
