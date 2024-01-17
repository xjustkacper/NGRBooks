package com.ngr.ngrbooks.registration.password;

import lombok.Data;

/**
 * Klasa pomocnicza reprezentująca dane formularza związane z żądaniem zmiany hasła.
 */
@Data
public class PasswordRequestUtil {
    private String email;         // Adres e-mail użytkownika.
    private String oldPassword;   // Stare hasło użytkownika.
    private String newPassword;   // Nowe hasło użytkownika.
}

