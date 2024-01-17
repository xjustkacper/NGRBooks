package com.ngr.ngrbooks.language;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.i18n.LocaleChangeInterceptor;
import org.springframework.web.servlet.i18n.SessionLocaleResolver;

import java.util.Locale;

/**
 * Konfiguracja obsługi języków w aplikacji Spring MVC.
 */
@Configuration
public class LanguageConfig implements WebMvcConfigurer {

    /**
     * Konfiguracja resolvera lokalizacji sesji.
     *
     * @return Obiekt LocaleResolver.
     */
    @Bean
    public LocaleResolver localeResolver() {
        SessionLocaleResolver localeResolver = new SessionLocaleResolver();
        localeResolver.setDefaultLocale(Locale.forLanguageTag("pl"));
        return localeResolver;
    }

    /**
     * Interceptor zmiany lokalizacji.
     *
     * @return Obiekt LocaleChangeInterceptor.
     */
    @Bean
    public LocaleChangeInterceptor localeChangeInterceptor() {
        LocaleChangeInterceptor lci = new LocaleChangeInterceptor();
        lci.setParamName("lang");
        return lci;
    }

    /**
     * Dodanie interceptora do rejestru interceptorów.
     *
     * @param registry Rejestr interceptorów.
     */
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(localeChangeInterceptor());
    }
}
