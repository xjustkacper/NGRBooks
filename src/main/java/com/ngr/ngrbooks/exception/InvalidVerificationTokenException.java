package com.ngr.ngrbooks.exception;

/**
 * Wyjątek reprezentujący sytuację, gdy token weryfikacyjny jest nieprawidłowy.
 * Rozszerza klasę RuntimeException, co oznacza, że jest to wyjątek niekontrolowany.
 */
public class InvalidVerificationTokenException extends RuntimeException {

    /**
     * Konstruktor klasy InvalidVerificationTokenException.
     *
     * @param message Komunikat opisujący przyczynę nieprawidłowego tokenu weryfikacyjnego.
     */
    public InvalidVerificationTokenException(String message) {
        super(message);
    }
}
