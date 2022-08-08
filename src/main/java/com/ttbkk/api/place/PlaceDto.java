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
     * GridApi 요청 데이터를 담는 Dto 클래스입니다.
     */
    @Getter
    @NoArgsConstructor
    public static class GridRequestDto {
        @NotNull
        private String topRight;

        @NotNull
        private String bottomLeft;

        /**
         * GridRequestDto 생성자.
         *
         * @param topRight Grid 지역 안의 우측상단 꼭지점의 좌표값.
         * @param bottomLeft Grid 지역 안의 좌측하단 꼭지점의 좌표값.
         */
        public GridRequestDto(String topRight, String bottomLeft) {
            this.topRight = topRight;
            this.bottomLeft = bottomLeft;
        }
    }
}
