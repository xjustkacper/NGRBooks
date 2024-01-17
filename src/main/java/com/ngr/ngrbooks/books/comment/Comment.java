package com.ngr.ngrbooks.books.comment;

import com.ngr.ngrbooks.books.Book;
import com.ngr.ngrbooks.user.User;
import com.ngr.ngrbooks.user.profile.UserProfile;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * Klasa Comment reprezentuje komentarz związany z książką, dodany przez użytkownika.
 * Jest to encja mapowana do bazy danych za pomocą adnotacji JPA.
 */
@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Comment {

    /**
     * Unikalny identyfikator komentarza.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * Treść komentarza, przechowywana jako tekst.
     */
    @Column(columnDefinition = "TEXT")
    private String content;

    /**
     * Profil użytkownika związany z komentarzem.
     * Wiele komentarzy może być powiązanych z jednym profilem użytkownika.
     */
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private UserProfile userProfile;

    /**
     * Książka związana z komentarzem.
     * Wiele komentarzy może być powiązanych z jedną książką.
     */
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "book_id")
    private Book book;

    // Konstruktory, gettery i settery są dostarczane za pomocą adnotacji Lombok.
}
