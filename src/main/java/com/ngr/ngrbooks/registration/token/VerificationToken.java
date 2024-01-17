package com.ngr.ngrbooks.registration.token;

import com.ngr.ngrbooks.user.User;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Calendar;
import java.util.Date;
/**
 * Klasa reprezentująca token weryfikacyjny dla użytkownika w kontekście rejestracji.
 */
@Getter
@Setter
@Entity
@NoArgsConstructor
public class VerificationToken {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;                    // Identyfikator tokenu w bazie danych.

    private String token;               // Wartość tokenu weryfikacyjnego.

    private Date expirationTime;        // Data wygaśnięcia tokenu.

    private static final int EXPIRATION_TIME = 15;  // Czas ważności tokenu w minutach.

    @OneToOne
    @JoinColumn(name = "user_id")
    private User user;                  // Użytkownik, dla którego utworzono token weryfikacyjny.

    /**
     * Konstruktor klasy.
     *
     * @param token Wartość tokenu weryfikacyjnego.
     * @param user  Użytkownik, dla którego utworzono token weryfikacyjny.
     */
    public VerificationToken(String token, User user) {
        super();
        this.token = token;
        this.user = user;
        this.expirationTime = this.getTokenExpirationTime();
    }

    /**
     * Konstruktor klasy, używany w przypadku, gdy nie ma jeszcze przypisanego użytkownika do tokenu.
     *
     * @param token Wartość tokenu weryfikacyjnego.
     */
    public VerificationToken(String token) {
        super();
        this.token = token;
        this.expirationTime = this.getTokenExpirationTime();
    }

    /**
     * Metoda obliczająca datę wygaśnięcia tokenu na podstawie bieżącej daty i czasu oraz czasu ważności tokenu.
     *
     * @return Data wygaśnięcia tokenu.
     */
    public Date getTokenExpirationTime() {
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(new Date().getTime());
        calendar.add(Calendar.MINUTE, EXPIRATION_TIME);
        return new Date(calendar.getTime().getTime());
    }
}
