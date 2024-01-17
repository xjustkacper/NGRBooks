package com.ngr.ngrbooks.registration.password;

import com.ngr.ngrbooks.user.User;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Calendar;
import java.util.Date;

/**
 * Klasa reprezentująca token resetujący hasło w kontekście resetowania hasła użytkownika.
 */
@Getter
@Setter
@Entity
@NoArgsConstructor
public class PasswordResetToken {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long token_id;           // Identyfikator tokenu resetującego hasło.

    private String token;            // Wartość tokenu resetującego hasło.

    private Date expirationTime;     // Data wygaśnięcia tokenu.

    private static final int EXPIRATION_TIME = 10;  // Czas ważności tokenu w minutach.

    @OneToOne
    @JoinColumn(name = "user_id")
    private User user;               // Użytkownik, dla którego utworzono token resetujący hasło.

    /**
     * Konstruktor klasy.
     *
     * @param token Wartość tokenu resetującego hasło.
     * @param user  Użytkownik, dla którego utworzono token resetujący hasło.
     */
    public PasswordResetToken(String token, User user) {
        super();
        this.token = token;
        this.user = user;
        this.expirationTime = this.getTokenExpirationTime();
    }

    /**
     * Konstruktor klasy, używany w przypadku, gdy nie ma jeszcze przypisanego użytkownika do tokenu.
     *
     * @param token Wartość tokenu resetującego hasło.
     */
    public PasswordResetToken(String token) {
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
