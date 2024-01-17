package com.ngr.ngrbooks.books;


import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * Klasa Book reprezentuje encję książki.
 * Jest to encja mapowana do bazy danych za pomocą adnotacji JPA.
 */
@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Book {

    /**
     * Unikalny identyfikator książki.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * Tytuł książki.
     */
    private String title;

    /**
     * Autor książki.
     */
    private String author;

    /**
     * Gatunek literacki książki.
     */
    private String genre;

    /**
     * Opis książki.
     */
    private String description;

    /**
     * Ścieżka do okładki książki.
     */
    private String cover;

    /**
     * Data wydania książki.
     */
    private String releaseDate;

    /**
     * Ścieżka do pliku PDF z treścią książki.
     */
    private String pdf;
}
