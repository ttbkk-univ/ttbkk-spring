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
     * 경도의 정수부분 범위 검사하는 메서드.
     *
     * @param longitude
     * @return double
     * @throws Exception
     */
    private double checkLongitudeInteger(double longitude) throws Exception {
        if (longitude >= -180 && longitude <= 180) {
            return longitude;
        }
        throw new Exception("올바르지 않은 경도 데이터");
    }

    /**
     * 위도의 정수부분 범위 검사하는 메서드.
     *
     * @param latitude
     * @return double
     * @throws Exception
     */
    private double checkLatitudeInteger(double latitude) throws Exception {
        if (latitude >= -90 && latitude <= 90) {
            return latitude;
        }
        throw new Exception("올바르지 않은 위도 데이터");
    }

    /**
     * 좌표의 form 을 먼저 검사하고,
     * 해당 좌표를 세부적으로 검사하기 위해 double 형태로 convert 해주는 메서드 .
     *
     * @param location 좌표 데이터.
     * @return double[]
     * @throws Exception IndexOutOfBoundsException, ClassCastException
     */
    private double[] checkAndConvertLocationForm(String location) throws Exception {
        double[] result = new double[2];
        String[] locationData = location.split(",");
        if (locationData.length != 2) {
            throw new Exception("좌표 데이터를 확인해주세요.");
        }
        //latitude
        result[0] = Double.parseDouble(locationData[0]);
        //longitude
        result[1] = Double.parseDouble(locationData[1]);
        return result;
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
