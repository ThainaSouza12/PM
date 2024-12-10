package com.example.usermanagement.config;

import com.example.usermanagement.controller.AuthenticationManager;

@Configuration
public class SecurityConfig {

    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        ((Object) http.csrf()).disable()
            .authorizeRequests()
            .antMatchers("/api/auth/**").permitAll()
            .anyRequest().authenticated();
        return http.build();
    }

    @Bean
    public AuthenticationManager authenticationManager(HttpSecurity http, PasswordEncoder passwordEncoder)
            throws Exception {
        return ((Object) http.getSharedObject(AuthenticationManagerBuilder.class))
                .userDetailsService(username -> null) // Adicione seu UserDetailsService aqui
                .passwordEncoder(passwordEncoder)
                .and()
                .build();
    }
}
