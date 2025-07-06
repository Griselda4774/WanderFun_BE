package com.wanderfun.infrastructurelayer.usecase;

import com.wanderfun.applicationlayer.dto.checkins.CheckInDto;
import com.wanderfun.applicationlayer.dto.places.MiniPlaceDto;
import com.wanderfun.applicationlayer.exception.ObjectAlreadyExistException;
import com.wanderfun.applicationlayer.exception.ObjectNotFoundException;
import com.wanderfun.applicationlayer.mapper.ObjectMapper;
import com.wanderfun.applicationlayer.service.checkins.CheckInService;
import com.wanderfun.applicationlayer.service.place.PlaceService;
import com.wanderfun.applicationlayer.service.users.UserService;
import com.wanderfun.applicationlayer.usecase.CheckInUsecase;
import com.wanderfun.applicationlayer.util.JwtUtil;
import com.wanderfun.domainlayer.model.checkins.CheckIn;
import com.wanderfun.domainlayer.model.places.Place;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.List;

@Service
public class CheckInUsecaseImpl implements CheckInUsecase {
    private final CheckInService checkInService;
    private final ObjectMapper objectMapper;
    private final JwtUtil jwtUtil;
    private final UserService userService;
    private final PlaceService placeService;

    @Autowired
    public CheckInUsecaseImpl(CheckInService checkInService, ObjectMapper objectMapper, JwtUtil jwtUtil, UserService userService, PlaceService placeService) {
        this.checkInService = checkInService;
        this.objectMapper = objectMapper;
        this.jwtUtil = jwtUtil;
        this.userService = userService;
        this.placeService = placeService;
    }

    @Override
    public List<CheckInDto> findAllByUser(String accessToken) {
        Long userId = userService.findByAccountId(jwtUtil.getIdFromToken(accessToken)).getId();
        return objectMapper.mapList(checkInService.findAllByUserId(userId), CheckInDto.class);
    }

    @Override
    public CheckInDto create(String accessToken, Long placeId) {
        Long userId = userService.findByAccountId(jwtUtil.getIdFromToken(accessToken)).getId();

        try {
            CheckIn lastCheckIn = checkInService.findLastCheckInByUserId(userId);
            LocalDateTime lastTime = lastCheckIn.getCreatedAt();
            LocalDateTime now = LocalDateTime.now();
            if (Duration.between(lastTime, now).compareTo(Duration.ofHours(24)) < 0) {
                throw new ObjectAlreadyExistException("You can only check in once every 24 hours.");
            }
        } catch (ObjectNotFoundException ignored) {
        }

        Place place = placeService.findById(placeId);

        CheckIn newCheckIn = new CheckIn();
        newCheckIn.setUserId(userId);
        newCheckIn.setPlace(place);

        CheckIn savedCheckIn = checkInService.create(newCheckIn);
        return objectMapper.map(savedCheckIn, CheckInDto.class);
    }

    @Override
    public List<MiniPlaceDto> findAllEligiblePlaces(double userLng, double userLat) {
        double boundingRadius = 200; // 200 meters

        // Calculate bounding box
        double latOffset = boundingRadius / 111111.0;
        double lngOffset = boundingRadius / (111111.0 * Math.cos(Math.toRadians(userLat)));
        double minLat = userLat - latOffset;
        double maxLat = userLat + latOffset;
        double minLng = userLng - lngOffset;
        double maxLng = userLng + lngOffset;

        List<Place> places = placeService.findAllInBoundingBox(minLng, maxLng, minLat, maxLat);
        List<Place> eligiblePlaces = places.stream()
                .filter(place -> {
                    double distance = haversine(userLat, userLng, place.getLatitude(), place.getLongitude());
                    return distance <= 200;
                })
                .toList();

        return objectMapper.mapList(eligiblePlaces, MiniPlaceDto.class);
    }

    public double haversine(double lat1, double lon1, double lat2, double lon2) {
        final int R = 6371000;
        double dLat = Math.toRadians(lat2 - lat1);
        double dLon = Math.toRadians(lon2 - lon1);
        double a = Math.sin(dLat / 2) * Math.sin(dLat / 2)
                + Math.cos(Math.toRadians(lat1)) * Math.cos(Math.toRadians(lat2))
                * Math.sin(dLon / 2) * Math.sin(dLon / 2);
        double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));
        return R * c;
    }
}
