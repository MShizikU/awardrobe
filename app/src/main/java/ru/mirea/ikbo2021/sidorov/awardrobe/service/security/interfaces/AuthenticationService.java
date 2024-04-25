package ru.mirea.ikbo2021.sidorov.awardrobe.service.security.interfaces;

import ru.mirea.ikbo2021.sidorov.awardrobe.dto.auth.JwtAuthenticationResponse;
import ru.mirea.ikbo2021.sidorov.awardrobe.dto.auth.SignInRequest;
import ru.mirea.ikbo2021.sidorov.awardrobe.dto.auth.SignUpRequest;

public interface AuthenticationService {
    JwtAuthenticationResponse signUp(SignUpRequest request);

    JwtAuthenticationResponse signIn(SignInRequest request);
}
