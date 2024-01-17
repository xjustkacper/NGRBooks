package com.ngr.ngrbooks.books;

import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Serwis obsługujący operacje związane z książkami.
 * Oznaczony adnotacją @Service, co informuje Spring, że ta klasa jest serwisem.
 * Adnotacja @RequiredArgsConstructor generuje konstruktor zależności na podstawie pól oznaczonych adnotacją @Autowired.
 */
@Service
@RequiredArgsConstructor
public class BookService {
    private final BookRepository bookRepository;

    /**
     * Metoda pobierająca informacje o książce na podstawie jej identyfikatora.
     *
     * @param id Identyfikator książki.
     * @return Obiekt książki.
     * @throws RuntimeException Jeśli książka o danym identyfikatorze nie istnieje.
     */
    public Book getBookById(Long id) {
        return bookRepository.findById(id).orElseThrow(() -> new RuntimeException("Book not found"));
    }

    /**
     * Metoda wyszukująca książki na podstawie kluczowego słowa.
     *
     * @param keyword Kluczowe słowo do wyszukania.
     * @return Lista książek pasujących do kluczowego słowa.
     */
    public List<Book> searchByKeyword(String keyword) {
        return bookRepository.findByTitleContainingIgnoreCaseOrAuthorContainingIgnoreCaseOrGenreContainingIgnoreCase(keyword, keyword, keyword);
    }

    /**
     * Metoda pobierająca wszystkie książki.
     *
     * @return Lista wszystkich książek.
     */
    public List<Book> getBooks() {
        return bookRepository.findAll();
    }

    /**
     * Metoda pobierająca książki na podstawie kategorii.
     *
     * @param category Kategoria do filtrowania książek.
     * @return Lista książek należących do danej kategorii.
     */
    public List<Book> getBooksByCategory(String category) {
        return bookRepository.findByGenreIgnoreCase(category);
    }

    /**
     * Metoda pobierająca unikalne kategorie książek.
     *
     * @return Lista unikalnych kategorii książek.
     */
    public List<String> getCategories() {
        return bookRepository.findDistinctGenre();
    }
}

