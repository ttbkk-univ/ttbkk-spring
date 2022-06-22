package com.ttbkk.api.auth;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
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
    public AuthDto.SignResult signIn(
            @RequestBody @Valid AuthDto.SignInRequest request
    ) {
        AuthDto.SignResult signResult = authFacade.signIn(request);
        System.out.println(signResult);
        return signResult;
    }

    @PostMapping("/verify")
    public String verify(
            @RequestBody @Valid AuthDto.VerifyRequest request
    ) {
        if (authFacade.verify(request)) {
            return "OK";
        } else {
            return "NOK";
        }
    }
}
