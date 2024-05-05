package ru.mirea.ikbo2021.sidorov.awardrobe.service.security;

import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import ru.mirea.ikbo2021.sidorov.awardrobe.domain.dto.auth.JwtAuthenticationResponse;
import ru.mirea.ikbo2021.sidorov.awardrobe.domain.dto.auth.SignInRequest;
import ru.mirea.ikbo2021.sidorov.awardrobe.domain.dto.auth.SignUpRequest;
import ru.mirea.ikbo2021.sidorov.awardrobe.exception.user.InvalidUserDataProblem;
import ru.mirea.ikbo2021.sidorov.awardrobe.domain.model.User;
import ru.mirea.ikbo2021.sidorov.awardrobe.domain.model.security.EmailPasswordAuthenticationToken;
import ru.mirea.ikbo2021.sidorov.awardrobe.repository.UserRepository;
import ru.mirea.ikbo2021.sidorov.awardrobe.repository.UserRoleRepository;
import ru.mirea.ikbo2021.sidorov.awardrobe.service.UserService;
import ru.mirea.ikbo2021.sidorov.awardrobe.service.security.interfaces.AuthenticationService;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

@Service
@RequiredArgsConstructor
public class AuthenticationServiceImpl implements AuthenticationService {
    private final UserRepository userRepository;
    private final UserRoleRepository roleRepository;
    private final UserService userService;
    private final PasswordEncoder passwordEncoder;
    private final JwtServiceImpl jwtService;
    private final AuthenticationManager authenticationManager;

    @Override
    public JwtAuthenticationResponse signUp(SignUpRequest request) {
        var relatedRole = roleRepository.findByName(request.role());
        var userRole = relatedRole.isEmpty() ? roleRepository.findByName("USER").get() : relatedRole.get();
        MessageDigest digest = null;
        try {
            digest = MessageDigest.getInstance("SHA-256");
        } catch (NoSuchAlgorithmException ignored) {

        }
        byte[] hashedBytes = digest.digest((request.username() + " " + request.email()).getBytes());

        StringBuilder hexString = new StringBuilder();
        for (byte b : hashedBytes) {
            String hex = Integer.toHexString(0xff & b);
            if (hex.length() == 1) {
                hexString.append('0');
            }
            hexString.append(hex);
        }

        String hash = hexString.toString();
        var user = User.builder()
                .username(request.username())
                .email(request.email())
                .password(passwordEncoder.encode(request.password()))
                .role(userRole)
                .isDisposable(false)
                .status("active")
                .hashcode(hash)
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
