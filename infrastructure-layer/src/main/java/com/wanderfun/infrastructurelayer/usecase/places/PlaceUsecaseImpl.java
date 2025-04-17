package com.wanderfun.infrastructurelayer.usecase.places;

import com.wanderfun.applicationlayer.dto.places.PlaceCreateDto;
import com.wanderfun.applicationlayer.dto.places.PlaceDto;
import com.wanderfun.applicationlayer.exception.ObjectAlreadyExistException;
import com.wanderfun.applicationlayer.mapper.ObjectMapper;
import com.wanderfun.applicationlayer.service.addresses.AddressService;
import com.wanderfun.applicationlayer.service.place.PlaceService;
import com.wanderfun.applicationlayer.usecase.places.PlaceUsecase;
import com.wanderfun.domainlayer.model.addresses.Address;
import com.wanderfun.domainlayer.model.addresses.District;
import com.wanderfun.domainlayer.model.addresses.Province;
import com.wanderfun.domainlayer.model.addresses.Ward;
import com.wanderfun.domainlayer.model.places.Place;
import com.wanderfun.domainlayer.model.places.PlaceCategory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PlaceUsecaseImpl implements PlaceUsecase {
    private final PlaceService placeService;
    private final ObjectMapper objectMapper;
    private final AddressService addressService;

    @Autowired
    public PlaceUsecaseImpl(PlaceService placeService, ObjectMapper objectMapper, AddressService addressService) {
        this.placeService = placeService;
        this.objectMapper = objectMapper;
        this.addressService = addressService;
    }

    @Override
    public List<PlaceDto> findAll() {
        return objectMapper.mapList(placeService.findAll(), PlaceDto.class);
    }

    @Override
    public List<PlaceDto> findAllByNameContaining(String name) {
        return objectMapper.mapList(placeService.findAllByNameContaining(name), PlaceDto.class);
    }

    @Override
    public PlaceDto findById(long id) {
        return objectMapper.map(placeService.findById(id), PlaceDto.class);
    }

    @Override
    public PlaceDto findByName(String name) {
        return objectMapper.map(placeService.findByName(name), PlaceDto.class);
    }

    @Override
    public PlaceDto findByLongitudeAndLatitude(double longitude, double latitude) {
        return objectMapper.map(placeService.findByLongitudeAndLatitude(longitude, latitude), PlaceDto.class);
    }

    @Override
    public List<PlaceDto> findAllByProvinceName(String provinceName) {
        return objectMapper.mapList(placeService.findAllByProvinceName(provinceName), PlaceDto.class);
    }

    @Override
    public boolean create(PlaceCreateDto placeCreateDto) {
        Place place = objectMapper.map(placeCreateDto, Place.class);
        checkPlaceBeforeCreate(place, placeCreateDto);
        placeService.create(place);
        return true;
    }

    @Override
    public boolean createAll(List<PlaceCreateDto> placeCreateDtoList) {
        List<Place> placeList = placeCreateDtoList.stream()
                .map(placeCreateDto -> {
                    Place place = objectMapper.map(placeCreateDto, Place.class);
                    checkPlaceBeforeCreate(place, placeCreateDto);
                    return place;
                })
                .toList();


        placeService.createAll(placeList);
        return true;
    }

    @Override
    public boolean updateById(Long id, PlaceCreateDto placeCreateDto) throws ObjectAlreadyExistException {
        Place place = objectMapper.map(placeCreateDto, Place.class);

        place.getAddress().getProvince().setCode(placeCreateDto.getAddress().getProvinceCode());
        place.getAddress().getDistrict().setCode(placeCreateDto.getAddress().getDistrictCode());
        place.getAddress().getWard().setCode(placeCreateDto.getAddress().getWardCode());

        place.getCategory().setId(placeCreateDto.getCategoryId());

        Place currentPlace = placeService.findById(id);
        // Check new name
        if (place.getName() != null) {
            if (!place.getName().equals(currentPlace.getName())) {
                try {
                    placeService.findByName(place.getName());
                    throw new ObjectAlreadyExistException("This name is already used!");
                } catch (Exception ignored) {}
            }
        }

        // Check new coordinates
        if (place.getLongitude() != currentPlace.getLongitude() || place.getLatitude() != currentPlace.getLatitude()) {
            try {
                placeService.findByLongitudeAndLatitude(place.getLongitude(), place.getLatitude());
                throw new ObjectAlreadyExistException("This longitude and latitude is already used!");
            } catch (Exception ignored) {}
        }

        // Check new address
        Address existingAddress;
        if (place.getAddress() != null) {
            boolean addressExists = false;

            try {
                addressService.findExistingAddress(place.getAddress().getProvince().getCode(),
                        place.getAddress().getDistrict().getCode(),
                        place.getAddress().getWard().getCode(),
                        place.getAddress().getStreet());
                addressExists = true;
            } catch (Exception ignored) {}

            if (!addressExists) {
                if (place.getAddress().getId() != null) {
                    try {
                        addressService.updateById(place.getAddress().getId(), place.getAddress());
                    } catch (Exception e) {
                        addressService.create(place.getAddress());
                    }
                } else {
                    addressService.create(place.getAddress());
                }
            }

            existingAddress = addressService.findExistingAddress(place.getAddress().getProvince().getCode(),
                    place.getAddress().getDistrict().getCode(),
                    place.getAddress().getWard().getCode(),
                    place.getAddress().getStreet());
            place.setAddress(existingAddress);
        }

        placeService.updateById(id, place);
        return true;
    }

    @Override
    public boolean deleteById(Long id) {
        placeService.deleteById(id);
        return true;
    }

    private void checkPlaceBeforeCreate(Place place, PlaceCreateDto placeCreateDto) throws ObjectAlreadyExistException {

        place.getAddress().setProvince(new Province());
        place.getAddress().getProvince().setCode(placeCreateDto.getAddress().getProvinceCode());

        place.getAddress().setDistrict(new District());
        place.getAddress().getDistrict().setCode(placeCreateDto.getAddress().getDistrictCode());

        place.getAddress().setWard(new Ward());
        place.getAddress().getWard().setCode(placeCreateDto.getAddress().getWardCode());

        place.setCategory(new PlaceCategory());
        place.getCategory().setId(placeCreateDto.getCategoryId());

        // Check unique name
        try {
            placeService.findByName(place.getName());
            throw new ObjectAlreadyExistException("This name is already used!");
        } catch (Exception ignored) {}

        // Check unique longitude and latitude
        try {
            placeService.findByLongitudeAndLatitude(place.getLongitude(), place.getLatitude());
            throw new ObjectAlreadyExistException("This longitude and latitude is already used!");
        } catch (Exception ignored) {}

        // Check existing address
        Address existingAddress;
        if (place.getAddress() != null) {
            try {
                existingAddress = addressService.findExistingAddress(place.getAddress().getProvince().getCode(),
                        place.getAddress().getDistrict().getCode(),
                        place.getAddress().getWard().getCode(),
                        place.getAddress().getStreet());
                place.setAddress(existingAddress);
            } catch (Exception e) {
                addressService.create(place.getAddress());
                existingAddress = addressService.findExistingAddress(place.getAddress().getProvince().getCode(),
                        place.getAddress().getDistrict().getCode(),
                        place.getAddress().getWard().getCode(),
                        place.getAddress().getStreet());
                place.setAddress(existingAddress);
            }
        }
    }
}
