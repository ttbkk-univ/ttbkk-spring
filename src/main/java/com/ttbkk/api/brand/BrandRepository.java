package com.ttbkk.api.brand;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 * Brand DB 접근을 위한 인터페이스.
 */
@Repository
public interface BrandRepository extends JpaRepository<Brand, String> { // DB타입인 UUID로 입력해야하는지??

    /**
     * 브랜드 이름으로 검색.
     * @param name 브랜드명
     * @return Optional<Brand>
     */
    Optional<Brand> findByName(String name);

    /**
     * 브랜드 이름으로 삭제.
     * @param name 브랜드 이름
     */
    void deleteByName(String name);
}
