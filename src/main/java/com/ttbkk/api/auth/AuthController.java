package com.ttbkk.api.auth;

import com.ttbkk.api.user.User;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.tomcat.util.json.ParseException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/auth")
public class AuthController {
    private final AuthService authService;

    @PostMapping("/signin/google")
    public ResponseEntity<AuthDto.SignInResult> signIn(
            @RequestBody @Valid AuthDto.SignInRequest request
    ) throws ParseException {
        AuthDto.SignInResult signInResult = authService.signIn(request);
        return ResponseEntity.status(200).body(signInResult);
    }

    @GetMapping("/myinfo")
    public ResponseEntity<User> signIn(
            @RequestHeader("Authorization") String authorization
    ) throws ParseException {
        String token = authorization.split(" ")[1];
        User user = authService.myInfo(token);
        return ResponseEntity.status(200).body(user);
    }
}
