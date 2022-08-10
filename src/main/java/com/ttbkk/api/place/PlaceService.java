package com.ttbkk.api.place;

import com.ttbkk.api.place.place_hashtags.PlaceHashtagsRepository;
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
    private final PlaceHashtagsRepository placeHashtagsRepository;

    /**
     * Create Place Api (장소 생성) 메서드.
     *
     * @param requestDto
     * @return PlaceDto.PlaceResponseDto
     */
    public PlaceDto.PlaceResponseDto createPlace(PlaceDto.PlaceCreateRequestDto requestDto) {

        //verify locationInfo
        PlaceDto.VerifiedCoordinate verifiedInfo = getVerifiedCoordinate(requestDto.getLatitude(), requestDto.getLongitude());

        //create Place
        Place place = Place.builder()
                .name(requestDto.getName())
                .latitude(verifiedInfo.getLatitude())
                .longitude(verifiedInfo.getLongitude())
                .telephone(requestDto.getTelephone())
                .address(requestDto.getAddress())
                .build();

        //user
        //brand
        //hashtag
        return new PlaceDto.PlaceResponseDto("장소 생성 성공");
    }

    /**
     * String type 의 파라미터 변수를 latitude 와 longitude 로 분리한다.
     * 분리한 String data 를 BigDecimal type 으로 변환하여 getPlacesAndCountInGrid 메서드에 파라미터로 넘겨준다.
     *
     * @param topRight   : topRight 지점의 latitude&longitude
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
     * 외부에 공개되는 좌표값 handle 메서드.
     * request data 로 받은 좌표 정보와 DB 스펙을 맞춰주는 역할을 한다.
     *
     * @param latitude
     * @param longitude
     * @return PlaceDto.VerifiedCoordinate
     */
    public PlaceDto.VerifiedCoordinate getVerifiedCoordinate(String latitude, String longitude) {
        String verifiedLocationLatitude = verifyLocationSize(latitude);
        String verifiedLocationLongitude = verifyLocationSize(longitude);
        BigDecimal verifiedLatitude = verifyLatitudeDecimals(verifiedLocationLatitude);
        BigDecimal verifiedLongitude = verifyLongitudeDecimals(verifiedLocationLongitude);

        return new PlaceDto.VerifiedCoordinate(verifiedLatitude, verifiedLongitude);
    }

    /**
     * DB 스펙과 충돌하지 않게 최대 길이인 15를 넘지 않는지 확인하여 넘는다면 크기를 조절하는 메서드.
     * 외부에 공개되지 않는 메서드 이므로 private method 로 구현.
     * <p>
     * locInfo.length() - 1 이유 : "." 은 decimal 크기에 포함되지 않는다.
     *
     * @param locationInfo 한 지점의 latitude 또는 longitude.
     * @return String
     */
    private String verifyLocationSize(String locationInfo) {
        final int maxTotalSize = 15;

        if (locationInfo.contains(".")) {
            return handleSizeIfContainDot(locationInfo, maxTotalSize);
        }
        return handleSizeIfNoneDot(locationInfo, maxTotalSize);
    }

    /**
     * DB 스펙과 충돌하지 않게 latitude 의 최대 소수점 길이인 13을 넘진 않는지 확인하여 넘는다면 크기를 조절하고 BigDecimal 타입으로 반환.
     * 외부에 공개되지 않는 메서드 이므로 private method 로 구현.
     *
     * @param latitude
     * @return BigDecimal type
     */
    private BigDecimal verifyLatitudeDecimals(String latitude) {
        final int maxDecimalLatitude = 13;
        if (latitude.contains(".")) {
            String decimalPart = latitude.split("\\.")[1];
            return new BigDecimal(handleSizeIfNoneDot(decimalPart, maxDecimalLatitude));
        }
        return new BigDecimal(latitude);
    }

    /**
     * DB 스펙과 충돌하지 않게 latitude 의 최대 소수점 길이인 12을 넘진 않는지 확인하여 넘는다면 크기를 조절하고 BigDecimal 타입으로 반환.
     * 외부에 공개되지 않는 메서드 이므로 private method 로 구현.
     *
     * @param longitude
     * @return BigDecimal type
     */
    private BigDecimal verifyLongitudeDecimals(String longitude) {
        final int maxDecimalLongitude = 12;
        if (longitude.contains(".")) {
            String decimalPart = longitude.split("\\.")[1];
            return new BigDecimal(handleSizeIfNoneDot(decimalPart, maxDecimalLongitude));
        }
        return new BigDecimal(longitude);
    }

    /**
     * "." 을 포함하는 (소수점이 있는) 값을 maxSize 에 따라 handling 하는 메서드.
     * 외부에 공개되지 않는 메서드 이므로 private method 로 구현.
     * <p>
     * decimal 은 "." 은 크기에서 무시되기 때문에 "." 을 포함하는 값과 "." 을 포함하지 않는 값을 처리하는 방식을 분리 하였다.
     *
     * @param locationInfo (좌표 관련)값
     * @param maxSize      해당 값이 가질수 있는 최대 크기
     * @return String
     */
    private String handleSizeIfContainDot(String locationInfo, int maxSize) {
        int currentSize = locationInfo.length() - 1;
        if (currentSize > maxSize) {
            return locationInfo.substring(0, maxSize + 2);
        }
        return locationInfo;
    }

    /**
     * "." 을 포함하지않는 (소수점이 없는) 값을 maxSize 에 따라 handling 하는 메서드.
     * 외부에 공개되지 않는 메서드 이므로 private method 로 구현.
     * <p>
     * decimal 은 "." 은 크기에서 무시되기 때문에 "." 을 포함하는 값과 "." 을 포함하지 않는 값을 처리하는 방식을 분리 하였다.
     *
     * @param locationInfo (좌표 관련)값
     * @param maxSize      해당 값이 가질수 있는 최대 크기
     * @return String
     */
    private String handleSizeIfNoneDot(String locationInfo, int maxSize) {
        int currentSize = locationInfo.length();
        if (currentSize > maxSize) {
            return locationInfo.substring(0, maxSize + 1);
        }
        return locationInfo;
    }

    /**
     * grid 영역의 크기를 검사하는 메서드.
     * grid 영역의 size 는 0.2 < size < 1
     * <p>
     * compareTo() -> 자기 자신이 비교 대상보다 작으면 음수, 같으면 0, 크면 양수
     *
     * @param topRightX   topRight 지점의 latitude
     * @param topRightY   topRight 지점의 longitude
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
