package com.ngr.ngrbooks.books.rating;

import com.ngr.ngrbooks.books.Book;
import com.ngr.ngrbooks.user.profile.UserProfile;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

/**
 * Interfejs RatingRepository jest interfejsem Spring Data JPA do obsługi operacji na encji Rating w bazie danych.
 * Dziedziczy po interfejsie JpaRepository, co umożliwia korzystanie z gotowych metod do operacji CRUD.
 */
public interface RatingRepository extends JpaRepository<Rating, Long> {

    /**
     * Metoda zwracająca ocenę przypisaną do konkretnej książki przez danego użytkownika.
     *
     * @param book        Książka, dla której sprawdzana jest ocena.
     * @param userProfile Profil użytkownika, dla którego sprawdzana jest ocena.
     * @return Ocena przypisana do konkretnej książki przez danego użytkownika.
     */
    Rating findByBookAndUserProfile(Book book, UserProfile userProfile);

    /**
     * Metoda zwracająca listę ocen przypisanych do książek przez danego użytkownika.
     *
     * @param userProfile Profil użytkownika, dla którego pobierane są oceny.
     * @return Lista ocen przypisanych do książek przez danego użytkownika.
     */
    List<Rating> findByUserProfile(UserProfile userProfile);

    /**
     * Metoda zwracająca listę 10 najlepiej ocenianych książek w kolejności malejącej.
     *
     * @param pageable Obiekt Pageable umożliwiający określenie strony i ilości wyników.
     * @return Lista 10 najlepiej ocenianych książek w kolejności malejącej.
     */
    @Query("SELECT r.book FROM Rating r GROUP BY r.book ORDER BY AVG(r.rating) DESC")
    List<Book> findTop10Books(Pageable pageable);
}

