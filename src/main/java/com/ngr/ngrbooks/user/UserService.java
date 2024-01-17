package com.ngr.ngrbooks.user;

import com.ngr.ngrbooks.exception.UserAlreadyExistsException;
import com.ngr.ngrbooks.registration.RegistrationRequest;
import com.ngr.ngrbooks.registration.password.PasswordResetTokenService;
import com.ngr.ngrbooks.registration.token.VerificationToken;
import com.ngr.ngrbooks.registration.token.VerificationTokenRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Calendar;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

/**
 * Serwis obsługujący operacje związane z użytkownikami w systemie.
 */
@Service
@RequiredArgsConstructor
public class UserService implements IUserService {

    private final UserRepository userRepository;                     // Repozytorium danych dla użytkowników.
    private final PasswordEncoder passwordEncoder;                   // Encoder do hasła.
    private final VerificationTokenRepository tokenRepository;       // Repozytorium danych dla tokenów weryfikacyjnych.
    private final PasswordResetTokenService passwordResetTokenService; // Serwis obsługujący operacje na tokenach resetujących hasło.

    /**
     * Pobiera listę wszystkich użytkowników.
     *
     * @return Lista użytkowników w systemie.
     */
    @Override
    public List<User> getUsers() {
        return userRepository.findAll();
    }

    /**
     * Rejestruje nowego użytkownika na podstawie danych z formularza rejestracyjnego.
     *
     * @param request Obiekt zawierający dane rejestracyjne użytkownika.
     * @return Zarejestrowany użytkownik.
     */
    @Override
    public User registerUser(RegistrationRequest request) {
        Optional<User> user = this.findByEmail(request.email());
        if (user.isPresent()) {
            throw new UserAlreadyExistsException("Użytkownik z adresem e-mail " + request.email() + " już istnieje");
        }

        var newUser = new User();
        LocalDate localDate = LocalDate.now(ZoneId.of("Europe/Warsaw"));
        newUser.setNickname(request.nickname());
        newUser.setEmail(request.email());
        newUser.setPassword(passwordEncoder.encode(request.password()));
        newUser.setRole("USER");
        newUser.setJoinDate(localDate);
        return userRepository.save(newUser);
    }

    /**
     * Wyszukuje użytkownika na podstawie adresu e-mail.
     *
     * @param email Adres e-mail użytkownika.
     * @return Obiekt Optional zawierający użytkownika lub pusty, jeśli użytkownik o danym adresie e-mail nie istnieje.
     */
    @Override
    public Optional<User> findByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    /**
     * Zapisuje token weryfikacyjny dla użytkownika.
     *
     * @param theUser Użytkownik, dla którego zapisywany jest token.
     * @param token   Token weryfikacyjny.
     */
    @Override
    public void saveUserVerificationToken(User theUser, String token) {
        var verificationToken = new VerificationToken(token, theUser);
        tokenRepository.save(verificationToken);
    }

    /**
     * Waliduje token weryfikacyjny.
     *
     * @param theToken Token weryfikacyjny do walidacji.
     * @return Wynik walidacji (np. "valid" lub odpowiedni komunikat o błędzie).
     */
    @Override
    public String validateToken(String theToken) {
        VerificationToken token = tokenRepository.findByToken(theToken);
        if (token == null) {
            return "Nieprawidłowy token";
        }
        User user = token.getUser();
        Calendar calendar = Calendar.getInstance();
        if ((token.getExpirationTime().getTime() - calendar.getTime().getTime()) <= 0) {
            tokenRepository.delete(token);
            return "Token wygasł";
        }
        user.setEnabled(true);
        userRepository.save(user);
        return "Konto zostało aktywowane";
    }

    /**
     * Generuje nowy token weryfikacyjny na podstawie istniejącego tokenu.
     *
     * @param oldToken Istniejący token weryfikacyjny.
     * @return Nowy token weryfikacyjny.
     */
    @Override
    public VerificationToken generateNewVerificationToken(String oldToken) {
        VerificationToken verificationToken = tokenRepository.findByToken(oldToken);
        var verificationTokenTime = new VerificationToken();
        verificationToken.setToken(UUID.randomUUID().toString());
        verificationToken.setExpirationTime(verificationTokenTime.getTokenExpirationTime());
        return tokenRepository.save(verificationToken);
    }

    /**
     * Zmienia hasło użytkownika.
     *
     * @param theUser      Użytkownik, dla którego zmieniane jest hasło.
     * @param newPassword  Nowe hasło użytkownika.
     */
    public void changePassword(User theUser, String newPassword) {
        theUser.setPassword(passwordEncoder.encode(newPassword));
        userRepository.save(theUser);
    }

    /**
     * Waliduje token resetujący hasło.
     *
     * @param token Token resetujący hasło do walidacji.
     * @return Wynik walidacji (np. "valid" lub odpowiedni komunikat o błędzie).
     */
    @Override
    public String validatePasswordResetToken(String token) {
        return passwordResetTokenService.validatePasswordResetToken(token);
    }

    /**
     * Znajduje użytkownika na podstawie tokena resetującego hasło.
     *
     * @param token Token resetujący hasło.
     * @return Znaleziony użytkownik lub null, jeśli użytkownik nie istnieje.
     */
    @Override
    public User findUserByPasswordToken(String token) {
        return passwordResetTokenService.findUserByPasswordToken(token).orElse(null);
    }

    /**
     * Tworzy token resetujący hasło dla danego użytkownika.
     *
     * @param user               Użytkownik, dla którego tworzony jest token resetujący hasło.
     * @param passwordResetToken Token resetujący hasło.
     */
    @Override
    public void createPasswordResetTokenForUser(User user, String passwordResetToken) {
        passwordResetTokenService.createPasswordResetTokenForUser(user, passwordResetToken);
    }
}
