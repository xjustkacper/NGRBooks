package com.ngr.ngrbooks.books.rating;

import com.ngr.ngrbooks.books.Book;
import com.ngr.ngrbooks.user.profile.UserProfile;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * Klasa Rating reprezentuje ocenę przypisaną przez użytkownika do konkretnej książki.
 * Jest to encja mapowana do bazy danych za pomocą adnotacji JPA.
 */
@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Rating {

    /**
     * Unikalny identyfikator oceny.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * Wartość oceny przypisanej przez użytkownika.
     */
    private int rating;

    /**
     * Książka, do której przypisana jest ocena.
     * Wiele ocen może być przypisanych do jednej książki.
     */
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "book_id")
    private Book book;

    /**
     * Profil użytkownika, który przypisał ocenę.
     * Wiele ocen może być przypisanych do jednego profilu użytkownika.
     */
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private UserProfile userProfile;
}
