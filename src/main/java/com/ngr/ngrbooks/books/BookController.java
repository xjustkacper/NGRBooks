package com.ngr.ngrbooks.books;

import com.ngr.ngrbooks.books.comment.Comment;
import com.ngr.ngrbooks.books.comment.CommentService;
import com.ngr.ngrbooks.books.favorites.FavoritesService;
import com.ngr.ngrbooks.books.rating.Rating;
import com.ngr.ngrbooks.books.rating.RatingRepository;
import com.ngr.ngrbooks.books.rating.RatingService;
import com.ngr.ngrbooks.user.User;
import com.ngr.ngrbooks.user.UserService;
import com.ngr.ngrbooks.user.profile.UserProfile;
import com.ngr.ngrbooks.user.profile.UserProfileController;
import com.ngr.ngrbooks.user.profile.UserProfileRepository;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.security.Principal;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

/**
 * Kontroler obsługujący żądania związane z książkami.
 * Oznaczony adnotacją @Controller, co informuje Spring, że ta klasa jest kontrolerem.
 * Adnotacja @RequiredArgsConstructor generuje konstruktor zależności na podstawie pól oznaczonych adnotacją @Autowired.
 */
@Controller
@RequiredArgsConstructor
public class BookController {

    private final BookService bookService;
    private final CommentService commentService;
    private final UserProfileRepository userProfileRepository;
    private final UserService userService;
    private final RatingService ratingService;
    private final RatingRepository ratingRepository;
    private final FavoritesService favoritesService;

    /**
     * Metoda obsługująca żądanie wyświetlenia strony z informacjami o konkretnej książce.
     *
     * @param id         Identyfikator książki.
     * @param model      Model do przekazania danych do widoku.
     * @param principal  Obiekt Principal reprezentujący zalogowanego użytkownika.
     * @return Nazwa widoku.
     */
    @GetMapping("/books/{id}")
    public String getBookById(@PathVariable Long id, Model model, Principal principal) {
        // Pobranie informacji o książce o danym identyfikatorze.
        Book book = bookService.getBookById(id);

        if (book != null) {
            // Dodanie informacji o książce do modelu.
            model.addAttribute("book", book);

            // Pobranie komentarzy do tej książki.
            List<Comment> comments = commentService.getCommentsByBookId(id);

            // Sprawdzenie, czy użytkownik jest zalogowany.
            if (principal != null) {
                // Pobranie informacji o zalogowanym użytkowniku.
                User user = userService.findByEmail(principal.getName())
                        .orElseThrow(() -> new RuntimeException("Logged-in user not found"));
                UserProfile userProfile = userProfileRepository.findByUserId(user.getId());

                // Dodanie informacji o zalogowanym użytkowniku do modelu.
                model.addAttribute("userProfile", userProfile);

                // Pobranie oceny użytkownika dla tej książki.
                Rating rating = ratingRepository.findByBookAndUserProfile(book, userProfile);
                model.addAttribute("rating", rating);

                // Sprawdzenie, czy książka jest w ulubionych użytkownika.
                boolean isFavourite = favoritesService.isFavourite(book, userProfile);
                model.addAttribute("isFavourite", isFavourite);
            }

            // Dodanie komentarzy do modelu.
            model.addAttribute("comments", comments);
            model.addAttribute("comment", new Comment());

            // Zwrócenie nazwy widoku.
            return "bookpage";
        } else {
            // Przekierowanie na stronę z listą książek, jeśli książka o danym identyfikatorze nie istnieje.
            return "redirect:/books";
        }
    }

    /**
     * Metoda obsługująca żądanie dodania lub usunięcia książki z ulubionych przez użytkownika.
     *
     * @param id         Identyfikator książki.
     * @param add        Flaga określająca, czy książka ma zostać dodana (true) lub usunięta (false) z ulubionych.
     * @param principal  Obiekt Principal reprezentujący zalogowanego użytkownika.
     * @return Przekierowanie na stronę z informacjami o konkretnej książce.
     */
    @PostMapping("/books/{id}/favourite")
    public String handleFavourite(@PathVariable Long id, @RequestParam boolean add, Principal principal) {
        // Pobranie informacji o książce o danym identyfikatorze.
        Book book = bookService.getBookById(id);

        // Pobranie informacji o zalogowanym użytkowniku.
        User user = userService.findByEmail(principal.getName())
                .orElseThrow(() -> new RuntimeException("Logged-in user not found"));
        UserProfile userProfile = userProfileRepository.findByUserId(user.getId());

        if (book != null) {
            // Dodanie lub usunięcie książki z ulubionych w zależności od flagi "add".
            if (add) {
                favoritesService.addFavourite(book, userProfile);
            } else {
                favoritesService.removeFavourite(book, userProfile);
            }
        }
        // Przekierowanie na stronę z informacjami o konkretnej książce.
        return "redirect:/books/" + id;
    }

