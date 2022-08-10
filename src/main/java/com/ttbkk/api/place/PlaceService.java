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

        verifyGridSize(topRightX, topRightY, bottomLeftX, bottomLeftY);

        List<Place> places = placeRepository.getPlacesAndCountInGrid(topRightX, topRightY, bottomLeftX, bottomLeftY);

        return new PlaceDto.GridResponseDto(places);
    }

    /**
     * 외부에서 사용하지 않는 메서드 이므로 private method 로 구현.
     * DB 스펙과 충돌하지 않게 최대 길이인 15를 넘지 않는지 확인하여 넘는다면 크기를 조절하는 메서드.
     *
     * locInfo.length() - 1 이유 : "." 은 decimal 크기에 포함되지 않는다.
     *
     * @param locationInfo 한 지점의 latitude 또는 longitude.
     * @return String
     */
    private String handleLocationLength(String locationInfo) {
        final int maxTotalLength = 15;

        int curSize = locationInfo.length();

        if (locationInfo.contains(".")) {
            curSize -= 1;
        }

        if (curSize > maxTotalLength) {
            return locationInfo.substring(0, maxTotalLength + 1);
        }
        return locationInfo;
    }

    /**
     * 외부에서 사용하지 않는 메서드 이므로 private method 로 구현.
     * DB 스펙과 충돌하지 않게 latitude 의 최대 소수점 길이인 13을 넘진 않는지 확인하여 넘는다면 크기를 조절하고 BigDecimal 타입으로 반환.
     *
     * @param latitude
     * @return BigDecimal type
     */
    private BigDecimal verifyDecimalsLatitude(String latitude) {
        final int maxDecimalLatitude = 13;
        int length = latitude.split("\\.")[1].length();
        if (length > maxDecimalLatitude) {
            return new BigDecimal(latitude.substring(0, maxDecimalLatitude + 1));
        }
        return new BigDecimal(latitude);
    }

    /**
     * 외부에서 사용하지 않는 메서드 이므로 private method 로 구현.
     * DB 스펙과 충돌하지 않게 latitude 의 최대 소수점 길이인 12을 넘진 않는지 확인하여 넘는다면 크기를 조절하고 BigDecimal 타입으로 반환.
     *
     * @param longitude
     * @return BigDecimal type
     */
    private BigDecimal verifyDecimalsLongitude(String longitude) {
        final int maxDecimalLongitude = 12;
        int length = longitude.split("\\.")[1].length();
        if (length > maxDecimalLongitude) {
            return new BigDecimal(longitude.substring(0, maxDecimalLongitude + 1));
        }
        return new BigDecimal(longitude);
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
