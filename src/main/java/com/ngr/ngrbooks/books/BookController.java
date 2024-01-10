package com.ngr.ngrbooks.books;


import com.ngr.ngrbooks.books.comment.Comment;
import com.ngr.ngrbooks.books.comment.CommentService;
import com.ngr.ngrbooks.books.favorites.FavoritesRepository;
import com.ngr.ngrbooks.books.favorites.FavoritesService;
import com.ngr.ngrbooks.books.rating.Rating;
import com.ngr.ngrbooks.books.rating.RatingRepository;
import com.ngr.ngrbooks.books.rating.RatingService;
import com.ngr.ngrbooks.user.User;
import com.ngr.ngrbooks.user.UserService;
import com.ngr.ngrbooks.user.profile.UserProfile;
import com.ngr.ngrbooks.user.profile.UserProfileController;
import com.ngr.ngrbooks.user.profile.UserProfileRepository;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

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

    @GetMapping("/books/{id}")
    public String getBookById(@PathVariable Long id, Model model, Principal principal){
        Book book = bookService.getBookById(id);
        User user = userService.findByEmail(principal.getName())
                .orElseThrow(() -> new RuntimeException("Logged-in user not found"));
        UserProfile userProfile = userProfileRepository.findByUserId(user.getId());
        if(book != null){
            model.addAttribute("book", book);
            List<Comment> comments = commentService.getCommentsByBookId(id);
            Rating rating = ratingRepository.findByBookAndUserProfile(book, userProfile);
            model.addAttribute("rating", rating);
            model.addAttribute("comments", comments);
            model.addAttribute("comment", new Comment());
            boolean isFavourite = favoritesService.isFavourite(book, userProfile);
            model.addAttribute("isFavourite", isFavourite);
            return "bookpage";
        }
        else {
            return "redirect:/books";
        }
    }

    @PostMapping("/books/{id}/favourite")
    public String handleFavourite(@PathVariable Long id, @RequestParam boolean add, Principal principal){
        Book book = bookService.getBookById(id);
        User user = userService.findByEmail(principal.getName())
                .orElseThrow(() -> new RuntimeException("Logged-in user not found"));
        UserProfile userProfile = userProfileRepository.findByUserId(user.getId());
        if(book != null){
            if(add){
                favoritesService.addFavourite(book, userProfile);
            } else {
                favoritesService.removeFavourite(book, userProfile);
            }
        }
        return "redirect:/books/" + id;
    }


    @PostMapping("/books/{id}/addComment")
    public String addComment(@PathVariable Long id,@RequestParam String content, Principal principal){

        Book book = bookService.getBookById(id);
        User user = userService.findByEmail(principal.getName())
                .orElseThrow(() -> new RuntimeException("Logged-in user not found"));
        UserProfile userProfile = userProfileRepository.findByUserId(user.getId());
        if(book != null){
            Comment comment = new Comment();
            comment.setUserProfile(userProfile);
            comment.setBook(book);
            comment.setContent(content);
            commentService.addComment(comment);
        }
        return "redirect:/books/" + id;
    }

    @PostMapping("/books/{id}/addRating")
    @ResponseBody
    public String addRating(@PathVariable Long id, @RequestParam int rating, Principal principal){

        Book book = bookService.getBookById(id);
        User user = userService.findByEmail(principal.getName())
                .orElseThrow(() -> new RuntimeException("Logged-in user not found"));
        UserProfile userProfile = userProfileRepository.findByUserId(user.getId());
        if(book != null){
            Rating rating1 = ratingRepository.findByBookAndUserProfile(book, userProfile);
            if (rating1 != null) {
                rating1.setRating(rating);
            } else {
                rating1 = new Rating();
                rating1.setUserProfile(userProfile);
                rating1.setBook(book);
                rating1.setRating(rating);
            }
            ratingService.addRating(rating1);
        }
        return "" + rating;
    }

    @GetMapping("/search")
    public String searchBooks(@RequestParam(value = "keyword", required = false) String keyword, Model model) {
        List<Book> searchResults;
        if (keyword != null && !keyword.isEmpty()) {
            searchResults = bookService.searchByKeyword(keyword); // Szukaj książek po kluczowym słowie
        } else {
            searchResults = Collections.emptyList();
        }

        model.addAttribute("searchResults", searchResults); // Przekazanie wyników wyszukiwania do widoku
        return "search";
    }

@GetMapping("/books")
    public String getBooks(@RequestParam(name = "category", required = false)String category, Model model) {
        List<Book> books;

        if(category != null && !category.isEmpty()) {
            books = bookService.getBooksByCategory(category);
            model.addAttribute("category", category);
        }
        else {
            books = bookService.getBooks();
        }
        List<String> categories = bookService.getCategories();
        model.addAttribute("books", books);
        model.addAttribute("categories", categories);
        return "datamovies";
    }




}
