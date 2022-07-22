package com.ttbkk.api.brand;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

public class BrandDto {

    @Getter
    @Setter
    @Builder
    public static class  CreateBrandRequest {
        private String name;
        private String description;

        /**
         * 파라미터와 생성 요청을 받아서 새 브랜드 생성.
         * @param name 브랜드 이름
         * @param description 브랜드 설명
         */
        public CreateBrandRequest(String name, String description) {
            this.name = name;
            this.description = description;
        }

    }
}
