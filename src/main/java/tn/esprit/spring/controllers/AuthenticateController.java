package tn.esprit.spring.controllers;

import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tn.esprit.spring.configuration.AuthenticationRequest;
import tn.esprit.spring.configuration.AuthenticationResponse;
import tn.esprit.spring.configuration.RegisterRequest;
import tn.esprit.spring.services.AthenticationService;


@RestController
@AllArgsConstructor
@CrossOrigin(origins = "*")
@RequestMapping("/api/v1/auth")

public class AuthenticateController {

    AthenticationService athenticationService ;

    @PostMapping("/register")
    public ResponseEntity<AuthenticationResponse> register(
            @RequestBody RegisterRequest request
    ) {
        return ResponseEntity.ok(athenticationService.register(request));
    }
    @PostMapping("/authenticate")
    public ResponseEntity<AuthenticationResponse> authenticate(
            @RequestBody AuthenticationRequest request
    ) {
        return ResponseEntity.ok(athenticationService.authenticate(request));
    }
}
