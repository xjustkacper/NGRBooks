package com.ngr.ngrbooks.books.rating;

import com.ngr.ngrbooks.books.Book;
import com.ngr.ngrbooks.user.profile.UserProfile;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class RatingService {
    private final RatingRepository ratingRepository;

    public void addRating(Rating rating1) {
        ratingRepository.save(rating1);

    }


    public List<Rating> getRatings(UserProfile userProfile) {
        return ratingRepository.findByUserProfile(userProfile);
    }

    public double getAverageRating() {
        List<Rating> allRatings = ratingRepository.findAll();
        return allRatings.stream().mapToInt(Rating::getRating).average().orElse(0.0);
    }

    public List<Rating> getTop10BooksByRating() {
        return ratingRepository.findTop10ByOrderByRatingDesc();
    }

    public List<Rating> getAverageRatingPerBook() {
        return ratingRepository.findAverageRatingPerBook();
    }


}
