package com.ttbkk.api.place;
import com.ttbkk.api.common.exception.domain.place.BadRequestGrid;
import com.ttbkk.api.common.exception.domain.place.BadRequestLocation;
import com.ttbkk.api.user.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.util.List;

/**
 * Place 관련 Service 로직 구현 클래스.
 */
@RequiredArgsConstructor
@Transactional
@Service
public class PlaceService {
    private final PlaceRepository placeRepository;

//    public PlaceDto.PlaceResponseDto getPlace(String placeId) {
//        Optional<Place> place = placeRepository.findById(placeId);
//        if (place.isEmpty()) {
//            re
//        }
//    }

    /**
     * Create Place API 메서드.
     * @param requestDto
     * @param user
     * @return PlaceDto.PlaceResponseDto.
     */
    public PlaceDto.PlaceResponseMessageDto createPlace(PlaceDto.PlaceCreateRequestDto requestDto, User user) {

        //check location
        BigDecimal[] location = checkLocation(requestDto.getLocation());

        //create Place
        Place place = Place.builder()
                .name(requestDto.getName())
                .latitude(location[0])
                .longitude(location[1])
                .description(requestDto.getDescription())
                .telephone(requestDto.getTelephone())
                .address(requestDto.getAddress())
                .build();

        //로그인한 사람만 장소 생성 가능 할 시, place builder 에 추가할 예정.
        //일단 setter 를 통해 주입.
        place.setCreatedBy(user);

        placeRepository.save(place);
        //user
        //brand
        //hashtag
        //review
        return new PlaceDto.PlaceResponseMessageDto("장소 생성 성공");
    }

    /**
     * 장소 업데이트 API 메서드.
     * @param requestDto
     * @param user
     * @return PlaceDto.PlaceResponseMessageDto.
     */
    public PlaceDto.PlaceResponseMessageDto updatePlace(PlaceDto.PlaceUpdateRequestDto requestDto, User user) {

        //check location
        BigDecimal[] location = checkLocation(requestDto.getLocation());

        //create Place
        Place place = Place.builder()
                .name(requestDto.getName())
                .latitude(location[0])
                .longitude(location[1])
                .description(requestDto.getDescription())
                .telephone(requestDto.getTelephone())
                .address(requestDto.getAddress())
                .build();

        //로그인한 사람만 장소 생성 가능 할 시, place builder 에 추가할 예정.
        //일단 setter 를 통해 주입.
        place.setCreatedBy(user);

        placeRepository.save(place);
        //user
        //brand
        //hashtag
        return new PlaceDto.PlaceResponseMessageDto("장소 업데이트 성공");
    }

    /**
     * 좌표 값의 유효성 검사와 함께 DB 에 설정된 표준 크기로 맞춰주는 함수.
     * @param location
     * @return BigDecimal[]
     */
    public BigDecimal[] checkLocation(String location) {
        double[] checkedFirstStep = this.checkLocationFormAndIntegerPart(location);
        BigDecimal[] checkedLastStep = this.checkLocationDecimalPart(checkedFirstStep);
        return checkedLastStep;
    }

    /**
     * String type 의 파라미터 변수를 latitude 와 longitude 로 분리한다.
     * 분리한 String data 를 BigDecimal type 으로 변환하여 getPlacesAndCountInGrid 메서드에 파라미터로 넘겨준다.
     *
     * locationData[0] : latitude
     * locationData[1] : longitude
     *
     * @param topRight : topRight 지점의 latitude&longitude
     * @param bottomLeft : bottomLeft 지점의 latitude&longitude
     * @return PlaceDto.GridResponseDto
     * @throws Exception
     */
    public PlaceDto.GridResponseDto getPlacesAndCountInGrid(String topRight, String bottomLeft) {
        double[] topRightLocation = this.checkLocationFormAndIntegerPart(topRight);
        double[] bottomLeftLocation = this.checkLocationFormAndIntegerPart(bottomLeft);

        BigDecimal topRightX = BigDecimal.valueOf(topRightLocation[0]);
        BigDecimal topRightY = BigDecimal.valueOf(topRightLocation[1]);
        BigDecimal bottomLeftX = BigDecimal.valueOf(bottomLeftLocation[0]);
        BigDecimal bottomLeftY = BigDecimal.valueOf(bottomLeftLocation[1]);

        this.verifyGridSize(topRightX, topRightY, bottomLeftX, bottomLeftY);

        List<Place> places = placeRepository.getPlacesAndCountInGrid(topRightX, topRightY, bottomLeftX, bottomLeftY);

        return new PlaceDto.GridResponseDto(places);
    }

    /**
     * location data 를 받아 DB 스펙에 맞춰 처리 후 BigDecimal 형태로 return 해주는 메서드.
     *
     * @param location
     * @return BigDecimal[]
     */
    public BigDecimal[] checkLocationDecimalPart(double[] location) {
        String latitude = checkLatitudeDecimalPart(location[0]);
        String longitude = checkLongitudeDecimalPart(location[1]);

        return typeCastingLocationToBigDecimal(latitude, longitude);
    }

