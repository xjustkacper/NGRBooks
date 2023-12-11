package com.ngr.ngrbooks.registration.token;

import org.springframework.data.jpa.repository.JpaRepository;



public interface VerificationTokenRepository extends JpaRepository<VerificationToken, Long> {

    VerificationToken findByToken(String token);
}
