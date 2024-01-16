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

@Controller
@RequiredArgsConstructor
public class HtmlController {
    private final RatingService ratingService;



    @GetMapping(value = {"/", "/login.html"})
    public String index(Model model){
        List<Book> top10Books = ratingService.getTop10Books();
        model.addAttribute("top10Books", top10Books);
        return "index";
    }

    @GetMapping("/register")
    public String register(){
        return "register";
    }

    @GetMapping("/login")
    public String login(){
        return "login";
    }




}
