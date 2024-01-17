package com.ngr.ngrbooks.books.favorites;

import com.ngr.ngrbooks.books.Book;
import com.ngr.ngrbooks.user.User;
import com.ngr.ngrbooks.user.profile.UserProfile;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * Klasa Favorites reprezentuje encję powiązaną z ulubionymi książkami użytkownika.
 * Jest to encja mapowana do bazy danych za pomocą adnotacji JPA.
 */
@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Favorites {

    /**
     * Unikalny identyfikator ulubionej pozycji.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * Książka, którą użytkownik dodał do ulubionych.
     * Wiele ulubionych może być powiązanych z jedną książką.
     */
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "book_id")
    private Book book;

    /**
     * Profil użytkownika, który dodał książkę do ulubionych.
     * Wiele ulubionych może być powiązanych z jednym profilem użytkownika.
     */
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private UserProfile user;

    // Konstruktory, gettery i settery są dostarczane za pomocą adnotacji Lombok.
}

