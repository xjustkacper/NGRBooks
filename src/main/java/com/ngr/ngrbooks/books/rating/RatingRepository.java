package com.ngr.ngrbooks.books.rating;

import com.ngr.ngrbooks.books.Book;
import com.ngr.ngrbooks.user.profile.UserProfile;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface RatingRepository extends JpaRepository<Rating, Long> {


    Rating findByBookAndUserProfile(Book book, UserProfile userProfile);


    List<Rating> findByUserProfile(UserProfile userProfile);

    @Query("SELECT r.book FROM Rating r GROUP BY r.book ORDER BY AVG(r.rating) DESC")
    List<Book> findTop10Books(Pageable pageable);




}
