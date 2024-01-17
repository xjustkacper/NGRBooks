package com.ngr.ngrbooks.books.rating;

import com.ngr.ngrbooks.books.Book;
import com.ngr.ngrbooks.user.profile.UserProfile;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Klasa RatingService zapewnia logikę biznesową związaną z operacjami na ocenach książek.
 * Jest oznaczona adnotacją @Service, co informuje Spring, że ta klasa jest serwisem.
 * Adnotacja @RequiredArgsConstructor generuje konstruktor zależności na podstawie pól oznaczonych adnotacją @Autowired.
 */
@Service
@RequiredArgsConstructor
public class RatingService {

    /**
     * Repozytorium ocen, używane do operacji na bazie danych.
     */
    private final RatingRepository ratingRepository;

    /**
     * Metoda dodająca nową ocenę książki do bazy danych.
     *
     * @param rating1 Nowa ocena do dodania.
     */
    public void addRating(Rating rating1) {
        ratingRepository.save(rating1);
    }

    /**
     * Metoda zwracająca listę ocen przypisanych do książek przez danego użytkownika.
     *
     * @param userProfile Profil użytkownika, dla którego pobierane są oceny.
     * @return Lista ocen przypisanych do książek przez danego użytkownika.
     */
    public List<Rating> getRatings(UserProfile userProfile) {
        return ratingRepository.findByUserProfile(userProfile);
    }

    /**
     * Metoda zwracająca listę 10 najlepiej ocenianych książek.
     *
     * @return Lista 10 najlepiej ocenianych książek.
     */
    public List<Book> getTop10Books() {
        return ratingRepository.findTop10Books(PageRequest.of(0, 10));
    }
}

