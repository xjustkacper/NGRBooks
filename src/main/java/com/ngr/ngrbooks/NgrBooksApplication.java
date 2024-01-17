package com.ngr.ngrbooks;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;



/**
 * Główna klasa uruchomieniowa Spring Boot dla aplikacji NgrBooks.
 */
@SpringBootApplication
public class NgrBooksApplication {

    /**
     * Metoda główna, uruchamiająca aplikację Spring Boot.
     *
     * @param args Argumenty wiersza poleceń.
     */
    public static void main(String[] args) {
        SpringApplication.run(NgrBooksApplication.class, args);
    }
}
