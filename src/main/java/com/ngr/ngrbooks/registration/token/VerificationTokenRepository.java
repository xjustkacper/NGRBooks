package com.ngr.ngrbooks.registration.token;

import org.springframework.data.jpa.repository.JpaRepository;



/**
 * Interfejs repozytorium obsługujący operacje na encji VerificationToken w bazie danych.
 */
public interface VerificationTokenRepository extends JpaRepository<VerificationToken, Long> {

    /**
     * Metoda wyszukująca token weryfikacyjny na podstawie jego wartości.
     *
     * @param token Wartość tokenu weryfikacyjnego.
     * @return Encja VerificationToken odpowiadająca podanemu tokenowi.
     */
    VerificationToken findByToken(String token);
}
