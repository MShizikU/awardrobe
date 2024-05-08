package ru.mirea.ikbo2021.sidorov.awardrobe.controller.auth;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.mirea.ikbo2021.sidorov.awardrobe.domain.dto.auth.JwtAuthenticationResponse;
import ru.mirea.ikbo2021.sidorov.awardrobe.domain.dto.auth.SignInRequest;
import ru.mirea.ikbo2021.sidorov.awardrobe.domain.dto.auth.SignUpRequest;
import ru.mirea.ikbo2021.sidorov.awardrobe.service.security.interfaces.AuthenticationService;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
@Tag(name = "Аутентификация")
public class AuthController{
    private final AuthenticationService authenticationService;

    /**
     * Регистрация пользователя
     */
    @Operation(summary = "Регистрация пользователя")
    @PostMapping("/sign-up")
    public JwtAuthenticationResponse signUp(@RequestBody @Valid SignUpRequest request) {
        return authenticationService.signUp(request);
    }

    /**
     * Авторизация пользователя
     */
    @Operation(summary = "Авторизация пользователя")
    @PostMapping("/sign-in")
    public JwtAuthenticationResponse signIn(@RequestBody @Valid SignInRequest request) {
        return authenticationService.signIn(request);
    }

}
