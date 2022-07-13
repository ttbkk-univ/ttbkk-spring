package com.ttbkk.api.user;

import com.querydsl.jpa.impl.JPAQuery;
import javassist.NotFoundException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import java.util.Optional;

@Slf4j
@Service
@Transactional
public class UserService {

    private final EntityManager entityManager;

    private final QUser qUser;

    private final JPAQuery<User> query;

    /**
     * 클래스 생성자.
     *
     * @param entityManager EntityManager
     */
    public UserService(EntityManager entityManager) {
        this.entityManager = entityManager;
        this.query = new JPAQuery<>(this.entityManager);
        this.qUser = QUser.user;
    }

    /**
     * 입력된 socialId를 가지는 유저가 존재하는지 조회합니다.
     *
     * @param socialId 구글의 경우 email 에 해당됩니다.
     * @return Optional<User> 유저를 찾지 못한 경우 null 이 반환됩니다.
     */
    public Optional<User> findBySocialId(String socialId) {
        return Optional.ofNullable(
            this.query
                .from(qUser)
                .where(
                    qUser.socialId.eq(socialId)
                )
                .fetchOne()
        );
    }

    /**
     * 유저를 생성합니다.
     *
     * @param socialId   구글 로그인 시 email 에 해당됩니다.
     * @param socialType 구글 로그인 시 GOOGLE 에 해당됩니다.
     * @return User 생성된 유저가 반환됩니다.
     */
    public User create(String socialId, String socialType) {
        User user = User.builder()
            .socialId(socialId)
            .socialType(socialType)
            .role(UserRole.USER)
            .build();
        this.entityManager.persist(user);
        return user;
    }

    /**
     * 넘겨받은 id를 가지는 유저를 찾아 삭제합니다.
     *
     * @param id 유저의 id 입니다.
     */
    public void deleteById(String id) throws NotFoundException {
        User user = Optional.ofNullable(
                this.query
                    .from(qUser)
                    .where(qUser.id.eq(id))
                    .fetchOne())
            .orElseThrow(() -> new NotFoundException("User Not Found"));
        this.entityManager.remove(user);
    }

    /**
     * 유저를 삭제합니다.
     *
     * @param user 삭제 할 유저입니다.
     */
    public void delete(User user) {
        this.entityManager.remove(user);
    }
}
