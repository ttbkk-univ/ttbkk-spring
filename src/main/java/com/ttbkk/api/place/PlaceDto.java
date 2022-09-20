package com.ttbkk.api.place;

import lombok.*;

import javax.validation.constraints.NotNull;
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
        private String location;

        private String description;

        private String telephone;

        private String address;
    }

    /**
     * String Type 으로 Response 해줄 때 사용 하는 Dto.
     * Ex) 장소 생성, 수정, 삭제 등에 사용.
     */
    @Getter
    public static class PlaceResponseMessageDto {
        private String message;

        /**
         * PlaceResponseDto 생성자.
         * @param message
         */
        public PlaceResponseMessageDto(String message) {
            this.message = message;
        }
    }

//    /**
//     * 단일 Place Response Dto.
//     */
//    @Getter
//    @NoArgsConstructor
//    public static class PlaceResponseDto {
//
//        private String name;
//
//        private String brand;
//
//        private List<String> hashtags;
//
//        private String location;
//
//        private String description;
//
//        private String telephone;
//
//        private String address;
//
//        public PlaceResponseDto(Place place) {
//            this.name = place.getName();
//            this.brand = place.getBrand().getName();
//            this.hashtags = place.getH;
//            this.location = location;
//            this.description = description;
//            this.telephone = telephone;
//            this.address = address;
//        }
//    }

    /**
     * Place Update Api 요청시 request data 를 담는 Dto.
     */
    @Getter
    @NoArgsConstructor
    public static class PlaceUpdateRequestDto {
        @NotNull
        private String name;

        @NotNull
        private String brand;

        private List<String> hashtags;

        @NotNull
        private String location;

        private String description;

        private String telephone;

        private String address;
    }

}
