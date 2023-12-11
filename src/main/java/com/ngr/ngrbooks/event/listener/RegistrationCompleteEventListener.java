package com.ngr.ngrbooks.event.listener;
import com.ngr.ngrbooks.event.RegistrationCompleteEvent;
import com.ngr.ngrbooks.user.User;
import com.ngr.ngrbooks.user.UserService;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationListener;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;

import java.io.UnsupportedEncodingException;
import java.util.UUID;
@Slf4j
@Component
@RequiredArgsConstructor
public class RegistrationCompleteEventListener implements ApplicationListener<RegistrationCompleteEvent> {
private final UserService userService;
private final JavaMailSender mailSender;

private User theUser;
    @Override
    public void onApplicationEvent(RegistrationCompleteEvent event) {
    theUser = event.getUser();
    String verificationToken = UUID.randomUUID().toString();
    userService.saveUserVerificationToken(theUser, verificationToken);
    String url = event.getApplicationUrl() + "/register/verifyEmail?token=" + verificationToken;
        try {
            sendVerificationEmail(url);
        } catch (MessagingException | UnsupportedEncodingException e) {
            throw new RuntimeException(e);
        }
        log.info("Kliknij w link aktywacyjny:   {}", url);
    }
    public void sendVerificationEmail(String url) throws MessagingException, UnsupportedEncodingException {
        String subject = "Weryfikacja adresu email";
        String senderName = "NGR Books";
        String mailContent = "Witaj, " + theUser.getNickname() + " kliknij w link aby aktywować konto: " + url;
        MimeMessage message = mailSender.createMimeMessage();
        var messageHelper = new MimeMessageHelper(message);
        messageHelper.setFrom("ngrcompany21@gmail.com", senderName);
        messageHelper.setTo(theUser.getEmail());
        messageHelper.setSubject(subject);
        messageHelper.setText(mailContent, true);
        mailSender.send(message);

    }
}
