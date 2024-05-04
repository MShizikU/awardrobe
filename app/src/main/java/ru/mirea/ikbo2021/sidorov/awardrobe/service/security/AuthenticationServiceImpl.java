package ru.mirea.ikbo2021.sidorov.awardrobe.service.security;

import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import ru.mirea.ikbo2021.sidorov.awardrobe.dto.auth.JwtAuthenticationResponse;
import ru.mirea.ikbo2021.sidorov.awardrobe.dto.auth.SignInRequest;
import ru.mirea.ikbo2021.sidorov.awardrobe.dto.auth.SignUpRequest;
import ru.mirea.ikbo2021.sidorov.awardrobe.exception.user.InvalidUserDataProblem;
import ru.mirea.ikbo2021.sidorov.awardrobe.model.User;
import ru.mirea.ikbo2021.sidorov.awardrobe.model.security.EmailPasswordAuthenticationToken;
import ru.mirea.ikbo2021.sidorov.awardrobe.repository.UserRepository;
import ru.mirea.ikbo2021.sidorov.awardrobe.repository.UserRoleRepository;
import ru.mirea.ikbo2021.sidorov.awardrobe.service.UserService;
import ru.mirea.ikbo2021.sidorov.awardrobe.service.security.interfaces.AuthenticationService;
import ru.mirea.ikbo2021.sidorov.awardrobe.service.security.interfaces.JwtService;

@Service
@RequiredArgsConstructor
public class AuthenticationServiceImpl implements AuthenticationService {
    private final UserRepository userRepository;
    private final UserRoleRepository roleRepository;
    private final UserService userService;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;

    @Override
    public JwtAuthenticationResponse signUp(SignUpRequest request) {
        var relatedRole = roleRepository.findByName(request.role());
        var userRole = relatedRole.isEmpty() ? roleRepository.findByName("USER").get() : relatedRole.get();

        var user = User.builder()
                .email(request.email())
                .password(passwordEncoder.encode(request.password()))
                .role(userRole)
                .build();

        userService.create(user);

        var jwt = jwtService.generateToken(user);
        return new JwtAuthenticationResponse(jwt);
    }

    @Override
    public JwtAuthenticationResponse signIn(SignInRequest request) {
        authenticationManager.authenticate(new EmailPasswordAuthenticationToken(
                request.username(),
                request.password()
        ));

        var user = userRepository
                .findByUsername(request.username())
                .orElseThrow(InvalidUserDataProblem::new);

        var jwt = jwtService.generateToken(user);
        return new JwtAuthenticationResponse(jwt);
    }
}
