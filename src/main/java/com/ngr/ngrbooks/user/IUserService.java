package com.ngr.ngrbooks.user;

import com.ngr.ngrbooks.registration.RegistrationRequest;
import com.ngr.ngrbooks.registration.token.VerificationToken;

import java.util.List;
import java.util.Optional;

/**
 * Interfejs definiujący operacje związane z obsługą użytkowników w systemie.
 */
public interface IUserService {

    /**
     * Pobiera listę wszystkich użytkowników.
     *
     * @return Lista użytkowników.
     */
    List<User> getUsers();

    /**
     * Rejestruje nowego użytkownika na podstawie danych z formularza rejestracyjnego.
     *
     * @param request Obiekt zawierający dane rejestracyjne użytkownika.
     * @return Zarejestrowany użytkownik.
     */
    User registerUser(RegistrationRequest request);

    /**
     * Wyszukuje użytkownika na podstawie adresu e-mail.
     *
     * @param email Adres e-mail użytkownika.
     * @return Obiekt Optional zawierający użytkownika lub pusty, jeśli użytkownik nie istnieje.
     */
    Optional<User> findByEmail(String email);

    /**
     * Zapisuje token weryfikacyjny dla użytkownika.
     *
     * @param theUser            Użytkownik, dla którego zapisywany jest token.
     * @param verificationToken Token weryfikacyjny.
     */
    void saveUserVerificationToken(User theUser, String verificationToken);

    /**
     * Waliduje token weryfikacyjny.
     *
     * @param theToken Token weryfikacyjny do walidacji.
     * @return Wynik walidacji (np. "valid" lub odpowiedni komunikat o błędzie).
     */
    String validateToken(String theToken);

    /**
     * Generuje nowy token weryfikacyjny na podstawie istniejącego tokenu.
     *
     * @param oldToken Istniejący token weryfikacyjny.
     * @return Nowy token weryfikacyjny.
     */
    VerificationToken generateNewVerificationToken(String oldToken);

    /**
     * Waliduje token resetujący hasło.
     *
     * @param token Token resetujący hasło do walidacji.
     * @return Wynik walidacji (np. "valid" lub odpowiedni komunikat o błędzie).
     */
    String validatePasswordResetToken(String token);

    /**
     * Znajduje użytkownika na podstawie tokena resetującego hasło.
     *
     * @param token Token resetujący hasło.
     * @return Znaleziony użytkownik lub null, jeśli użytkownik nie istnieje.
     */
    User findUserByPasswordToken(String token);

    /**
     * Tworzy token resetujący hasło dla danego użytkownika.
     *
     * @param user               Użytkownik, dla którego tworzony jest token resetujący hasło.
     * @param passwordResetToken Token resetujący hasło.
     */
    void createPasswordResetTokenForUser(User user, String passwordResetToken);
}
