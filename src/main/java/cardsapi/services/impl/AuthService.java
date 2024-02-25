package cardsapi.services.impl;

import cardsapi.auth.JwtService;
import cardsapi.dtos.LoginDto;
import cardsapi.models.User;
import cardsapi.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthService {
    private final AuthenticationManager authenticationManager;
    private final JwtService jwtService;
    private final UserRepository userRepository;

    public String signIn(LoginDto loginDto) {
        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
                        loginDto.email(),
                        loginDto.password()));

        User user = userRepository.findByEmailAndPassword(loginDto.email(), loginDto.password())
                .orElseThrow(() -> new IllegalArgumentException("Invalid email or password"));

        return jwtService.generateToken(user);
    }
}
