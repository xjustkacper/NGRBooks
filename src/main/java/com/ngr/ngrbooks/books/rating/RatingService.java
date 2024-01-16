package com.ngr.ngrbooks.books.rating;

import com.ngr.ngrbooks.books.Book;
import com.ngr.ngrbooks.user.profile.UserProfile;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
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


    public List<Book> getTop10Books() {
        return ratingRepository.findTop10Books(PageRequest.of(0, 10));
    }

}
