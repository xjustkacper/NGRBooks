package com.ngr.ngrbooks.books;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface BookRepository extends JpaRepository<Book, Long> {

    List<Book> findByTitleContainingIgnoreCaseOrAuthorContainingIgnoreCaseOrGenreContainingIgnoreCase(String title, String author, String genre);

    List<Book> findByGenreIgnoreCase(String category);

    @Query("SELECT DISTINCT b.genre FROM Book b")
    List<String> findDistinctGenre();
}
