package com.ttbkk.api.place;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;


@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/place")
public class PlaceController {
    private final PlaceService placeService;

    /**
     * client request data 를 받아 dto 를 통해 data 를 검증 하고 ,
     * 해당 data 안에 있는 Place 객체 리스트와 Count 데이터가 있는 PlaceDto.GridResponseDto 를 return 합니다.
     * @param request grid 내의 우측상단, 좌측하단의 꼭지점들의 latitude & longitude
     * @return PlaceDto.GridResponseDto
     */
    @PostMapping("/grid")
    public ResponseEntity<PlaceDto.GridResponseDto> callGridApi(@RequestBody @Validated PlaceDto.GridRequestDto request) {
        PlaceDto.GridResponseDto response = placeService.getPlacesAndCountInGrid(request);
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }
}
