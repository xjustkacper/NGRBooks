package com.ngr.ngrbooks.registration;


/**
 * Klasa reprezentująca żądanie rejestracji użytkownika.
 */
public record RegistrationRequest(
        String nickname,
        String email,
        String password,
        String role) {
}
