package com.ngr.ngrbooks.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.savedrequest.HttpSessionRequestCache;

/**
 * Konfiguracja bezpieczeństwa dla rejestracji użytkowników.
 */
@Configuration
@EnableWebSecurity
public class UserRegistrationSecurityConfig {

    /**
     * Bean dostarczający encoder do hasła.
     *
     * @return BCryptPasswordEncoder
     */
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    /**
     * Konfiguracja łańcucha filtrów bezpieczeństwa.
     *
     * @param http Obiekt HttpSecurity do konfiguracji.
     * @return SecurityFilterChain
     * @throws Exception Wyjątek rzucany w przypadku problemów z konfiguracją.
     */
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        HttpSessionRequestCache requestCache = new HttpSessionRequestCache();
        requestCache.setMatchingRequestParameterName(null);
        http.requestCache((cache) -> cache.requestCache(requestCache));

        return http.cors()
                .and().csrf().disable()
                .authorizeHttpRequests()
                .requestMatchers("/register/**", "/", "/login/**", "/confirm/**", "/css/**", "/js/**", "/images/**", "/webjars/**", "/register", "/password-reset-request", "/login/password-reset-request/**", "/books/{id}", "/search/**", "/books", "/books/{id}/read")
                .permitAll()
                .and()
                .authorizeHttpRequests()
                .requestMatchers("/users/**", "/profile/**", "/edit/**", "/books/{id}/addComment", "/books/{id}/addRating", "/books/{id}/favourite")
                .hasAnyAuthority("USER", "ADMIN")
                .and()
                .authorizeHttpRequests()
                .requestMatchers("/logout")
                .hasAnyAuthority("USER", "ADMIN")
                .and()
                .logout()
                .logoutSuccessUrl("/")
                .and()
                .formLogin()
                .loginPage("/login")
                .usernameParameter("email")
                .defaultSuccessUrl("/")
                .and()
                .build();
    }
}
