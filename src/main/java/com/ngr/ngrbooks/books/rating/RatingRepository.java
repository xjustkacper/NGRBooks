package com.ngr.ngrbooks.books.rating;

import com.ngr.ngrbooks.books.Book;
import com.ngr.ngrbooks.user.profile.UserProfile;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface RatingRepository extends JpaRepository<Rating, Long> {


    Rating findByBookAndUserProfile(Book book, UserProfile userProfile);


    List<Rating> findByUserProfile(UserProfile userProfile);

    List<Rating> findTop10ByOrderByRatingDesc();

    @Query("SELECT (r.book.id, AVG(r.rating)) FROM Rating r GROUP BY r.book.id")
    List<Rating> findAverageRatingPerBook();
}
