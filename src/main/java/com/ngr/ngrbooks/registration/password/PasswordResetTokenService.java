package com.ngr.ngrbooks.registration.password;

import com.ngr.ngrbooks.user.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Calendar;
import java.util.Optional;


/**
 * Serwis obsługujący operacje związane z tokenami resetującymi hasło użytkownika.
 */
@Service
@RequiredArgsConstructor
public class PasswordResetTokenService {
    private final PasswordResetTokenRepository passwordResetTokenRepository;

    /**
     * Tworzy token resetujący hasło dla danego użytkownika.
     *
     * @param user           Użytkownik, dla którego tworzony jest token resetujący hasło.
     * @param passwordToken  Wartość tokenu resetującego hasło.
     */
    public void createPasswordResetTokenForUser(User user, String passwordToken) {
        PasswordResetToken passwordResetToken = new PasswordResetToken(passwordToken, user);
        passwordResetTokenRepository.save(passwordResetToken);
    }

    /**
     * Sprawdza, czy token resetujący hasło jest ważny.
     *
     * @param passwordResetToken Wartość tokenu resetującego hasło.
     * @return Informacja o ważności tokenu (np. "valid" lub odpowiedni komunikat o błędzie).
     */
    public String validatePasswordResetToken(String passwordResetToken) {
        PasswordResetToken token = passwordResetTokenRepository.findByToken(passwordResetToken);
        if (token == null) {
            return "Invalid verification token";
        }

        User user = token.getUser();
        Calendar calendar = Calendar.getInstance();

        if (token.getExpirationTime().getTime() - calendar.getTime().getTime() <= 0) {
            return "Link already expired, resend link";
        }

        return "valid";
    }

    /**
     * Znajduje użytkownika na podstawie tokenu resetującego hasło.
     *
     * @param passwordResetToken Wartość tokenu resetującego hasło.
     * @return Obiekt Optional zawierający użytkownika lub pusty, jeśli użytkownik nie istnieje.
     */
    public Optional<User> findUserByPasswordToken(String passwordResetToken) {
        return Optional.ofNullable(passwordResetTokenRepository.findByToken(passwordResetToken).getUser());
    }

    /**
     * Znajduje token resetujący hasło na podstawie jego wartości.
     *
     * @param token Wartość tokenu resetującego hasło.
     * @return Encja PasswordResetToken odpowiadająca podanemu tokenowi.
     */
    public PasswordResetToken findPasswordResetToken(String token) {
        return passwordResetTokenRepository.findByToken(token);
    }
}
