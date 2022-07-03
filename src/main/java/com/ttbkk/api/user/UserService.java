package com.ttbkk.api.user;

import com.querydsl.jpa.impl.JPAQuery;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import java.util.Optional;

@Slf4j
@Service
@RequiredArgsConstructor
public class UserService {

    /**
     * entity manager 입니다.
     */
    @Autowired
    private EntityManager entityManager;

    /**
     * 입력된 socialId를 가지는 유저가 존재하는지 조회합니다.
     * @param socialId 구글의 경우 email 에 해당됩니다.
     * @return Optional<User> 유저를 찾지 못한 경우 null 이 반환됩니다.
     */
    public Optional<User> findBySocialId(String socialId) {
        JPAQuery<User> query = new JPAQuery<>(this.entityManager);
        QUser qUser = new QUser("user");
        return Optional.ofNullable(
            query
                .from(qUser)
                .where(
                    qUser.socialId.eq(socialId)
                )
                .fetchOne()
        );
    }

    /**
     * 유저를 생성합니다.
     * @param socialId 구글 로그인 시 email 에 해당됩니다.
     * @param nickname 구글 로그인 시 name 에 해당됩니다.
     * @param socialType 구글 로그인 시 GOOGLE 에 해당됩니다.
     * @return User 생성된 유저가 반환됩니다.
     */
    public User create(String socialId, String nickname, String socialType) {
        User user = User.builder()
            .socialId(socialId)
            .nickname(nickname)
            .socialType(socialType)
            .build();
        this.entityManager.persist(user);
        return user;
    }
}
