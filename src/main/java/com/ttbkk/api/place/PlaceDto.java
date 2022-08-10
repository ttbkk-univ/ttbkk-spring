package com.ttbkk.api.place;

import lombok.*;

import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.util.List;

public class PlaceDto {

    /**
     * GridApi 요청 받을시 return 하는 Dto 클래스입니다.
     */
    @Getter
    public static class GridResponseDto {
        private int count;
        private List<Place> edges;

        /**
         * 생성자.
         * Place 리스트를 받아 저장하고, 리스트의 size 를 유지하는 Dto
         * @param edges
         */
        public GridResponseDto(List<Place> edges) {
            this.edges = edges;
            this.count = edges.size();
        }
    }

    /**
     * GridApi 요청 받을시 return 하는 Dto 클래스입니다.
     */
    @Getter
    @NoArgsConstructor
    public static class PlaceCreateRequestDto {
        @NotNull
        private String name;

        @NotNull
        private String brand;

        private List<String> hashtags;

        @NotNull
        private BigDecimal latitude;

        @NotNull
        private BigDecimal longitude;

        private String description;

        private String telephone;

        private String address;
    }
}
