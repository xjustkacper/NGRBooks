package com.ngr.ngrbooks.exception;

/**
 * Wyjątek rzucany, gdy istniejący użytkownik próbuje zostać zarejestrowany ponownie.
 */
public class UserAlreadyExistsException extends RuntimeException {

    /**
     * Konstruktor klasy, przyjmujący komunikat jako parametr.
     *
     * @param message Komunikat opisujący wyjątek.
     */
    public UserAlreadyExistsException(String message) {
        super(message);
    }
}
