package com.ngr.ngrbooks.books;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

/**
 * Interfejs BookRepository jest interfejsem Spring Data JPA do obsługi operacji na encji Book w bazie danych.
 * Dziedziczy po interfejsie JpaRepository, co umożliwia korzystanie z gotowych metod do operacji CRUD.
 */
public interface BookRepository extends JpaRepository<Book, Long> {

    /**
     * Metoda zwracająca listę książek, których tytuł, autor lub gatunek zawiera podany ciąg znaków (ignorując wielkość liter).
     *
     * @param title  Ciąg znaków, który może być częścią tytułu książki.
     * @param author Ciąg znaków, który może być częścią autora książki.
     * @param genre  Ciąg znaków, który może być częścią gatunku książki.
     * @return Lista książek spełniających kryteria wyszukiwania.
     */
    List<Book> findByTitleContainingIgnoreCaseOrAuthorContainingIgnoreCaseOrGenreContainingIgnoreCase(String title, String author, String genre);

    /**
     * Metoda zwracająca listę książek danego gatunku (ignorując wielkość liter).
     *
     * @param category Gatunek, dla którego pobierane są książki.
     * @return Lista książek danego gatunku.
     */
    List<Book> findByGenreIgnoreCase(String category);

    /**
     * Metoda zwracająca listę unikalnych gatunków książek.
     *
     * @return Lista unikalnych gatunków książek.
     */
    @Query("SELECT DISTINCT b.genre FROM Book b")
    List<String> findDistinctGenre();
}
