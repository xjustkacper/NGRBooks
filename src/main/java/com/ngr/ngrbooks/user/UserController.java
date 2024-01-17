package com.ngr.ngrbooks.user;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * Kontroler obsługujący operacje związane z użytkownikami w systemie.
 */
@RestController
@RequiredArgsConstructor
@RequestMapping("/users")
public class UserController {

    private final UserService userService;  // Serwis obsługujący operacje na użytkownikach.

    /**
     * Obsługuje żądanie pobrania listy wszystkich użytkowników.
     *
     * @return Lista wszystkich użytkowników w systemie.
     */
    @GetMapping
    public List<User> getUsers() {
        return userService.getUsers();
    }
}