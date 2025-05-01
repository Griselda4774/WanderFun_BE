package com.wanderfun.applicationlayer.dto.trips;

import java.util.Date;
import java.util.List;

public class TripDto {
    private Long id;
    private String name;
    private Date startTime;
    private Date endTime;
    private Long userId;
    private List<TripPlaceDto> listTripPlaces;
}
