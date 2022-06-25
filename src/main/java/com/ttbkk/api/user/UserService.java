package com.ttbkk.api.user;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class UserService {
    public User findByEmail(String email) {
        // db 구축 이후 연동
        return null;
    }
}
