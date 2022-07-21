package com.ttbkk.api.user;

import com.querydsl.jpa.impl.JPAQuery;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.util.Optional;

@Repository
public class UserRepository {

    private final EntityManager entityManager;

    private final QUser qUser;

    private final JPAQuery<User> query;

    /**
     * 클래스 생성자.
     *
     * @param entityManager EntityManager
     */
    public UserRepository(EntityManager entityManager) {
        this.entityManager = entityManager;
        this.query = new JPAQuery<>(this.entityManager);
        this.qUser = QUser.user;
    }

    /**
     * 유저의 socialId를 받아 조회하여 User 또는 null을 반환합니다.
     *
     * @param socialId User.socialId
     * @return Optional<User>
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
     * @param user User
     */
    public void create(User user) {
        this.entityManager.persist(user);
    }

    /**
     * 해당 id를 가지는 유저를 조회합니다.
     *
     * @param id User.id
     * @return Optional<User>
     */
    public Optional<User> findById(String id) {
        return Optional.ofNullable(
            this.query
                .from(qUser)
                .where(
                    qUser.id.eq(id)
                )
                .fetchOne()
        );
    }

    /**
     * 해당 유저를 삭제합니다.
     * @param user User
     */
    public void remove(User user) {
        this.entityManager.remove(user);
    }
}

