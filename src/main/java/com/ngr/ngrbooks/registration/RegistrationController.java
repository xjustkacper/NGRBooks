package com.ngr.ngrbooks.registration;

import com.ngr.ngrbooks.event.RegistrationCompleteEvent;
import com.ngr.ngrbooks.registration.token.VerificationToken;
import com.ngr.ngrbooks.registration.token.VerificationTokenRepository;
import com.ngr.ngrbooks.user.User;
import com.ngr.ngrbooks.user.UserService;
import com.ngr.ngrbooks.user.profile.UserProfile;
import com.ngr.ngrbooks.user.profile.UserProfileService;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/register")
public class RegistrationController {

    private final UserService userService;
    private final ApplicationEventPublisher publisher;
    private final VerificationTokenRepository tokenRepository;
    private final UserProfileService userProfile;
    @PostMapping
    public String registerUser(@RequestBody RegistrationRequest registrationRequest, final HttpServletRequest request){


        User user = userService.registerUser(registrationRequest);
        publisher.publishEvent(new RegistrationCompleteEvent(user, applicationUrl(request)));

        return "Sukces! Sprawdź email aby aktywować konto";


    }
    @GetMapping("/verifyEmail")
    public String verifyEmail(@RequestParam("token") String token){
        VerificationToken theToken = tokenRepository.findByToken(token);
        if(theToken.getUser().isEnabled()){
            return "Konto zostało już aktywowane.";
        }
        String verificationResult = userService.validateToken(token);
        if(verificationResult.equalsIgnoreCase("Konto zostało aktywowane")){
            userProfile.createUserProfile(theToken.getUser());
            return "Email został zweryfikowany";
        }
    return "Nieprawidłowy token";
    }

    public String applicationUrl(HttpServletRequest request) {
        return "http://" + request.getServerName() + ":" + request.getServerPort() + request.getContextPath();
    }
}
