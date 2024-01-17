package com.ngr.ngrbooks.security;

import com.ngr.ngrbooks.user.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

/**
 * Serwis obsługujący ładowanie szczegółów użytkownika na podstawie adresu e-mail w kontekście rejestracji.
 */
@Service
@RequiredArgsConstructor
public class UserRegistrationDetailsService implements UserDetailsService {

    private final UserRepository userRepository;

    /**
     * Ładuje szczegóły użytkownika na podstawie adresu e-mail.
     *
     * @param email Adres e-mail użytkownika.
     * @return Szczegóły użytkownika w kontekście rejestracji.
     * @throws UsernameNotFoundException Wyjątek rzucany, gdy użytkownik o podanym adresie e-mail nie został znaleziony.
     */
    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        return userRepository.findByEmail(email)
                .map(UserRegistrationDetails::new)
                .orElseThrow(() -> new UsernameNotFoundException("User not found"));
    }
}
