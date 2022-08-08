package com.ttbkk.api.place;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;


@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/place")
public class PlaceController {
    private final PlaceService placeService;

    /**
     * client request data 를 받아 dto 를 통해 data 를 검증 하고 ,
     * 해당 data 안에 있는 Place 객체 리스트와 Count 데이터가 있는 PlaceDto.GridResponseDto 를 return 합니다.
     * <p>
     * .@Pattern : client 에서 넘어온 parameter 들 정규식을 통한 data format 검증
     *
     * @param topRight   grid 내의 우측상단 꼭지점 좌표의 latitude & longitude
     * @param bottomLeft grid 내의 좌측하단 꼭지점 좌표의 latitude & longitude
     * @return ResponseEntity<PlaceDto.GridResponseDto>
     */
    @GetMapping("/grid")
    public ResponseEntity<PlaceDto.GridResponseDto> callGridApi(@Validated @Size(max = 3) @RequestParam String topRight, @Validated
    @Pattern(regexp = "^-?\\d{1,2}\\.\\d{0,100},-?\\d{1,3}\\.\\d{0,100}$", message = "좌표값 데이터 포맷을 확인해주세요. (데이터 포맷: '{latitude정보}','{longitude정보}')")
    @RequestParam String bottomLeft) {

        System.out.println(topRight + ", " + bottomLeft);
        PlaceDto.GridRequestDto request = new PlaceDto.GridRequestDto(topRight, bottomLeft);
        PlaceDto.GridResponseDto response = placeService.getPlacesAndCountInGrid(request);
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }
}
