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
     * Place Create Api (장소생성) 요청시 request data 를 담는 Dto.
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
        private String latitude;

        @NotNull
        private String longitude;

        private String description;

        private String telephone;

        private String address;
    }

    @Getter
    public static class PlaceResponseDto {
        private String message;

        /**
         * PlaceResponseDto 생성자.
         * @param message
         */
        public PlaceResponseDto(String message) {
            this.message = message;
        }
    }

    @Getter
    public static class VerifiedCoordinate {
        private BigDecimal latitude;
        private BigDecimal longitude;

        /**
         * VerifiedCoordinate 생성자.
         * @param latitude
         * @param longitude
         */
        public VerifiedCoordinate(BigDecimal latitude, BigDecimal longitude) {
            this.latitude = latitude;
            this.longitude = longitude;
        }
    }
}
