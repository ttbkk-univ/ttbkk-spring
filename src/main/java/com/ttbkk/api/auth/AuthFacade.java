package com.ttbkk.api.auth;

import com.ttbkk.api.user.User;
import com.ttbkk.api.user.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class AuthFacade {
    private final JWTService jwtService;
    private final UserService userService;

    public AuthDto.SignResult signIn(AuthDto.SignInRequest request) {
        User user = userService.findById(request.id);
        String accessToken = jwtService.encode(user);
        return new AuthDto.SignResult(accessToken);
    }

    public boolean verify(AuthDto.VerifyRequest request) {
        try {
            User user = jwtService.parse(request.accessToken);
            userService.findById(user.id);
            return true;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return false;
        }
    }
}
