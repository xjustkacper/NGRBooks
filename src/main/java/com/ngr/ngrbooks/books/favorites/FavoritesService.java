package com.ngr.ngrbooks.books.favorites;

import com.ngr.ngrbooks.books.Book;
import com.ngr.ngrbooks.user.profile.UserProfile;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Klasa FavoritesService zapewnia logikę biznesową związaną z operacjami na ulubionych książkach użytkownika.
 * Jest oznaczona adnotacją @Service, co informuje Spring, że ta klasa jest serwisem.
 * Adnotacja @RequiredArgsConstructor generuje konstruktor zależności na podstawie pól oznaczonych adnotacją @Autowired.
 */
@Service
@RequiredArgsConstructor
public class FavoritesService {

    /**
     * Repozytorium ulubionych, używane do operacji na bazie danych.
     */
    private final FavoritesRepository favoritesRepository;

    /**
     * Metoda dodająca książkę do ulubionych dla danego użytkownika.
     *
     * @param book        Książka, która ma zostać dodana do ulubionych.
     * @param userProfile Profil użytkownika, dla którego książka ma zostać dodana do ulubionych.
     */
    public void addFavourite(Book book, UserProfile userProfile) {
        Favorites favorites = new Favorites();
        favorites.setBook(book);
        favorites.setUser(userProfile);
        favoritesRepository.save(favorites);
    }

    /**
     * Metoda usuwająca książkę z ulubionych danego użytkownika.
     *
     * @param book        Książka, która ma zostać usunięta z ulubionych.
     * @param userProfile Profil użytkownika, dla którego książka ma zostać usunięta z ulubionych.
     */
    public void removeFavourite(Book book, UserProfile userProfile) {
        Favorites favorites = favoritesRepository.findByBookAndUser(book, userProfile);
        if (favorites != null) {
            favoritesRepository.delete(favorites);
        }
    }

    /**
     * Metoda sprawdzająca, czy dana książka jest ulubioną dla danego użytkownika.
     *
     * @param book        Książka, dla której sprawdzana jest ulubioność.
     * @param userProfile Profil użytkownika, dla którego sprawdzana jest ulubioność.
     * @return true, jeśli książka jest ulubioną; false, w przeciwnym razie.
     */
    public boolean isFavourite(Book book, UserProfile userProfile) {
        Favorites favorites = favoritesRepository.findByBookAndUser(book, userProfile);
        return favorites != null;
    }

    /**
     * Metoda zwracająca listę ulubionych książek danego użytkownika.
     *
     * @param userProfile Profil użytkownika, dla którego pobierane są ulubione książki.
     * @return Lista ulubionych książek danego użytkownika.
     */
    public List<Book> getFavorites(UserProfile userProfile) {
        return favoritesRepository.findAllByUser(userProfile);
    }
}
