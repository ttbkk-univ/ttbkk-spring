package com.ttbkk.api.brand;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

/**
 * BrandController 에서 넘어오는 요청을 처리하기 위한 매서드 모음.
 */

@Service
@RequiredArgsConstructor
public class BrandService {

    private final BrandRepository brandRepository;

    /* @RequiredArgsConstructor lombok 어노테이션으로 초기화 하지 않아도 된다.
    @Autowired
    public BrandService(BrandRepository brandRepository) {
        this.brandRepository = brandRepository;
    }
    */

    /**
     * 전체 브랜드 조회하기.
     * @return Brand 의 List
     */
    public List<Brand> findAll() {
        return brandRepository.findAll();
    }

    /**
     * 아이디 값으로 브랜드 조회하기.
     * @param id (PK)
     * @return Brand 객체
     */
    public Optional<Brand> findById(String id) {
        return brandRepository.findById(id);
    }

    /**
     * 브랜드 명으로 조회하기.
     * @param name 브랜드명
     * @return 해당 브랜드 전체 목록
     */
    public Optional<Brand> findByName(String name) {
        return brandRepository.findByName(name);
    }

    /**
     * 브랜드 삭제하기.
     * @param id 아이디
     */
    public void deleteBrand(String id) {
        brandRepository.deleteById(id);
    }

    /**
     * 브랜드 이름으로 삭제하기.
     * @param name 브랜드명
     */
    @Transactional
    public void deleteByName(String name) {
        brandRepository.deleteByName(name);
    }

    /**
     * 브랜드 생성하기.
     * @param brand 브랜드 객체
     * @return save()
     */
    public Brand createBrand(Brand brand) {
        return brandRepository.save(brand);
    }
}
