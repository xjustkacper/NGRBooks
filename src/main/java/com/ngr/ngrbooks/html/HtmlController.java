package com.ngr.ngrbooks.html;

import com.ngr.ngrbooks.books.Book;
import com.ngr.ngrbooks.books.rating.Rating;
import com.ngr.ngrbooks.books.rating.RatingService;
import com.ngr.ngrbooks.registration.password.PasswordRequestUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;


/**
 * Kontroler obsługujący żądania dotyczące stron HTML.
 */
@Controller
@RequiredArgsConstructor
public class HtmlController {
    private final RatingService ratingService;

    /**
     * Obsługuje żądanie dla głównej strony oraz strony logowania.
     *
     * @param model Model Spring, umożliwia przekazywanie danych do widoku.
     * @return Nazwa widoku, który ma zostać wyrenderowany.
     */
    @GetMapping(value = {"/", "/login.html"})
    public String index(Model model) {
        List<Book> top10Books = ratingService.getTop10Books();
        model.addAttribute("top10Books", top10Books);
        return "index";
    }

    /**
     * Obsługuje żądanie dla strony rejestracji.
     *
     * @return Nazwa widoku strony rejestracji.
     */
    @GetMapping("/register")
    public String register() {
        return "register";
    }

    /**
     * Obsługuje żądanie dla strony logowania.
     *
     * @return Nazwa widoku strony logowania.
     */
    @GetMapping("/login")
    public String login() {
        return "login";
    }
}
