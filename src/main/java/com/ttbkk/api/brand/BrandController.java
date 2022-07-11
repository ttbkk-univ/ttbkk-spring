package com.ttbkk.api.brand;

import lombok.RequiredArgsConstructor;
import org.jetbrains.annotations.NotNull;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

/**
 * 웹 주소를 통해서 들어오는 곳.
 */
@RestController
@RequiredArgsConstructor
@RequestMapping("/brands")
public class BrandController {

    private final BrandService brandService;

    /**
     * 등록된 전체 브랜드 조회.
     * @return 브랜드의 리스트
     */
    @GetMapping("/")
    public List<Brand> getBrands() {
        return brandService.findAll();
    }

    /**
     * 새 브랜드 항목을 생성.
     * @param param POST 로 name 과 description 을 받아온다.
     * @return 브랜드를 새로 생성
     */
    @PostMapping("/")
    public ResponseEntity<BrandDto.CreateBrandRequest> createBrand(
            @RequestBody @NotNull Map<String, Object> param
    ) {
        //Dto 버전으로 만들기
//        return brandService.createBrand(Brand.builder().name((String) request.get("name")).description((String) request.get("description")).build());
        BrandDto.CreateBrandRequest brandRequest = new BrandDto.CreateBrandRequest((String) param.get("name"), (String) param.get("description")); // 강제 형변형 없이 사용할수는 없을까..?
        Brand brand = new Brand(brandRequest.getName(), brandRequest.getDescription());
//        Brand brand = new BrandDto.CreateBrandRequest(param));
        brandService.createBrand(brand);
        return ResponseEntity.status(HttpStatus.OK).body(brandRequest);
    }
}
