package com.wanderfun.presentationlayer.controller;
//
//import com.wanderfun.applicationlayer.dto.checkin.CheckInDto;
//import com.wanderfun.applicationlayer.dto.favouriteplace.FavouritePlaceDto;
//import com.wanderfun.applicationlayer.dto.feedback.FeedbackCreateDto;
//import com.wanderfun.applicationlayer.dto.feedback.FeedbackDto;
//import com.wanderfun.applicationlayer.dto.place.PlaceCreateDto;
//import com.wanderfun.applicationlayer.dto.place.PlaceDto;
//import com.wanderfun.applicationlayer.dto.ResponseDto;
//import com.wanderfun.applicationlayer.dto.place.PlaceMiniDto;
//import com.wanderfun.applicationlayer.usecase.places.PlaceUsecase;
import com.wanderfun.applicationlayer.dto.PlaceCategoryDto;
import com.wanderfun.applicationlayer.dto.ResponseDto;
import com.wanderfun.domainlayer.model.places.PlaceCategory;
import com.wanderfun.presentationlayer.exception.RequestFailedException;
import com.wanderfun.applicationlayer.usecase.places.PlaceCategoryUsecase;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("wanderfun/place")
public class PlaceCategoryController {
    private final PlaceCategoryUsecase placeCategoryUsecase;

