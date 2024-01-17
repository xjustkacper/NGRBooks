package com.ngr.ngrbooks.user;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

/**
 * Interfejs repozytorium danych dla encji użytkowników.
 */
public interface UserRepository extends JpaRepository<User, Long> {

    /**
     * Wyszukuje użytkownika na podstawie adresu e-mail.
     *
     * @param email Adres e-mail użytkownika.
     * @return Obiekt Optional zawierający użytkownika lub pusty, jeśli użytkownik o danym adresie e-mail nie istnieje.
     */
    Optional<User> findByEmail(String email);
}
