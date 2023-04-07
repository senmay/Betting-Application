package com.dominik.typer.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {
    public static final String ADMIN = "ADMIN";
    public static final String USER = "USER";
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http.csrf().disable()
                .authorizeHttpRequests(authorize -> authorize
                        .requestMatchers(HttpMethod.POST,"/bet").hasAnyRole(USER,ADMIN)
                        .requestMatchers(HttpMethod.GET,"/bet").hasRole(USER)
                        .requestMatchers(HttpMethod.POST,"/match").hasRole(ADMIN)
                        .requestMatchers(HttpMethod.DELETE,"/match").hasRole(ADMIN)
                        .requestMatchers(HttpMethod.PUT,"/match").hasRole(ADMIN)
                        .requestMatchers(HttpMethod.GET,"/user").hasRole(ADMIN)
                        .requestMatchers("/user/register").permitAll()
                        .requestMatchers("/user/admin").hasRole(ADMIN)
                        .requestMatchers(HttpMethod.DELETE,"/user").hasRole(ADMIN)
                        .requestMatchers(HttpMethod.POST,"/team").hasRole(ADMIN)
                        .requestMatchers(HttpMethod.DELETE,"/team").hasRole(ADMIN)
                        .requestMatchers(HttpMethod.PUT,"/team").hasRole(ADMIN)
                        .anyRequest().authenticated())
                .httpBasic();

        return http.build();
    }
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

}
