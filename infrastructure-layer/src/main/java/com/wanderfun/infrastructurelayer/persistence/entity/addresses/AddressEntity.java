package com.wanderfun.infrastructurelayer.persistence.entity.addresses;

import jakarta.persistence.*;

@Entity
@Table(name = "addresses")
public class AddressEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private ProvinceEntity provinceEntity;
    private DistrictEntity district;
    private WardEntity wardEntity;
    private String street;
    private String targetType;
    private Long targetId;

    public Address() {
    }
}
