package com.ngr.ngrbooks.books.favorites;

import com.ngr.ngrbooks.books.Book;
import com.ngr.ngrbooks.user.profile.UserProfile;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class FavoritesService {

    private final FavoritesRepository favoritesRepository;



    public void addFavourite(Book book, UserProfile userProfile) {
        Favorites favorites = new Favorites();
        favorites.setBook(book);
        favorites.setUser(userProfile);
        favoritesRepository.save(favorites);
    }

    public void removeFavourite(Book book, UserProfile userProfile) {
        Favorites favorites = favoritesRepository.findByBookAndUser(book, userProfile);
        favoritesRepository.delete(favorites);
    }

    public boolean isFavourite(Book book, UserProfile userProfile) {
Favorites favorites = favoritesRepository.findByBookAndUser(book, userProfile);
        return favorites != null;

    }
}
