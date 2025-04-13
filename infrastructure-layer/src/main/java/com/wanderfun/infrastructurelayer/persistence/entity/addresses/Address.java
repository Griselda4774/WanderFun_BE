package com.wanderfun.infrastructurelayer.persistence.entity.addresses;

public class Address {
    private Long id;
    private Province province;
    private District district;
    private Ward ward;
    private String street;
    private AddressTargetType targetType;
    private Long targetId;

    public Address() {
    }
}
