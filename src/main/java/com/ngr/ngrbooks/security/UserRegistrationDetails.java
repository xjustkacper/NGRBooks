package com.ngr.ngrbooks.security;

import com.ngr.ngrbooks.user.User;
import lombok.Data;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Klasa implementująca interfejs UserDetails, reprezentująca szczegóły zarejestrowanego użytkownika w kontekście bezpieczeństwa.
 */
@Data
public class UserRegistrationDetails implements UserDetails {

    private String userName;                    // Nazwa użytkownika (adres e-mail).
    private String password;                    // Hasło użytkownika.
    private boolean isEnabled;                  // Informacja o tym, czy konto użytkownika jest aktywne.
    private List<GrantedAuthority> authorities; // Lista uprawnień użytkownika.

    /**
     * Konstruktor klasy.
     *
     * @param user Obiekt User reprezentujący użytkownika.
     */
    public UserRegistrationDetails(User user) {
        this.userName = user.getEmail();
        this.password = user.getPassword();
        this.isEnabled = user.isEnabled();

        // Konwersja ciągu znaków z uprawnieniami na listę obiektów GrantedAuthority.
        this.authorities = Arrays.stream(user.getRole()
                        .split(","))
                .map(SimpleGrantedAuthority::new)
                .collect(Collectors.toList());
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return userName;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return isEnabled;
    }
}
