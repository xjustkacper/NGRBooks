package com.ngr.ngrbooks.user;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.NaturalId;

import java.time.LocalDate;
import java.util.Date;

/**
 * Klasa reprezentująca encję użytkownika w systemie.
 */
@Getter
@Setter
@NoArgsConstructor
@Entity
@AllArgsConstructor
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;                 // Identyfikator użytkownika.

    private String nickname;         // Nick użytkownika.

    @NaturalId(mutable = true)
    private String email;            // Adres e-mail użytkownika, unikalny (NaturalId).

    private String password;         // Hasło użytkownika.

    private String role;             // Rola użytkownika (np. "USER", "ADMIN").

    private boolean isEnabled = false; // Flaga informująca, czy konto użytkownika jest aktywne.

    private LocalDate joinDate;       // Data dołączenia użytkownika do systemu.

}
