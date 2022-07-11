package com.ttbkk.api.place;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.math.BigDecimal;

/**
 * Place 관련 Service 로직 구현 클래스.
 */
@RequiredArgsConstructor
@Transactional
@Service
public class PlaceService {
    private final PlaceRepository placeRepository;

    /**
     * dto 의 String data 를 BigDecimal type 으로 변환하여 Repo 에 넘겨준다.
     * @param dto client 의 requestData
     * @return PlaceDto.GridResponseDto
     */
    public PlaceDto.GridResponseDto getPlacesAndCountInGrid(PlaceDto.GridRequestDto dto) {
        return placeRepository.getPlacesAndCountInGrid(new BigDecimal(dto.getTopRightX()), new BigDecimal(dto.getTopRightY()), new BigDecimal(dto.getBotLeftX()), new BigDecimal(dto.getBotLeftY()));
    }

}
