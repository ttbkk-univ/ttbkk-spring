package com.ttbkk.api.place;

import com.ttbkk.api.annotations.auth.IsUser;
import com.ttbkk.api.user.User;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@Slf4j
@Validated
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
    public ResponseEntity<PlaceDto.GridResponseDto> callGridApi(
            @RequestParam String topRight,
            @RequestParam String bottomLeft
    ) {

        PlaceDto.GridResponseDto response = placeService.getPlacesAndCountInGrid(topRight, bottomLeft);
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    /**
     * 장소 생성 API.
     *
     * @param requestDto
     * @param user
     * @return ResponseEntity<PlaceDto.PlaceResponseMessageDto>.
     */
    @PostMapping
    @IsUser
    public ResponseEntity<PlaceDto.PlaceResponseMessageDto> createPlaceApi(
            @RequestBody PlaceDto.PlaceCreateRequestDto requestDto,
            User user
    ) {
        PlaceDto.PlaceResponseMessageDto response = placeService.createPlace(requestDto, user);
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

//    @GetMapping
//    public ResponseEntity<PlaceDto.PlaceResponseDto> getPlaceApi(@PathVariable String placeId) {
//        PlaceRepository
//        return ResponseEntity.status(HttpStatus.OK).body(response);
//    }

    /**
     * Place Update API.
     * @param placeId
     * @param requestDto
     * @param user
     * @return ResponseEntity<PlaceDto.PlaceResponseMessageDto>.
     */
    @PatchMapping
    @IsUser
    public ResponseEntity<PlaceDto.PlaceResponseMessageDto> updatePlaceApi(
            @PathVariable String placeId,
            @RequestBody PlaceDto.PlaceUpdateRequestDto requestDto,
            User user
    ) {
        PlaceDto.PlaceResponseMessageDto response = placeService.updatePlace(placeId, requestDto, user);
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }
}
