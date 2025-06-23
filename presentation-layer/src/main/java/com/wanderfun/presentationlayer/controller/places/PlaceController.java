package com.wanderfun.presentationlayer.controller.places;
//
//import com.wanderfun.applicationlayer.dto.checkin.CheckInDto;
//import com.wanderfun.applicationlayer.dto.favouriteplace.FavouritePlaceDto;
//import com.wanderfun.applicationlayer.dto.feedback.FeedbackCreateDto;
//import com.wanderfun.applicationlayer.dto.feedback.FeedbackDto;
//import com.wanderfun.applicationlayer.dto.place.PlaceCreateDto;
//import com.wanderfun.applicationlayer.dto.place.PlaceDto;
//import com.wanderfun.applicationlayer.dto.ResponseDto;
//import com.wanderfun.applicationlayer.dto.place.PlaceMiniDto;
import com.wanderfun.applicationlayer.dto.places.*;
import com.wanderfun.applicationlayer.dto.ResponseDto;
import com.wanderfun.applicationlayer.usecase.places.PlaceUsecase;
import com.wanderfun.presentationlayer.exception.RequestFailedException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("wanderfun/place")
public class PlaceController {
    private final PlaceUsecase placeUsecase;

    @Autowired
    public PlaceController(PlaceUsecase placeUsecase) {
        this.placeUsecase = placeUsecase;
    }


    // Place-related endpoints
    @GetMapping("")
    public ResponseEntity<ResponseDto<List<PlaceDto>>> findAllPlaces() {
        List<PlaceDto> result = placeUsecase.findAll();
        if(result == null) {
            throw new RequestFailedException("Find all places failed!");
        }

        ResponseDto<List<PlaceDto>> response = new ResponseDto<>();
        response.setStatusCode(HttpStatus.OK.toString());
        response.setMessage("Find all places successful!");
        response.setData(result);
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @GetMapping("/search/{name}")
    public ResponseEntity<ResponseDto<List<PlaceDto>>> findAllPlacesByNameContaining(@PathVariable String name) {
        List<PlaceDto> result = placeUsecase.findAllByNameContaining(name);
        if (result == null) {
            throw new RequestFailedException("Find all places by name containing failed!");
        }

        ResponseDto<List<PlaceDto>> response = new ResponseDto<>();
        response.setStatusCode(HttpStatus.OK.toString());
        response.setMessage("Find all places by name containing successful!");
        response.setData(result);
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @GetMapping("/search/province/{provinceName}")
    public ResponseEntity<ResponseDto<List<PlaceDto>>> findAllPlacesByProvinceName(@PathVariable String provinceName) {
        List<PlaceDto> result = placeUsecase.findAllByProvinceName(provinceName);
        if (result == null) {
            throw new RequestFailedException("Find all places by province name failed!");
        }

        ResponseDto<List<PlaceDto>> response = new ResponseDto<>();
        response.setStatusCode(HttpStatus.OK.toString());
        response.setMessage("Find all places by province name successful!");
        response.setData(result);
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ResponseDto<FullPlaceDto>> findPlaceById(@PathVariable Long id) {
        FullPlaceDto result = placeUsecase.findById(id);
        if (result == null) {
            throw new RequestFailedException("Find place failed!");
        }

        ResponseDto<FullPlaceDto> response = new ResponseDto<>();
        response.setStatusCode(HttpStatus.OK.toString());
        response.setMessage("Find place successful!");
        response.setData(result);
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @GetMapping("/name/{name}")
    public ResponseEntity<ResponseDto<PlaceDto>> findPlaceByName(@PathVariable String name) {
        PlaceDto result = placeUsecase.findByName(name);
        if (result == null) {
            throw new RequestFailedException("Find place by name failed!");
        }

        ResponseDto<PlaceDto> response = new ResponseDto<>();
        response.setStatusCode(HttpStatus.OK.toString());
        response.setMessage("Find place by name successful!");
        response.setData(result);
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @GetMapping("/coordinates")
    public ResponseEntity<ResponseDto<PlaceDto>> findPlaceByLongitudeAndLatitude(@RequestParam double longitude,
                                                                             @RequestParam double latitude) {
        PlaceDto result = placeUsecase.findByLongitudeAndLatitude(longitude, latitude);
        if (result == null) {
            throw new RequestFailedException("Find place by longitude and latitude failed!");
        }

        ResponseDto<PlaceDto> response = new ResponseDto<>();
        response.setStatusCode(HttpStatus.OK.toString());
        response.setMessage("Find place by longitude and latitude successful!");
        response.setData(result);
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @PostMapping("")
    public ResponseEntity<ResponseDto<PlaceDto>> createPlace(@RequestBody PlaceCreateDto placeCreateDto) {
        boolean result = placeUsecase.create(placeCreateDto);
        if (!result) {
            throw new RequestFailedException("Create place failed!");
        }

        ResponseDto<PlaceDto> response = new ResponseDto<>();
        response.setStatusCode(HttpStatus.OK.toString());
        response.setMessage("Create place successful!");
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @PostMapping("/all")
    public ResponseEntity<ResponseDto<List<PlaceDto>>> createAllPlaces(@RequestBody List<PlaceCreateDto> placeCreateDtos) {
        boolean result = placeUsecase.createAll(placeCreateDtos);
        if (!result) {
            throw new RequestFailedException("Create all place failed!");
        }

        ResponseDto<List<PlaceDto>> response = new ResponseDto<>();
        response.setStatusCode(HttpStatus.OK.toString());
        response.setMessage("Create all place successful!");
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ResponseDto<PlaceDto>> updatePlaceById(@PathVariable Long id, @RequestBody PlaceCreateDto placeCreateDto) {
        boolean result = placeUsecase.updateById(id, placeCreateDto);
        if (!result) {
            throw new RequestFailedException("Update place failed!");
        }

        ResponseDto<PlaceDto> response = new ResponseDto<>();
        response.setStatusCode(HttpStatus.OK.toString());
        response.setMessage("Update place successful!");
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ResponseDto<PlaceDto>> deletePlaceById(@PathVariable Long id) {
        boolean result = placeUsecase.deleteById(id);
        if (!result) {
            throw new RequestFailedException("Delete place failed!");
        }

        ResponseDto<PlaceDto> response = new ResponseDto<>();
        response.setStatusCode(HttpStatus.OK.toString());
        response.setMessage("Delete place successful!");
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @DeleteMapping("")
    public ResponseEntity<ResponseDto<PlaceDto>> deleteAllPlaces() {
        boolean result = placeUsecase.deleteAll();
        if (!result) {
            throw new RequestFailedException("Delete all places failed!");
        }

        ResponseDto<PlaceDto> response = new ResponseDto<>();
        response.setStatusCode(HttpStatus.OK.toString());
        response.setMessage("Delete all places successful!");
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

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
}
