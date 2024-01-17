package com.ngr.ngrbooks.exception;


import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.Map;

/**
 * Klasa obsługująca wyjątki związane z procesem rejestracji użytkowników.
 * Jest to komponent Spring, oznaczony adnotacją @RestControllerAdvice, który obsługuje wyjątki na poziomie globalnym.
 */
@RestControllerAdvice
public class RegistrationExceptionHandler {

    /**
     * Obsługuje wyjątki związane z błędnymi argumentami przekazanymi do metod kontrolerów.
     *
     * @param ex Wyjątek reprezentujący błąd w przekazanych argumentach.
     * @return Mapa z informacjami o błędach.
     */
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public Map<String, String> handleException(MethodArgumentNotValidException ex) {
        Map<String, String> errors = new HashMap<>();
        ex.getBindingResult()
                .getFieldErrors()
                .forEach(error -> errors.put(error.getField(), error.getDefaultMessage()));
        return errors;
    }

    /**
     * Obsługuje wyjątek, gdy użytkownik o podanej nazwie już istnieje.
     *
     * @param ex Wyjątek reprezentujący konflikt związany z istnieniem użytkownika.
     * @return Mapa z informacją o błędzie.
     */
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(UserAlreadyExistsException.class)
    public Map<String, String> userNotFound(UserAlreadyExistsException ex) {
        Map<String, String> error = new HashMap<>();
        error.put("error", ex.getMessage());
        return error;
    }

    /**
     * Obsługuje wyjątek, gdy token weryfikacyjny jest nieprawidłowy.
     *
     * @param ex Wyjątek reprezentujący błąd związany z nieprawidłowym tokenem weryfikacyjnym.
     * @return Mapa z informacją o błędzie.
     */
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(InvalidVerificationTokenException.class)
    public Map<String, String> tokenNotFound(InvalidVerificationTokenException ex) {
        Map<String, String> error = new HashMap<>();
        error.put("error", ex.getMessage());
        return error;
    }
}
