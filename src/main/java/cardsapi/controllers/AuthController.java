package cardsapi.controllers;

import cardsapi.dtos.LoginDto;
import cardsapi.services.impl.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class AuthController {

private final AuthService authService;

    @PostMapping("api/login")
    public ResponseEntity<String> login(@RequestBody LoginDto request) {
        return ResponseEntity.ok(authService.signIn(request));
    }
}
