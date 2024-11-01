package com.project2.wanderfun.domain.model;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

public class Place {
    private BigDecimal longitude;
    private BigDecimal latitude;
    private String address;
    private String name;
    private String iconUrl;
    private String coverImageUrl;
    private List<DescriptionSection> description;
    private int checkInPoint;
    private float checkInRange;
    private PlaceCategory category;
    private Date timeOpen;
    private Date timeClose;
    private String alternativeName;
    private String operator;
    private String link;
    private List<String> imageUrls;
    private List<Feedback> feedbacks;
}
