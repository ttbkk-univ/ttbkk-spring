package com.ttbkk.api.user;

import com.ttbkk.api.common.exception.UnauthorizedException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Optional;

@Slf4j
@Service
@RequiredArgsConstructor
public class UserService {
    private final ArrayList<User> db;

    public UserService() {
        this.db = new ArrayList<User>();
        this.db.add(new User("siner308"));
        this.db.add(new User("maddercolor"));
        this.db.add(new User("Albegam"));
        this.db.add(new User("honorrra"));
        this.db.add(new User("Haze-S"));
        this.db.add(new User("yuoa"));
        this.db.add(new User("JIWEON-JEONG"));
    }

    public User findById(String id) {
        Optional<User> user = this.db.stream().filter(row -> row.id.equals(id)).findFirst();
        return user.orElseThrow(UnauthorizedException::new);
    }
}
