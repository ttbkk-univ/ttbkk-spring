package com.ttbkk.api.auth;

import com.ttbkk.api.annotations.auth.IsUser;
import com.ttbkk.api.user.User;
import lombok.extern.slf4j.Slf4j;
import org.apache.tomcat.util.json.ParseException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Slf4j
@RestController
@RequestMapping("/auth")
public class AuthController {
    private final AuthService authService;

    /**
     * 클래스 생성자.
      * @param authService AuthService
     */
    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    /**
     * 인증 기능을 제공하는 provider 로 부터 받은 JWT 를 유저로부터 받아,
     * email 로 조회하고, 일치하는 유저가 없다면 회원가입을 진행합니다.
     * @param request authProviderToken 를 담고있고, 이는 google 등으로부터 받은 유저 식별용 토큰입니다. (ex. JWT)
     * @return String 회원가입 또는 로그인 이후 발급받은 JWT 입니다.
     * @throws ParseException 파싱 실패 시 예외처리.
     */
    @PostMapping("/signin/google")
    public ResponseEntity<AuthDto.SignInResult> signIn(
            @RequestBody @Valid AuthDto.SignInRequest request
    ) throws ParseException {
        String accessToken = this.authService.signIn(request.getAuthProviderToken());
        AuthDto.SignInResult result = new AuthDto.SignInResult(accessToken);
        return ResponseEntity.status(HttpStatus.OK).body(result);
    }

    /**
     * token 을 사용해 유저의 정보를 조회합니다.
     * 우선 간단한 구현을 위해 토큰을 파싱하도록만 되어있지만,
     * 이후 socialId 를 통해 user 전체 entity 를 조회해서 받아올 수 있도록 수정할 예정입니다.
     * @param currentUser IsUser AOP 에서 토큰을 통해 조회되는 유저 정보.
     * @return User 파싱 된 유저 입니다.
     */
    @IsUser
    @GetMapping("/myinfo")
    public ResponseEntity<User> signIn(
        User currentUser
    ) {
        return ResponseEntity.status(HttpStatus.OK).body(currentUser);
    }
}
