package com.ttbkk.api.auth;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/auth")
public class AuthController {
    private final AuthFacade authFacade;

    @PostMapping("/signin")
    public ResponseEntity<AuthDto.SignResult> signIn(
            @RequestBody @Valid AuthDto.SignInRequest request
    ) {
        AuthDto.SignResult signResult = authFacade.signIn(request);
        return ResponseEntity.status(200).body(signResult);
    }

    @PostMapping("/verify")
    public ResponseEntity<String> verify(
            @RequestBody @Valid AuthDto.VerifyRequest request
    ) {
        String result = authFacade.verify(request) ? "OK" : "NOK";
        return ResponseEntity.status(200).body(result);
    }
}
