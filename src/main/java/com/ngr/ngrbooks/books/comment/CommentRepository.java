package com.ngr.ngrbooks.books.comment;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * Interfejs CommentRepository jest interfejsem Spring Data JPA do obsługi operacji na encji Comment w bazie danych.
 * Dziedziczy po interfejsie JpaRepository, co umożliwia korzystanie z gotowych metod do operacji CRUD.
 */
public interface CommentRepository extends JpaRepository<Comment, Long> {

    /**
     * Metoda zwracająca listę komentarzy powiązanych z określoną książką na podstawie jej identyfikatora.
     *
     * id Identyfikator książki, dla której pobierane są komentarze.
     * Lista komentarzy związanych z określoną książką.
     */
    List<Comment> findByBookId(Long id);
}