    @Autowired
    public PlaceCategoryController(PlaceCategoryUsecase placeCategoryUsecase) {
        this.placeCategoryUsecase = placeCategoryUsecase;
    }

//    @GetMapping("")
//    public ResponseEntity<ResponseDto<List<PlaceMiniDto>>> findAllPlaces() {
//        List<PlaceMiniDto> result = placeUsecase.findAllPlaces();
//        if(result == null) {
//            throw new RequestFailedException("Find all places failed!");
//        }
//
//        ResponseDto<List<PlaceMiniDto>> response = new ResponseDto<>();
//        response.setStatusCode(HttpStatus.OK.toString());
//        response.setMessage("Find all places successful!");
//        response.setData(result);
//        return ResponseEntity.status(HttpStatus.OK).body(response);
//    }
//
//    @GetMapping("/search/{name}")
//    public ResponseEntity<ResponseDto<List<PlaceMiniDto>>> findAllPlacesByNameContaining(@PathVariable String name) {
//        List<PlaceMiniDto> result = placeUsecase.findAllPlacesByNameContaining(name);
//        if (result == null) {
//            throw new RequestFailedException("Find all places by name containing failed!");
//        }
//
//        ResponseDto<List<PlaceMiniDto>> response = new ResponseDto<>();
//        response.setStatusCode(HttpStatus.OK.toString());
//        response.setMessage("Find all places by name containing successful!");
//        response.setData(result);
//        return ResponseEntity.status(HttpStatus.OK).body(response);
//    }
//
//    @GetMapping("/{id}")
//    public ResponseEntity<ResponseDto<PlaceDto>> findPlaceById(@PathVariable long id) {
//        PlaceDto result = placeUsecase.findPlaceById(id);
//        if (result == null) {
//            throw new RequestFailedException("Find place failed!");
//        }
//
//        ResponseDto<PlaceDto> response = new ResponseDto<>();
//        response.setStatusCode(HttpStatus.OK.toString());
//        response.setMessage("Find place successful!");
//        response.setData(result);
//        return ResponseEntity.status(HttpStatus.OK).body(response);
//    }
//
//    @GetMapping("/name/{name}")
//    public ResponseEntity<ResponseDto<PlaceDto>> findPlaceByName(@PathVariable String name) {
//        PlaceDto result = placeUsecase.findPlaceByName(name);
//        if (result == null) {
//            throw new RequestFailedException("Find place by name failed!");
//        }
//
//        ResponseDto<PlaceDto> response = new ResponseDto<>();
//        response.setStatusCode(HttpStatus.OK.toString());
//        response.setMessage("Find place by name successful!");
//        response.setData(result);
//        return ResponseEntity.status(HttpStatus.OK).body(response);
//    }
//
//    @GetMapping("/coordinates")
//    public ResponseEntity<ResponseDto<PlaceDto>> findPlaceByLongitudeAndLatitude(@RequestParam double longitude,
//                                                                             @RequestParam double latitude) {
//        PlaceDto result = placeUsecase.findPlaceByLongitudeAndLatitude(longitude, latitude);
//        if (result == null) {
//            throw new RequestFailedException("Find place by longitude and latitude failed!");
//        }
//
//        ResponseDto<PlaceDto> response = new ResponseDto<>();
//        response.setStatusCode(HttpStatus.OK.toString());
//        response.setMessage("Find place by longitude and latitude successful!");
//        response.setData(result);
//        return ResponseEntity.status(HttpStatus.OK).body(response);
//    }
//
//
//    @PostMapping("")
//    public ResponseEntity<ResponseDto<PlaceDto>> createPlace(@RequestBody PlaceCreateDto placeCreateDto) {
//        boolean result = placeUsecase.createPlace(placeCreateDto);
//        if (!result) {
//            throw new RequestFailedException("Create place failed!");
//        }
//
//        ResponseDto<PlaceDto> response = new ResponseDto<>();
//        response.setStatusCode(HttpStatus.OK.toString());
//        response.setMessage("Create place successful!");
//        return ResponseEntity.status(HttpStatus.OK).body(response);
//    }
//
//    @PutMapping("/{id}")
//    public ResponseEntity<ResponseDto<PlaceDto>> updatePlaceById(@PathVariable long id, @RequestBody PlaceCreateDto placeCreateDto) {
//        boolean result = placeUsecase.updatePlaceById(id, placeCreateDto);
//        if (!result) {
//            throw new RequestFailedException("Update place failed!");
//        }
//
//        ResponseDto<PlaceDto> response = new ResponseDto<>();
//        response.setStatusCode(HttpStatus.OK.toString());
//        response.setMessage("Update place successful!");
//        return ResponseEntity.status(HttpStatus.OK).body(response);
//    }
//
//    @DeleteMapping("/{id}")
//    public ResponseEntity<ResponseDto<PlaceDto>> deletePlaceById(@PathVariable long id) {
//        boolean result = placeUsecase.deletePlaceById(id);
//        if (!result) {
//            throw new RequestFailedException("Delete place failed!");
//        }
//
//        ResponseDto<PlaceDto> response = new ResponseDto<>();
//        response.setStatusCode(HttpStatus.OK.toString());
//        response.setMessage("Delete place successful!");
//        return ResponseEntity.status(HttpStatus.OK).body(response);
//    }
//
//    @DeleteMapping("")
//    public ResponseEntity<ResponseDto<PlaceDto>> deleteAllPlaces() {
//        boolean result = placeUsecase.deleteAllPlaces();
//        if (!result) {
//            throw new RequestFailedException("Delete all places failed!");
//        }
//
//        ResponseDto<PlaceDto> response = new ResponseDto<>();
//        response.setStatusCode(HttpStatus.OK.toString());
//        response.setMessage("Delete all places successful!");
//        return ResponseEntity.status(HttpStatus.OK).body(response);
//    }
//
//    @PostMapping("/feedback/{placeId}")
//    public ResponseEntity<ResponseDto<FeedbackDto>> createFeedback(@RequestHeader("Authorization") String accessToken,
//                                                                   @RequestBody FeedbackCreateDto feedbackCreateDto,
//                                                                   @PathVariable long placeId) {
//        if (accessToken.startsWith("Bearer ")) {
//            accessToken = accessToken.substring(7);
//        }
//        boolean result = placeUsecase.createFeedback(feedbackCreateDto, placeId, accessToken);
//        if (!result) {
//            throw new RequestFailedException("Create feedback failed!");
//        }
//
//        ResponseDto<FeedbackDto> response = new ResponseDto<>();
//        response.setStatusCode(HttpStatus.OK.toString());
//        response.setMessage("Create feedback successful!");
//        return ResponseEntity.status(HttpStatus.OK).body(response);
//    }
//
//    @GetMapping("/favourite")
//    public ResponseEntity<ResponseDto<List<FavouritePlaceDto>>> findAllFavouritePlaces(@RequestHeader("Authorization") String accessToken) {
//        if (accessToken.startsWith("Bearer ")) {
//            accessToken = accessToken.substring(7);
//        }
//        List<FavouritePlaceDto> result = placeUsecase.findAllFavouritePlaces(accessToken);
//        if(result == null) {
//            throw new RequestFailedException("Find all favourite places failed!");
//        }
//
//        ResponseDto<List<FavouritePlaceDto>> response = new ResponseDto<>();
//        response.setStatusCode(HttpStatus.OK.toString());
//        response.setMessage("Find all favourite places successful!");
//        response.setData(result);
//        return ResponseEntity.status(HttpStatus.OK).body(response);
//    }
//
//    @PostMapping("/favourite/{placeId}")
//    public ResponseEntity<ResponseDto<FavouritePlaceDto>> addFavouritePlace(@PathVariable long placeId, @RequestHeader("Authorization") String accessToken) {
//        boolean result = placeUsecase.addFavouritePlace(placeId, accessToken);
//        if (!result) {
//            throw new RequestFailedException("Add favourite place failed!");
//        }
//
//        ResponseDto<FavouritePlaceDto> response = new ResponseDto<>();
//        response.setStatusCode(HttpStatus.OK.toString());
//        response.setMessage("Add favourite place successful!");
//        return ResponseEntity.status(HttpStatus.OK).body(response);
//    }
//
//    @DeleteMapping("/favourite/list")
//    public ResponseEntity<ResponseDto<FavouritePlaceDto>> deleteFavouritePlaceByIds(@RequestBody List<Long> ids, @RequestHeader("Authorization") String accessToken) {
//        if (accessToken.startsWith("Bearer ")) {
//            accessToken = accessToken.substring(7);
//        }
//        boolean result = placeUsecase.deleteFavouritePlaceByIds(ids, accessToken);
//        if (!result) {
//            throw new RequestFailedException("Delete favourite place by ids failed!");
//        }
//
//        ResponseDto<FavouritePlaceDto> response = new ResponseDto<>();
//        response.setStatusCode(HttpStatus.OK.toString());
//        response.setMessage("Delete favourite place by ids successful!");
//        return ResponseEntity.status(HttpStatus.OK).body(response);
//    }
//
//    @GetMapping("/checkin/{placeId}")
//    public ResponseEntity<ResponseDto<CheckInDto>> findCheckInByPlaceIdAndUserId(@PathVariable long placeId, @RequestHeader("Authorization") String accessToken) {
//        if (accessToken.startsWith("Bearer ")) {
//            accessToken = accessToken.substring(7);
//        }
//
//        CheckInDto result = placeUsecase.findCheckInByPlaceIdAndUserId(placeId, accessToken);
//        if (result == null) {
//            throw new RequestFailedException("Find check in place failed!");
//        }
//
//        ResponseDto<CheckInDto> response = new ResponseDto<>();
//        response.setStatusCode(HttpStatus.OK.toString());
//        response.setMessage("Find check in place successful!");
//        response.setData(result);
//        return ResponseEntity.status(HttpStatus.OK).body(response);
//    }
//
//    @PostMapping("/checkin/{placeId}")
//    public ResponseEntity<ResponseDto<CheckInDto>> checkInPlace(@PathVariable long placeId, @RequestHeader("Authorization") String accessToken) {
//        if (accessToken.startsWith("Bearer ")) {
//            accessToken = accessToken.substring(7);
//        }
//
//        boolean result = placeUsecase.checkInPlace(placeId, accessToken);
//        if (!result) {
//            throw new RequestFailedException("Check in place failed!");
//        }
//
//        ResponseDto<CheckInDto> response = new ResponseDto<>();
//        response.setStatusCode(HttpStatus.OK.toString());
//        response.setMessage("Check in place successful!");
//        return ResponseEntity.status(HttpStatus.OK).body(response);
//    }

