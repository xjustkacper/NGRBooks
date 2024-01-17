package com.ngr.ngrbooks.books.comment;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Klasa CommentService zapewnia logikę biznesową związaną z operacjami na komentarzach.
 * Jest oznaczona adnotacją @Service, co informuje Spring, że ta klasa jest serwisem.
 * Adnotacja @RequiredArgsConstructor generuje konstruktor zależności na podstawie pól oznaczonych adnotacją @Autowired.
 */
@Service
@RequiredArgsConstructor
public class CommentService {

    /**
     * Repozytorium komentarzy, używane do operacji na bazie danych.
     */
    private final CommentRepository commentRepository;

    /**
     * Metoda pobierająca listę komentarzy związanych z określoną książką na podstawie jej identyfikatora.
     *
     * @param id Identyfikator książki, dla której pobierane są komentarze.
     * @return Lista komentarzy związanych z określoną książką.
     */
    public List<Comment> getCommentsByBookId(Long id) {
        return commentRepository.findByBookId(id);
    }

    /**
     * Metoda dodająca nowy komentarz do bazy danych.
     *
     * @param comment Komentarz do dodania.
     */
    public void addComment(Comment comment) {
        commentRepository.save(comment);
    }
}

