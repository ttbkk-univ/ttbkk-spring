package com.ttbkk.api.annotations.auth;

import com.ttbkk.api.auth.JWTService;
//import com.ttbkk.api.common.exception.UnauthorizedException;
import com.ttbkk.api.user.User;
import com.ttbkk.api.user.UserRole;
import com.ttbkk.api.user.UserService;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.Arrays;


@Aspect
@Component
public class IsAdminAspect extends BaseAuthCheckAspect {
    /**
     * 클래스 생성자.
     *
     * @param httpServletRequest header.authorization으로 전달되는 JWT를 받아오기 위함.
     * @param jwtService         JWT를 파싱하기 위함.
     * @param userService        JWT로부터 얻은 socialId로 유저의 정보를 조회하기 위함.
     */
    public IsAdminAspect(HttpServletRequest httpServletRequest, JWTService jwtService, UserService userService) {
        super(httpServletRequest, jwtService, userService);
    }

    /**
     * Annotation의 실행 파트.
     *
     * @param joinPoint annotation이 감싸고 있는 함수이다. 해당 함수의 실행 전후로 부가 작업을 실행해주는 것이 해당 annotation이다.
     * @return Object joinPoint의 실행 결과.
     * @throws Throwable 에러 예외 사항 표시
     */
    @Around("@annotation(com.ttbkk.api.annotations.auth.IsAdmin)") // 어노테이션과 Aspect 연결
    public Object check(final ProceedingJoinPoint joinPoint) throws Throwable {
        User requestUser = this.getUser();
        ArrayList<String> targets = new ArrayList<>(Arrays.asList(
            UserRole.ADMIN.toString(),
            UserRole.SUPER_ADMIN.toString()
        ));
        if (!targets.contains(requestUser.getRole().toString())) {
//            throw new UnauthorizedException();
        }
        return joinPoint.proceed(new Object[]{requestUser});
    }
}
