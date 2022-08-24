package com.ttbkk.api.place;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.math.BigDecimal;
import java.util.List;

/**
 * Place 관련 Service 로직 구현 클래스.
 */
@RequiredArgsConstructor
@Transactional
@Service
public class PlaceService {
    private final PlaceRepository placeRepository;

    /**
     * String type 의 파라미터 변수를 latitude 와 longitude 로 분리한다.
     * 분리한 String data 를 BigDecimal type 으로 변환하여 getPlacesAndCountInGrid 메서드에 파라미터로 넘겨준다.
     *
     * @param topRight : topRight 지점의 latitude&longitude
     * @param bottomLeft : bottomLeft 지점의 latitude&longitude
     * @return PlaceDto.GridResponseDto
     * @throws Exception
     */
    public PlaceDto.GridResponseDto getPlacesAndCountInGrid(String topRight, String bottomLeft) throws Exception {
        BigDecimal topRightX = new BigDecimal(topRight.split(",")[0]);
        BigDecimal topRightY = new BigDecimal(topRight.split(",")[1]);
        BigDecimal bottomLeftX = new BigDecimal(bottomLeft.split(",")[0]);
        BigDecimal bottomLeftY = new BigDecimal(bottomLeft.split(",")[1]);

        this.verifyGridSize(topRightX, topRightY, bottomLeftX, bottomLeftY);

        List<Place> places = placeRepository.getPlacesAndCountInGrid(topRightX, topRightY, bottomLeftX, bottomLeftY);

        return new PlaceDto.GridResponseDto(places);
    }

    /**
     * 외부에서 사용하지 않는 메서드 이므로 private method 로 구현.
     * latitude 또는 longitude 를 DB에 저장하기 위해 DB 스펙으로 handling 하는 메서드.
     *
     * locInfo.length() - 1 이유 : "." 은 decimal 크기에 포함되지 않는다.
     * if (locInfo.contains("-")) 이 구문에서 curSize -1 을 해준 이유도 동일하다.
     *
     * @param locInfo 한 지점의 latitude 또는 longitude.
     * @return BigDecimal
     */
    private BigDecimal makeLocInfoToDecimal(String locInfo) {
        int decimalLength = 20;
        int curSize = locInfo.length() - 1;

        if (locInfo.contains("-")) {
            curSize -= 1;
        }
        if (curSize > decimalLength) {
            String verifiedString = locInfo.substring(0, decimalLength + 1);
            return new BigDecimal(verifiedString);
        } else {
            return new BigDecimal(locInfo);
        }
    }

    /**
     * grid 영역의 크기를 검사하는 메서드.
     * grid 영역의 size 는 0.2 < size < 1
     *
     * compareTo() -> 자기 자신이 비교 대상보다 작으면 음수, 같으면 0, 크면 양수
     *
     * @param topRightX topRight 지점의 latitude
     * @param topRightY topRight 지점의 longitude
     * @param bottomLeftX bottomLeft 지점의 latitude
     * @param bottomLeftY bottomLeft 지점의 longitude
     */
    private void verifyGridSize(BigDecimal topRightX, BigDecimal topRightY, BigDecimal bottomLeftX, BigDecimal bottomLeftY) throws Exception {
        if (topRightY.subtract(bottomLeftY).abs().compareTo(BigDecimal.valueOf(0.2)) == -1
                || topRightY.subtract(bottomLeftY).abs().compareTo(BigDecimal.valueOf(1)) == 1
                || topRightX.subtract(bottomLeftX).abs().compareTo(BigDecimal.valueOf(0.2)) == -1
                || topRightX.subtract(bottomLeftX).abs().compareTo(BigDecimal.valueOf(1)) == 1) {

            //추후 Custom 예외처리로 바꿀 예정. -> 새로운 pr에서 처리.
            throw new Exception("verifyGridSize exception");
        }
    }
}
