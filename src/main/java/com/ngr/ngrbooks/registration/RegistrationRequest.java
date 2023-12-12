package com.ngr.ngrbooks.registration;

import org.hibernate.annotations.NaturalId;

public record RegistrationRequest(
        String nickname,
        String email,
        String password,
        String role) {
}
