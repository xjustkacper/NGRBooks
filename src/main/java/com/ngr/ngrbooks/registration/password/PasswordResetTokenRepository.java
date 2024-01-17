package com.ngr.ngrbooks.registration.password;

import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Interfejs repozytorium obsługujący operacje na encji PasswordResetToken w bazie danych.
 */
public interface PasswordResetTokenRepository extends JpaRepository<PasswordResetToken, Long> {

    /**
     * Metoda wyszukująca token resetujący hasło na podstawie wartości tokenu.
     *
     * @param passwordResetToken Wartość tokenu resetującego hasło.
     * @return Encja PasswordResetToken odpowiadająca podanemu tokenowi.
     */
    PasswordResetToken findByToken(String passwordResetToken);
}