package com.ngr.ngrbooks.user.passwordReset;

import com.ngr.ngrbooks.user.User;
import com.ngr.ngrbooks.user.UserService;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Optional;
import java.util.UUID;

@Controller
@RequiredArgsConstructor
public class PasswordResetController {

    private final UserService userService;


    @PostMapping("/resetPassword")
    public GenericResponse resetPassword(HttpServletResponse response, @RequestParam("email") String userEmail) {
        Optional<User> userOptional = userService.findByEmail(userEmail);
        if (userOptional.isPresent()) {
            User user = userOptional.get();
            String token = UUID.randomUUID().toString();
            userService.createPasswordResetTokenForUser(user, token);
            mailSender.send(constructResetTokenEmail(getAppUrl(request), request.getLocale(), token, user));
            return new GenericResponse(messages.getMessage("message.resetPasswordEmail", null, request.getLocale()));

        }
    }

}
