package com.ngr.ngrbooks.event;

import com.ngr.ngrbooks.user.User;
import lombok.Getter;
import lombok.Setter;
import org.springframework.context.ApplicationEvent;

import org.springframework.context.ApplicationEvent;

/**
 * Klasa reprezentująca zdarzenie zakończenia procesu rejestracji.
 * Rozszerza klasę ApplicationEvent, co wskazuje na użycie w mechanizmie zdarzeń Spring.
 */
@Getter
@Setter
public class RegistrationCompleteEvent extends ApplicationEvent {

    /**
     * Obiekt reprezentujący użytkownika, dla którego zakończono proces rejestracji.
     */
    private User user;

    /**
     * URL, który będzie używany w procesie rejestracji.
     */
    private String applicationUrl;

    /**
     * Konstruktor klasy RegistrationCompleteEvent.
     *
     * @param user           Obiekt reprezentujący użytkownika.
     * @param applicationUrl URL, który będzie używany w procesie rejestracji.
     */
    public RegistrationCompleteEvent(User user, String applicationUrl) {
        super(user);
        this.user = user;
        this.applicationUrl = applicationUrl;
    }
}

