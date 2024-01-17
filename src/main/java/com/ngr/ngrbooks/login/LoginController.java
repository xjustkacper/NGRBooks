package com.ngr.ngrbooks.login;


import com.ngr.ngrbooks.event.listener.RegistrationCompleteEventListener;
import com.ngr.ngrbooks.registration.password.PasswordRequestUtil;
import com.ngr.ngrbooks.registration.token.VerificationTokenRepository;
import com.ngr.ngrbooks.user.User;
import com.ngr.ngrbooks.user.UserService;
import jakarta.mail.MessagingException;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.io.UnsupportedEncodingException;
import java.util.Optional;
import java.util.UUID;

/**
 * Kontroler obsługujący operacje związane z logowaniem i resetowaniem hasła.
 */
@Slf4j
@Controller
@RequiredArgsConstructor
@RequestMapping("/login")
public class LoginController {

    private final UserService userService;
    private final ApplicationEventPublisher publisher;
    private final VerificationTokenRepository tokenRepository;
    private final RegistrationCompleteEventListener eventListener;
    private final HttpServletRequest servletRequest;

    /**
     * Obsługuje żądanie odnośnie żądania zresetowania hasła.
     *
     * @param model Model Spring, umożliwia przekazywanie danych do widoku.
     * @return Nazwa widoku strony żądania zresetowania hasła.
     */
    @GetMapping("/password-reset-request")
    public String passwordResetRequest(Model model) {
        model.addAttribute("passwordRequestUtil", new PasswordRequestUtil());
        return "password-reset-request";
    }

    /**
     * Obsługuje żądanie zresetowania hasła.
     *
     * @param passwordRequestUtil Obiekt PasswordRequestUtil z danymi formularza.
     * @param servletRequest      Obiekt HttpServletRequest do uzyskania informacji o żądaniu.
     * @return Przekierowanie do głównej strony.
     * @throws MessagingException         Wyjątek związany z wysyłaniem wiadomości.
     * @throws UnsupportedEncodingException Wyjątek związany z nieobsługiwanym kodowaniem znaków.
     */
    @PostMapping("/password-reset-request")
    public String resetPasswordRequest(@ModelAttribute("passwordRequestUtil") PasswordRequestUtil passwordRequestUtil,
                                       final HttpServletRequest servletRequest)
            throws MessagingException, UnsupportedEncodingException {

        Optional<User> user = userService.findByEmail(passwordRequestUtil.getEmail());

        if (user.isPresent()) {
            String passwordResetToken = UUID.randomUUID().toString();
            userService.createPasswordResetTokenForUser(user.get(), passwordResetToken);
            String applicationUrl = applicationUrl(servletRequest);
            passwordResetEmailLink(user.get(), applicationUrl, passwordResetToken);
        }
        return "redirect:/";
    }

    /**
     * Generuje link do strony zmiany hasła i wysyła e-mail z linkiem.
     *
     * @param user           Obiekt User, dla którego zmieniane jest hasło.
     * @param applicationUrl Bazowy URL aplikacji.
     * @param passwordToken  Token resetujący hasło.
     * @throws MessagingException         Wyjątek związany z wysyłaniem wiadomości.
     * @throws UnsupportedEncodingException Wyjątek związany z nieobsługiwanym kodowaniem znaków.
     */
    private String passwordResetEmailLink(User user, String applicationUrl, String passwordToken)
            throws MessagingException, UnsupportedEncodingException {
        String url = applicationUrl + "/login/reset-password?token=" + passwordToken;
        eventListener.sendPasswordResetVerificationEmail(url);
        log.info("Click the link to reset your password: {}", url);
        return url;
    }

    /**
     * Obsługuje żądanie zmiany hasła.
     *
     * @param token Token resetujący hasło.
     * @param model Model Spring, umożliwia przekazywanie danych do widoku.
     * @return Nazwa widoku strony zmiany hasła.
     */
    @GetMapping("/reset-password")
    public String changePassword(@RequestParam("token") String token, Model model) {
        model.addAttribute("passwordRequestUtil", new PasswordRequestUtil());
        model.addAttribute("token", token);
        return "reset-password";
    }

    /**
     * Obsługuje żądanie potwierdzenia zmiany hasła.
     *
     * @param passwordRequestUtil Obiekt PasswordRequestUtil z danymi formularza.
     * @param token               Token resetujący hasło.
     * @return Przekierowanie do głównej strony lub komunikat o błędzie.
     */
    @PostMapping("/reset-password")
    public String resetPassword(@ModelAttribute("passwordRequestUtil") PasswordRequestUtil passwordRequestUtil,
                                @RequestParam("token") String token) {
        String tokenVerificationResult = userService.validatePasswordResetToken(token);
        if (!tokenVerificationResult.equalsIgnoreCase("valid")) {
            return "Invalid token password reset token";
        }
        Optional<User> theUser = Optional.ofNullable(userService.findUserByPasswordToken(token));
        if (theUser.isPresent()) {
            userService.changePassword(theUser.get(), passwordRequestUtil.getNewPassword());
            return "redirect:/";
        }
        return "Invalid password reset token";
    }

    /**
     * Generuje URL aplikacji na podstawie obiektu HttpServletRequest.
     *
     * @param request Obiekt HttpServletRequest.
     * @return Pełny URL aplikacji.
     */
    public String applicationUrl(HttpServletRequest request) {
        return "http://" + request.getServerName() + ":"
                + request.getServerPort() + request.getContextPath();
    }
}
