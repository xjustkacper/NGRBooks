package com.ngr.ngrbooks.books.favorites;

import com.ngr.ngrbooks.books.Book;
import com.ngr.ngrbooks.user.profile.UserProfile;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface FavoritesRepository extends JpaRepository<Favorites, Long> {
    Favorites findByBookAndUser(Book book, UserProfile userProfile);

    @Query("SELECT f.book FROM Favorites f WHERE f.user = ?1")
    List<Book> findAllByUser(UserProfile userProfile);
}
