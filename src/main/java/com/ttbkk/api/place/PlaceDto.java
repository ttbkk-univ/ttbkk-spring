package com.ttbkk.api.place;

import com.ttbkk.api.common.exception.BaseException;
import lombok.*;

import javax.validation.constraints.NotNull;
import java.util.List;

public class PlaceDto {

    /**
     * GridApi 요청 받을시 return 하는 Dto 클래스입니다.
     */
    @Getter
    public static class GridResponseDto {
        private List<Place> edges;
        private int count;

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
    public static class GridRequestDto {
        private String topRightX;
        private String topRightY;
        private String botLeftX;
        private String botLeftY;

        /**
         * if문 ~ : 좌표값이 latitude 와 longitude 2가지 요소로 이루어져있는지 검증.
         *
         * @param topRight Grid 지역 안의 우측상단 꼭지점의 좌표값
         * @param botLeft Grid 지역 안의 좌측하단 꼭지점의 좌표값
         */
        public GridRequestDto(@NotNull String topRight, @NotNull String botLeft) {
            if (topRight.split(",").length != 2 || botLeft.split(",").length != 2) {
                throw new BaseException("Invalid Request Parameter : 좌표값 확인");
            }
            this.topRightX = topRight.split(",")[0];
            this.topRightY = topRight.split(",")[1];
            this.botLeftX = botLeft.split(",")[0];
            this.botLeftY = botLeft.split(",")[1];
        }
    }
}