    @GetMapping("/categories")
    public ResponseEntity<ResponseDto<List<PlaceCategory>>> findAllPlaceCategory(@RequestHeader("Authorization") String accessToken){
        if (accessToken.startsWith("Bearer ")) {
            accessToken = accessToken.substring(7);
        }
        List<PlaceCategory> result = placeCategoryUsecase.findAllPlaceCategory();

        if(result == null) {
            throw new RequestFailedException("Find all favourite places failed!");
        }

        ResponseDto<List<PlaceCategory>> response = new ResponseDto<>();
        response.setStatusCode(HttpStatus.OK.toString());
        response.setMessage("Find all place categories successfully!");
        response.setData(result);
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @GetMapping("/categories/{placeCategoryId}")
    public ResponseEntity<ResponseDto<PlaceCategory>> findPlaceCategoryById(@PathVariable Integer placeCategoryId) {
        PlaceCategory result = placeCategoryUsecase.findPlaceCategoryById(placeCategoryId);
        if (result == null) {
            throw new RequestFailedException("Find place category failed!");
        }

        ResponseDto<PlaceCategory> response = new ResponseDto<>();
        response.setStatusCode(HttpStatus.OK.toString());
        response.setMessage("Find place category successfully!");
        response.setData(result);
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @PostMapping("/categories/{placeCategoryId}")
    public ResponseEntity<ResponseDto<PlaceCategory>> addPlaceCategory(@PathVariable long placeCategoryId,
                                                                       @RequestHeader("Authorization") String accessToken,
                                                                       @RequestBody PlaceCategoryDto placeCategoryDto){
        boolean result = placeCategoryUsecase.createPlaceCategory(placeCategoryDto);

        if (!result) {
            throw new RequestFailedException("Create place category failed!");
        }

        ResponseDto<PlaceCategory> response = new ResponseDto<>();
        response.setStatusCode(HttpStatus.OK.toString());
        response.setMessage("Create place category successfully!");
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @PutMapping("/categories/{placeCategoryId}")
    public ResponseEntity<ResponseDto<PlaceCategory>> updatePlaceCategory(@PathVariable Integer placeCategoryId,
                                                                          @RequestBody PlaceCategoryDto placeCategoryDto) {
        boolean result = placeCategoryUsecase.updatePlaceCategoryById(placeCategoryId, placeCategoryDto);

        if (!result) {
            throw new RequestFailedException("Update place category failed!");
        }

        ResponseDto<PlaceCategory> response = new ResponseDto<>();
        response.setStatusCode(HttpStatus.OK.toString());
        response.setMessage("Update place category successfully!");
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @DeleteMapping("/categories/{placeCategoryId}")
    public ResponseEntity<ResponseDto<PlaceCategory>> deletePlaceCategory(@PathVariable Integer placeCategoryId) {
        boolean result = placeCategoryUsecase.deletePlaceCategoryById(placeCategoryId);

        if (!result) {
            throw new RequestFailedException("Delete place category failed!");
        }

        ResponseDto<PlaceCategory> response = new ResponseDto<>();
        response.setStatusCode(HttpStatus.OK.toString());
        response.setMessage("Delete place category successfully!");
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }
}
