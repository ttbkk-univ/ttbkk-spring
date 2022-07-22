package com.ttbkk.api.place;

import lombok.*;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
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
     *
     * Controller @Validated 를 통해 검증하고 오류 발생 시 GlobalExceptionHandler 클래스의 handleMethodArgumentNotValid 메서드 실행.
     * .@Pattern : 정규식을 통한 data format 검증
     */
    @Getter
    @NoArgsConstructor
    public static class GridRequestDto {
        @NotNull
        @Pattern(regexp = "^-?\\d{1,2}\\.\\d{0,100},-?\\d{1,3}\\.\\d{0,100}$",
                message = "좌표값 데이터 포맷을 확인해주세요. (데이터 포맷: '{latitude정보}','{longitude정보}')")
        private String topRight;

        @NotNull
        @Pattern(regexp = "^-?\\d{1,2}\\.\\d{0,100},-?\\d{1,3}\\.\\d{0,100}$",
                message = "좌표값 데이터 포맷을 확인해주세요. (데이터 포맷: '{latitude정보}','{longitude정보}')")
        private String botLeft;

        /**
         * GridRequestDto 생성자.
         *
         * @param topRight Grid 지역 안의 우측상단 꼭지점의 좌표값.
         * @param botLeft Grid 지역 안의 좌측하단 꼭지점의 좌표값.
         */
        public GridRequestDto(String topRight, String botLeft) {
            this.topRight = topRight;
            this.botLeft = botLeft;
        }
    }
}
