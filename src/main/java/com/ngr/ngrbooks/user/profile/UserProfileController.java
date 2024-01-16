package com.ngr.ngrbooks.user.profile;

import com.lowagie.text.DocumentException;
import com.ngr.ngrbooks.books.Book;
import com.ngr.ngrbooks.books.BookService;
import com.ngr.ngrbooks.books.favorites.FavoritesService;
import com.ngr.ngrbooks.books.rating.Rating;
import com.ngr.ngrbooks.books.rating.RatingRepository;
import com.ngr.ngrbooks.books.rating.RatingService;
import com.ngr.ngrbooks.user.User;
import com.ngr.ngrbooks.user.UserService;
import com.ngr.ngrbooks.user.profile.pdf.PdfExporter;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;


import java.io.IOException;
import java.security.Principal;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Controller
@RequiredArgsConstructor
public class UserProfileController {
    private final UserProfileRepository userProfileRepository;
    private final UserService userService;
    private final FavoritesService favoritesService;
    private final RatingRepository ratingRepository;
    private final RatingService ratingService;
    private final BookService bookService;


    @GetMapping("/profile")
    public String getUserProfile(Model model, Principal principal){
        addProfileToModel(model, principal);
        User user = userService.findByEmail(principal.getName())
                .orElseThrow(() -> new RuntimeException("Logged-in user not found"));
        UserProfile userProfile = userProfileRepository.findByUserId(user.getId());

        List<Book> books = bookService.getBooks();
        List<Rating> ratings = ratingService.getRatings(userProfile);

        model.addAttribute("books", books);
        model.addAttribute("ratings", ratings);
        model.addAttribute("userProfile", userProfile);
        model.addAttribute("favoritesService", favoritesService);
        model.addAttribute("ratingRepository", ratingRepository);

        return "profile";
    }



    @GetMapping("/edit")
    public String getUserProfileEdit(Model model, Principal principal){
        addProfileToModel(model, principal);
        return "edit";
    }



    @PostMapping("/edit")
    public String editUserProfile( @ModelAttribute UserProfile formUserProfile, Model model, Principal principal){
        Optional<User> userOptional = userService.findByEmail(principal.getName());
        if (userOptional.isPresent()) {
            User user = userOptional.get();

            UserProfile userProfile = userProfileRepository.findByUserId(user.getId());

            userProfile.setDescription(formUserProfile.getDescription());
            userProfile.setAvatar(formUserProfile.getAvatar());
            userProfileRepository.save(userProfile);

            model.addAttribute("nickname", user.getNickname());
            model.addAttribute("userProfile", userProfile);
        }
        return "redirect:/profile";
    }


    @GetMapping("/profile/pdf")
    public void exportToPdf(HttpServletResponse response, Principal principal) throws DocumentException, IOException {
        response.setContentType("application/pdf");

        DateFormat dateFormatter = new SimpleDateFormat("yyyy-MM-dd_HH:mm:ss");
        String currentDateTime = dateFormatter.format(new Date());

        String headerKey = "Content-Disposition";
        String headerValue = "attachment; filename=profile_" + currentDateTime + ".pdf";
        response.setHeader(headerKey, headerValue);

        Optional<User> userOptional = userService.findByEmail(principal.getName());
        if (userOptional.isPresent()) {
            User user = userOptional.get();
            UserProfile userProfile = userProfileRepository.findByUserId(user.getId());

            List<Book> books = favoritesService.getFavorites(userProfile);


            PdfExporter exporter = new PdfExporter(books);

            exporter.export(response);
        }
    }

    private void addProfileToModel(Model model, Principal principal) {
        Optional<User> userOptional = userService.findByEmail(principal.getName());
        if (userOptional.isPresent()) {
            User user = userOptional.get();
            UserProfile userProfile = userProfileRepository.findByUserId(user.getId());

            model.addAttribute("nickname", user.getNickname());
            model.addAttribute("userProfile", userProfile);
        }
    }

}
