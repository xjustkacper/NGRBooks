package com.ngr.ngrbooks.books.favorites;

import com.ngr.ngrbooks.books.Book;
import com.ngr.ngrbooks.user.profile.UserProfile;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

/**
 * Interfejs FavoritesRepository jest interfejsem Spring Data JPA do obsługi operacji na encji Favorites w bazie danych.
 * Dziedziczy po interfejsie JpaRepository, co umożliwia korzystanie z gotowych metod do operacji CRUD.
 */
public interface FavoritesRepository extends JpaRepository<Favorites, Long> {

    /**
     * Metoda zwracająca ulubioną pozycję na podstawie konkretnej książki i profilu użytkownika.
     *
     * @param book     Książka, dla której sprawdzana jest ulubiona pozycja.
     * @param userProfile Profil użytkownika, dla którego sprawdzana jest ulubiona pozycja.
     * @return Ulubiona pozycja dla danej książki i użytkownika.
     */
    Favorites findByBookAndUser(Book book, UserProfile userProfile);

    /**
     * Metoda zwracająca listę książek będących ulubionymi przez danego użytkownika.
     *
     * @param userProfile Profil użytkownika, dla którego pobierane są ulubione książki.
     * @return Lista ulubionych książek danego użytkownika.
     */
    @Query("SELECT f.book FROM Favorites f WHERE f.user = ?1")
    List<Book> findAllByUser(UserProfile userProfile);
}

