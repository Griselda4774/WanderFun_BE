package com.wanderfun.infrastructurelayer.usecase;

import com.wanderfun.applicationlayer.dto.places.FullPlaceDto;
import com.wanderfun.applicationlayer.dto.places.PlaceCreateDto;
import com.wanderfun.applicationlayer.dto.places.PlaceDetailDto;
import com.wanderfun.applicationlayer.dto.places.PlaceDto;
import com.wanderfun.applicationlayer.exception.ObjectAlreadyExistException;
import com.wanderfun.applicationlayer.exception.ObjectNotFoundException;
import com.wanderfun.applicationlayer.mapper.ObjectMapper;
import com.wanderfun.applicationlayer.service.addresses.AddressService;
import com.wanderfun.applicationlayer.service.addresses.DistrictService;
import com.wanderfun.applicationlayer.service.addresses.ProvinceService;
import com.wanderfun.applicationlayer.service.addresses.WardService;
import com.wanderfun.applicationlayer.service.place.PlaceDetailService;
import com.wanderfun.applicationlayer.service.place.PlaceService;
import com.wanderfun.applicationlayer.usecase.PlaceUsecase;
import com.wanderfun.domainlayer.model.addresses.Address;
import com.wanderfun.domainlayer.model.addresses.District;
import com.wanderfun.domainlayer.model.addresses.Province;
import com.wanderfun.domainlayer.model.addresses.Ward;
import com.wanderfun.domainlayer.model.places.Place;
import com.wanderfun.domainlayer.model.places.PlaceCategory;
import com.wanderfun.domainlayer.model.places.PlaceDetail;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.function.Function;
import java.util.stream.Collectors;

@Service
public class PlaceUsecaseImpl implements PlaceUsecase {
    private final PlaceService placeService;
    private final ObjectMapper objectMapper;
    private final AddressService addressService;
    private final PlaceDetailService placeDetailService;
    private final ProvinceService provinceService;
    private final DistrictService districtService;
    private final WardService wardService;