    /**
     * Metoda obsługująca żądanie dodania nowego komentarza do książki.
     *
     * @param id      Identyfikator książki.
     * @param content Treść komentarza.
     * @param principal  Obiekt Principal reprezentujący zalogowanego użytkownika.
     * @return Przekierowanie na stronę z informacjami o konkretnej książce.
     */
    @PostMapping("/books/{id}/addComment")
    public String addComment(@PathVariable Long id, @RequestParam String content, Principal principal) {
        // Pobranie informacji o książce o danym identyfikatorze.
        Book book = bookService.getBookById(id);

        // Pobranie informacji o zalogowanym użytkowniku.
        User user = userService.findByEmail(principal.getName())
                .orElseThrow(() -> new RuntimeException("Logged-in user not found"));
        UserProfile userProfile = userProfileRepository.findByUserId(user.getId());

        if (book != null) {
            // Utworzenie nowego obiektu komentarza.
            Comment comment = new Comment();
            comment.setUserProfile(userProfile);
            comment.setBook(book);
            comment.setContent(content);

            // Dodanie komentarza.
            commentService.addComment(comment);
        }
        // Przekierowanie na stronę z informacjami o konkretnej książce.
        return "redirect:/books/" + id;
    }

    /**
     * Metoda obsługująca żądanie odczytu książki.
     *
     * @param id       Identyfikator książki.
     * @param response Obiekt HttpServletResponse do obsługi przekierowania użytkownika do pliku PDF książki.
     * @throws IOException Wyjątek w przypadku problemów z przekierowaniem.
     */
    @GetMapping("/books/{id}/read")
    public void readBook(@PathVariable Long id, HttpServletResponse response) throws IOException {
        // Pobranie informacji o książce o danym identyfikatorze.
        Book book = bookService.getBookById(id);

        if (book != null) {
            // Pobranie linku do pliku PDF książki.
            String downloadLink = book.getPdf();

            if (downloadLink != null) {
                // Przekierowanie użytkownika do pliku PDF.
                response.sendRedirect(downloadLink);
                return;
            }
        }
        // Przekierowanie na stronę z listą książek, jeśli plik PDF nie jest dostępny.
        response.sendRedirect("/books");
    }

    /**
     * Metoda obsługująca żądanie dodania oceny do książki.
     *
     * @param id        Identyfikator książki.
     * @param rating    Ocena książki.
     * @param principal Obiekt Principal reprezentujący zalogowanego użytkownika.
     * @return Ocena książki w postaci tekstu.
     */
    @PostMapping("/books/{id}/addRating")
    @ResponseBody
    public String addRating(@PathVariable Long id, @RequestParam int rating, Principal principal) {
        // Pobranie informacji o książce o danym identyfikatorze.
        Book book = bookService.getBookById(id);

        // Pobranie informacji o zalogowanym użytkowniku.
        User user = userService.findByEmail(principal.getName())
                .orElseThrow(() -> new RuntimeException("Logged-in user not found"));
        UserProfile userProfile = userProfileRepository.findByUserId(user.getId());

        if (book != null) {
            // Pobranie lub utworzenie nowego obiektu oceny.
            Rating rating1 = ratingRepository.findByBookAndUserProfile(book, userProfile);

            if (rating1 != null) {
                // Aktualizacja oceny, jeśli ocena już istnieje.
                rating1.setRating(rating);
            } else {
                // Utworzenie nowego obiektu oceny, jeśli ocena nie istnieje.
                rating1 = new Rating();
                rating1.setUserProfile(userProfile);
                rating1.setBook(book);
                rating1.setRating(rating);
            }

            // Dodanie oceny.
            ratingService.addRating(rating1);
        }
        // Zwrócenie oceny książki w postaci tekstu.
        return "" + rating;
    }

    /**
     * Metoda obsługująca żądanie wyszukiwania książek po kluczowym słowie.
     *
     * @param keyword Słowo kluczowe do wyszukania.
     * @param model   Model do przekazania danych do widoku.
     * @return Nazwa widoku.
     */
    @GetMapping("/search")
    public String searchBooks(@RequestParam(value = "keyword", required = false) String keyword, Model model) {
        // Pobranie wyników wyszukiwania na podstawie kluczowego słowa.
        List<Book> searchResults;

        if (keyword != null && !keyword.isEmpty()) {
            searchResults = bookService.searchByKeyword(keyword);
        } else {
            searchResults = Collections.emptyList();
        }

        // Dodanie wyników wyszukiwania do modelu.
        model.addAttribute("searchResults", searchResults);

        // Zwrócenie nazwy widoku.
        return "search";
    }

    /**
     * Metoda obsługująca żądanie pobrania listy książek wraz z możliwością filtrowania po kategorii.
     *
     * @param category Kategoria do filtrowania.
     * @param model    Model do przekazania danych do widoku.
     * @return Nazwa widoku.
     */
    @GetMapping("/books")
    public String getBooks(@RequestParam(name = "category", required = false) String category, Model model) {
        // Pobranie listy książek wraz z opcjonalnym filtrowaniem po kategorii.
        List<Book> books;

        if (category != null && !category.isEmpty()) {
            books = bookService.getBooksByCategory(category);
            model.addAttribute("category", category);
        } else {
            books = bookService.getBooks();
        }

        // Pobranie unikalnych kategorii.
        List<String> categories = bookService.getCategories();

        // Dodanie listy książek i kategorii do modelu.
        model.addAttribute("books", books);
        model.addAttribute("categories", categories);

        // Zwrócenie nazwy widoku.
        return "datamovies";
    }
}