    /**
     * String type 의 location data 들 (latitude, longitude)을 받아 BigDecimal type 으로 Return.
     * @param latitude
     * @param longitude
     * @return BigDecimal[]
     */
    private BigDecimal[] typeCastingLocationToBigDecimal(String latitude, String longitude) {
        BigDecimal[] result = new BigDecimal[2];
        result[0] = new BigDecimal(latitude);
        result[1] = new BigDecimal(longitude);

        return result;
    }

    /**
     * latitude DB 스펙인 decimal(15,13) 를 맞춰주는 메서드.
     *
     * @param latitude
     * @return String
     */
    private String checkLatitudeDecimalPart(double latitude) {
        DecimalFormat decimalFormat = new DecimalFormat("0.0000000000000");
        return decimalFormat.format(latitude);
    }

    /**
     * longitude DB 스펙인 decimal(15,12) 를 맞춰주는 메서드.
     *
     * @param longitude
     * @return String
     */
    private String checkLongitudeDecimalPart(double longitude) {
        DecimalFormat decimalFormat = new DecimalFormat("0.000000000000");
        return decimalFormat.format(longitude);
    }

    /**
     * 좌표를 받아올때 데이터 형식이 맞는지, 위도와 경도 범위가 올바른지 검사 하는 메서드.
     * 추후 다른 기능 구현시 재사용 하기 위해 double[] 로 리턴. (ex) 소수자릿수 검사 등)
     *
     * locationData[0] : latitude
     * locationData[1] : longitude
     *
     * @param location
     * @return double[]
     */
    public double[] checkLocationFormAndIntegerPart(String location) {
        double[] locationData = this.checkAndConvertLocationForm(location);
        this.checkLatitudeIntegerPart(locationData[0]);
        this.checkLongitudeIntegerPart(locationData[1]);

        return locationData;
    }

    /**
     * 경도의 정수부분 범위 검사하는 메서드.
     * -180 <= longitude <= 180.
     *
     * @param longitude
     * @throws Exception
     */
    private void checkLongitudeIntegerPart(double longitude) {
        if (!(-180 <= longitude && longitude <= 180)) {
            throw new BadRequestLocation();
        }
    }

    /**
     * 위도의 정수부분 범위 검사하는 메서드.
     * -90 <= latitude <= 90
     *
     * @param latitude
     * @throws Exception
     */
    private void checkLatitudeIntegerPart(double latitude) {
        if (!(-90 <= latitude && latitude <= 90)) {
            throw new BadRequestLocation();
        }
    }

    /**
     * 좌표의 form 을 먼저 검사하고,
     * 해당 좌표를 세부적으로 검사하기 위해 double 형태로 convert 해주는 메서드 .
     *
     * result[0] : latitude
     * result[1] : longitude
     *
     * @param location 좌표 데이터.
     * @return double[]
     * @throws Exception IndexOutOfBoundsException, ClassCastException
     */
    private double[] checkAndConvertLocationForm(String location) {
        double[] result = new double[2];
        String[] locationData = location.split(",");
        if (locationData.length != 2) {
            throw new BadRequestLocation();
        }
        try {
            result[0] = Double.parseDouble(locationData[0]);
            result[1] = Double.parseDouble(locationData[1]);
        } catch (NumberFormatException e) {
            throw new BadRequestLocation();
        }
        return result;
    }

    /**
     * grid 영역의 크기를 검사하는 메서드.
     * grid 영역의 size 는 0.2 < size < 1
     *
     * compareTo() -> 자기 자신이 비교 대상보다 작으면 음수, 같으면 0, 크면 양수.
     *
     * @param topRightX topRight 지점의 latitude
     * @param topRightY topRight 지점의 longitude
     * @param bottomLeftX bottomLeft 지점의 latitude
     * @param bottomLeftY bottomLeft 지점의 longitude
     */
    private void verifyGridSize(BigDecimal topRightX, BigDecimal topRightY,
                                BigDecimal bottomLeftX, BigDecimal bottomLeftY) {

        if (topRightY.subtract(bottomLeftY).abs().compareTo(BigDecimal.valueOf(0.2)) < 0
                || topRightY.subtract(bottomLeftY).abs().compareTo(BigDecimal.valueOf(1)) > 0
                || topRightX.subtract(bottomLeftX).abs().compareTo(BigDecimal.valueOf(0.2)) < 0
                || topRightX.subtract(bottomLeftX).abs().compareTo(BigDecimal.valueOf(1)) > 0) {

            //추후 Custom 예외처리로 바꿀 예정. -> 새로운 pr에서 처리.
            throw new BadRequestGrid();
        }
    }

}


