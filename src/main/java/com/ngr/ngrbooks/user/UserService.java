package com.ngr.ngrbooks.user;

import com.ngr.ngrbooks.exception.UserAlreadyExistsException;
import com.ngr.ngrbooks.registration.RegistrationRequest;
import com.ngr.ngrbooks.registration.token.VerificationToken;
import com.ngr.ngrbooks.registration.token.VerificationTokenRepository;
import com.ngr.ngrbooks.user.passwordReset.PasswordResetToken;
import com.ngr.ngrbooks.user.passwordReset.PasswordResetTokenRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Calendar;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserService implements IUserService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final VerificationTokenRepository tokenRepository;
    private final PasswordResetTokenRepository passwordTokenRepository;

    @Override
    public List<User> getUsers() {
        return userRepository.findAll();
    }

    @Override
    public User registerUser(RegistrationRequest request) {
        Optional<User> user = this.findByEmail(request.email());
        if (user.isPresent()){
            throw new UserAlreadyExistsException("Użytkownik z email'em " + request.email() + " już istnieje");

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

    @Override
    public Optional<User> findByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    @Override
    public void saveUserVerificationToken(User theUser, String token) {
        var verificationToken = new VerificationToken(token, theUser);
        tokenRepository.save(verificationToken);



    }

    @Override
    public String validateToken(String theToken) {
        VerificationToken token = tokenRepository.findByToken(theToken);
        if (token == null){
            return "Nieprawidłowy token";
        }
        User user = token.getUser();
        Calendar calendar = Calendar.getInstance();
        if((token.getExpirationTime().getTime() - calendar.getTime().getTime()) <= 0){
            tokenRepository.delete(token);
            return "Token wygasł";
        }
        user.setEnabled(true);
        userRepository.save(user);
        return "Konto zostało aktywowane";
    }

    public void createPasswordResetTokenForUser(User user, String token) {
        PasswordResetToken myToken = new PasswordResetToken(token, user);
        passwordTokenRepository.save(myToken);

    }
}