    @Autowired
    public PlaceUsecaseImpl(PlaceService placeService, ObjectMapper objectMapper, AddressService addressService, PlaceDetailService placeDetailService, ProvinceService provinceService, DistrictService districtService, WardService wardService) {
        this.placeService = placeService;
        this.objectMapper = objectMapper;
        this.addressService = addressService;
        this.placeDetailService = placeDetailService;
        this.provinceService = provinceService;
        this.districtService = districtService;
        this.wardService = wardService;
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
    public FullPlaceDto findById(Long id) throws ObjectNotFoundException {
        FullPlaceDto fullPlaceDto = objectMapper.map(placeService.findById(id), FullPlaceDto.class);

        try {
            PlaceDetail placeDetail = placeDetailService.findByPlaceId(id);
            PlaceDetailDto placeDetailDto = objectMapper.map(placeDetail, PlaceDetailDto.class);
            fullPlaceDto.setPlaceDetail(placeDetailDto);
        } catch (ObjectNotFoundException e) {
            fullPlaceDto.setPlaceDetail(new PlaceDetailDto());
        }

        return fullPlaceDto;
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
        List<Place> places = placeService.findAllByProvinceName(provinceName);
        List<PlaceDto> placeDtoList = objectMapper.mapList(places, PlaceDto.class);
        return placeDtoList;
    }

    @Override
    public boolean create(PlaceCreateDto placeCreateDto) {
        Place place = objectMapper.map(placeCreateDto, Place.class);
        checkPlaceBeforeCreate(place, placeCreateDto);
        Place savedPlace = placeService.create(place);
        handlePlaceDetail(savedPlace, placeCreateDto);
        return true;
    }

    @Override
    public boolean createAll(List<PlaceCreateDto> placeCreateDtoList) {
        Map<String, PlaceCreateDto> placeDtoMap = placeCreateDtoList.stream()
                .collect(Collectors.toMap(
                        PlaceCreateDto::getName,
                        Function.identity()
                ));

        List<Place> placeList = placeCreateDtoList.stream()
                .map(placeCreateDto -> {
                    try {
                        Place place = objectMapper.map(placeCreateDto, Place.class);
                        checkPlaceBeforeCreate(place, placeCreateDto);
                        return place;
                    } catch (ObjectAlreadyExistException | ObjectNotFoundException e) {
                        return null;
                    }
                })
                .filter(Objects::nonNull)
                .toList();

        List<Place> savedPlaceList = placeService.createAll(placeList);
        savedPlaceList.forEach(place -> {
            PlaceCreateDto dto = placeDtoMap.get(place.getName());
            if (dto != null) {
                handlePlaceDetail(place, dto);
            }
        });

        return true;
    }

    @Override
    public boolean updateById(Long id, PlaceCreateDto placeCreateDto) throws ObjectAlreadyExistException {
        Place place = objectMapper.map(placeCreateDto, Place.class);

        setUpPlaceInputData(place, placeCreateDto);

        Place currentPlace = placeService.findById(id);
        // Check new name
        if (place.getName() != null) {
            if (!place.getName().equals(currentPlace.getName())) {
                try {
                    placeService.findByName(place.getName());
                    throw new ObjectAlreadyExistException("This name is already used!");
                } catch (ObjectNotFoundException ignored) {}
            }
        }

        // Check new coordinates
        if (place.getLongitude() != currentPlace.getLongitude() || place.getLatitude() != currentPlace.getLatitude()) {
            try {
                placeService.findByLongitudeAndLatitude(place.getLongitude(), place.getLatitude());
                throw new ObjectAlreadyExistException("This longitude and latitude is already used!");
            } catch (ObjectNotFoundException ignored) {}
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
            } catch (ObjectNotFoundException ignored) {}

            if (!addressExists) {
                if (place.getAddress().getId() != null) {
                    try {
                        addressService.updateById(place.getAddress().getId(), place.getAddress());
                    } catch (ObjectNotFoundException e) {
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

        Place savedPlace = placeService.updateById(id, place);
        handlePlaceDetail(savedPlace, placeCreateDto);

        return true;
    }

    @Override
    public boolean deleteById(Long id) {
        placeService.deleteById(id);
        return true;
    }

    private void setUpPlaceInputData(Place place, PlaceCreateDto placeCreateDto) {
        if (place.getAddress() == null) {
            place.setAddress(new Address());
        }

        String provinceCode = provinceService.findByName(placeCreateDto.getAddress().getProvinceName()).getCode();
        place.getAddress().setProvince(new Province());
        place.getAddress().getProvince().setCode(provinceCode);

        String districtCode = districtService.findByNameAndProvinceCode(placeCreateDto.getAddress().getDistrictName(), provinceCode).getCode();
        place.getAddress().setDistrict(new District());
        place.getAddress().getDistrict().setCode(districtCode);

        String wardCode;
        try {
            wardCode = wardService.findByNameAndDistrictCode(placeCreateDto.getAddress().getWardName(), districtCode).getCode();
        } catch (ObjectNotFoundException e) {
            wardCode = null;
        }

        place.getAddress().setWard(new Ward());
        place.getAddress().getWard().setCode(wardCode);

        String street = placeCreateDto.getAddress().getStreet();
        place.getAddress().setStreet(StringUtils.hasText(street) ? street : null);

        place.setCategory(new PlaceCategory());
        place.getCategory().setId(placeCreateDto.getCategoryId());
    }

    private void checkPlaceBeforeCreate(Place place, PlaceCreateDto placeCreateDto) throws ObjectAlreadyExistException {
        setUpPlaceInputData(place, placeCreateDto);

        // Check unique name
        try {
            placeService.findByName(place.getName());
            throw new ObjectAlreadyExistException("This name is already used!");
        } catch (ObjectNotFoundException ignored) {}

        // Check unique longitude and latitude
        try {
            placeService.findByLongitudeAndLatitude(place.getLongitude(), place.getLatitude());
            throw new ObjectAlreadyExistException("This longitude and latitude is already used!");
        } catch (ObjectNotFoundException ignored) {}

        // Check existing address
        Address existingAddress;
        if (place.getAddress() != null) {
            try {
                existingAddress = addressService.findExistingAddress(place.getAddress().getProvince().getCode(),
                        place.getAddress().getDistrict().getCode(),
                        place.getAddress().getWard().getCode(),
                        place.getAddress().getStreet());
                place.setAddress(existingAddress);
            } catch (ObjectNotFoundException e) {
                addressService.create(place.getAddress());
                existingAddress = addressService.findExistingAddress(place.getAddress().getProvince().getCode(),
                        place.getAddress().getDistrict().getCode(),
                        place.getAddress().getWard().getCode(),
                        place.getAddress().getStreet());
                place.setAddress(existingAddress);
            }
        }
    }

    private void handlePlaceDetail(Place savedPlace, PlaceCreateDto placeCreateDto) {
        PlaceDetail placeDetail = objectMapper.map(placeCreateDto.getPlaceDetailDto(), PlaceDetail.class);
        placeDetail.setPlaceId(savedPlace.getId());
        try {
            PlaceDetail existingPlaceDetail = placeDetailService.findByPlaceId(savedPlace.getId());
            placeDetailService.updateById(existingPlaceDetail.getId(), placeDetail);
        } catch (ObjectNotFoundException e) {
            placeDetailService.create(placeDetail);
        }
    }
}
