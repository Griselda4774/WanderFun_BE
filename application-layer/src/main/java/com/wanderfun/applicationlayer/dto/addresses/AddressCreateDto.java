package com.wanderfun.applicationlayer.dto.addresses;

import jakarta.validation.constraints.NotBlank;

public class AddressCreateDto {
    @NotBlank
    private String provinceName;
    @NotBlank
    private String districtName;
    private String wardName;
    private String street;

    public AddressCreateDto() {
    }

    public String getProvinceName() {
        return provinceName;
    }

    public void setProvinceName(String provinceName) {
        this.provinceName = provinceName;
    }

    public String getDistrictName() {
        return districtName;
    }

    public void setDistrictName(String districtName) {
        this.districtName = districtName;
    }

    public String getWardName() {
        return wardName;
    }

    public void setWardName(String wardName) {
        this.wardName = wardName;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }
}
