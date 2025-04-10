package com.wanderfun.applicationlayer.usecase;

import com.wanderfun.applicationlayer.dto.checkin.CheckInDto;
import com.wanderfun.applicationlayer.dto.favouriteplace.FavouritePlaceDto;
import com.wanderfun.applicationlayer.dto.feedback.FeedbackCreateDto;
import com.wanderfun.applicationlayer.dto.place.PlaceCreateDto;
import com.wanderfun.applicationlayer.dto.place.PlaceDto;
import com.wanderfun.applicationlayer.dto.place.PlaceMiniDto;
import com.wanderfun.applicationlayer.exception.ObjectAlreadyExistException;
import com.wanderfun.applicationlayer.mapper.ObjectMapper;
import com.wanderfun.applicationlayer.service.*;
import com.wanderfun.applicationlayer.util.JwtUtil;
import com.wanderfun.domainlayer.model.places.Feedback;
import com.wanderfun.domainlayer.model.places.Place;
import com.wanderfun.domainlayer.model.checkins.CheckIn;
import com.wanderfun.domainlayer.model.users.FavouritePlace;
import com.wanderfun.domainlayer.model.users.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class PlaceUsecase {
    private final PlaceService placeService;
    private final ObjectMapper objectMapper;
    private final JwtUtil jwtUtil;
    private final FeedbackService feedbackService;
    private final FavouritePlaceService favouritePlaceService;
    private final CheckInService checkInService;
    private final UserService userService;

    @Autowired
    public PlaceUsecase(PlaceService placeService, ObjectMapper objectMapper, JwtUtil jwtUtil, FeedbackService feedbackService,
                        FavouritePlaceService favouritePlaceService, CheckInService checkInService, UserService userService) {
        this.placeService = placeService;
        this.objectMapper = objectMapper;
        this.jwtUtil = jwtUtil;
        this.feedbackService = feedbackService;
        this.favouritePlaceService = favouritePlaceService;
        this.checkInService = checkInService;
        this.userService = userService;
    }

    public List<PlaceMiniDto> findAllPlaces() {
        return objectMapper.mapList(placeService.findAll(), PlaceMiniDto.class);
    }

    public PlaceDto findPlaceById(Long id) {
        return objectMapper.map(placeService.findById(id), PlaceDto.class);
    }

    public List<PlaceMiniDto> findAllPlacesByNameContaining(String name) {
        return objectMapper.mapList(placeService.findAllByNameContaining(name), PlaceMiniDto.class);
    }

    public PlaceDto findPlaceByName(String name) {
        return objectMapper.map(placeService.findByName(name), PlaceDto.class);
    }

    public PlaceDto findPlaceByLongitudeAndLatitude(double longitude, double latitude) {
        return objectMapper.map(placeService.findByLongitudeAndLatitude(longitude, latitude), PlaceDto.class);
    }

    public boolean createPlace(PlaceCreateDto placeCreateDto) throws ObjectAlreadyExistException {
        Place place = objectMapper.map(placeCreateDto, Place.class);
        Place existingPlace;
        try {
            existingPlace = placeService.findByName(place.getName());
        } catch (Exception e) {
            existingPlace = null;
        }
        if(existingPlace != null) {
            throw new ObjectAlreadyExistException("This name is already used!");
        }

        try {
            existingPlace = placeService.findByLongitudeAndLatitude(place.getLongitude(), place.getLatitude());
        } catch (Exception e) {
            existingPlace = null;
        }
        if(existingPlace != null) {
            throw new ObjectAlreadyExistException("This longitude and latitude is already used!");
        }

        placeService.create(place);
        return true;
    }

    public boolean updatePlaceById(Long id, PlaceCreateDto placeCreateDto) throws ObjectAlreadyExistException {
        Place place = objectMapper.map(placeCreateDto, Place.class);

        Place currentPlace = placeService.findById(id);
        if (place.getName() != null) {
            if (!place.getName().equals(currentPlace.getName())) {
                Place existingPlace;
                try {
                    existingPlace = placeService.findByName(place.getName());
                } catch (Exception e) {
                    existingPlace = null;
                }
                if (existingPlace != null) {
                    throw new ObjectAlreadyExistException("This name is already used!");
                }
            }
        }

        if (place.getLongitude() != currentPlace.getLongitude() || place.getLatitude() != currentPlace.getLatitude()) {
            Place existingPlace;
            try {
                existingPlace = placeService.findByLongitudeAndLatitude(place.getLongitude(), place.getLatitude());
            } catch (Exception e) {
                existingPlace = null;
            }
            if (existingPlace != null) {
                throw new ObjectAlreadyExistException("This longitude and latitude is already used!");
            }
        }

        placeService.updateById(id, place);
        return true;
    }

    public boolean deletePlaceById(Long id) {
        placeService.deleteById(id);
        return true;
    }

    public boolean deleteAllPlaces() {
        placeService.deleteAll();
        return true;
    }

    public boolean createFeedback(FeedbackCreateDto feedbackCreateDto, Long placeId, String accessToken) {
        Feedback feedback = objectMapper.map(feedbackCreateDto, Feedback.class);
        feedback.setPlaceId(placeId);
        feedback.setTime(new Date());
        User user = userService.findById(jwtUtil.getIdFromToken(accessToken));
        feedback.setUserName(user.getLastName() + " " + user.getFirstName());
        feedback.setUserAvatar(user.getAvatarUrl());
        feedbackService.create(feedback);
        Place place = placeService.findById(placeId);
        place.calculateAverageRating();
        placeService.updateById(placeId, place);
        return true;
    }

    public List<FavouritePlaceDto> findAllFavouritePlaces(String accessToken) {
        return objectMapper.mapList(favouritePlaceService.findAllByUserId(jwtUtil.getIdFromToken(accessToken)), FavouritePlaceDto.class);
    }

    public boolean addFavouritePlace(Long placeId, String accessToken) {
        FavouritePlace favouritePlace = new FavouritePlace();
        favouritePlace.setUserId(jwtUtil.getIdFromToken(accessToken));
        Place place = placeService.findById(placeId);
        favouritePlace.setPlaceId(place.getId());
        favouritePlace.setPlaceName(place.getName());
        favouritePlace.setPlaceLongitude(place.getLongitude());
        favouritePlace.setPlaceLatitude(place.getLatitude());
        favouritePlace.setPlaceCoverImageUrl(place.getCoverImageUrl());
        favouritePlaceService.create(favouritePlace);
        return true;
    }

    public boolean deleteFavouritePlaceByIds(List<Long> ids, String accessToken) {
        List<FavouritePlace> favouritePlaces = favouritePlaceService.findAllByUserId(jwtUtil.getIdFromToken(accessToken));
        List<Long> validIds = favouritePlaces.stream()
                .map(FavouritePlace::getId)
                .collect(Collectors.toList());

        List<Long> filteredIds = ids.stream()
                .filter(validIds::contains)
                .collect(Collectors.toList());
        favouritePlaceService.deleteByIds(filteredIds);
        return true;
    }

    public CheckInDto findCheckInByPlaceIdAndUserId(Long placeId, String accessToken) {
        return objectMapper.map(checkInService.findByPlaceIdAndUserId(placeId, jwtUtil.getIdFromToken(accessToken)), CheckInDto.class);
    }

    public boolean checkInPlace(Long placeId, String accessToken) {
        CheckIn checkIn;
        CheckIn existingCheckIn;
        Place place = placeService.findById(placeId);
        try {
            existingCheckIn = checkInService.findByPlaceIdAndUserId(placeId, jwtUtil.getIdFromToken(accessToken));
        } catch (Exception e) {
            existingCheckIn = null;
        }
        if(existingCheckIn != null) {
            checkIn = existingCheckIn;
            checkIn.addCount();
            checkIn.addTotalPoint(place.getCheckInPoint());
            checkIn.setLastCheckInTime(new Date());
            checkInService.updateById(checkIn.getId(), checkIn);
        } else {
            checkIn = new CheckIn();
            checkIn.setPlaceId(placeId);
            checkIn.setUserId(jwtUtil.getIdFromToken(accessToken));
            checkIn.addCount();
            checkIn.addTotalPoint(place.getCheckInPoint());
            checkIn.setLastCheckInTime(new Date());
            checkInService.create(checkIn);
        }
        return true;
    }
}
