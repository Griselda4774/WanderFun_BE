package com.wanderfun.applicationlayer.dto.addresses;

import jakarta.validation.constraints.NotBlank;

public class AddressCreateDto {
    @NotBlank
    private String provinceCode;
    @NotBlank
    private String districtCode;
    private String wardCode;
    private String street;

    public AddressCreateDto() {
    }

    public String getProvinceCode() {
        return provinceCode;
    }

    public void setProvinceCode(String provinceCode) {
        this.provinceCode = provinceCode;
    }

    public String getDistrictCode() {
        return districtCode;
    }

    public void setDistrictCode(String districtCode) {
        this.districtCode = districtCode;
    }

    public String getWardCode() {
        return wardCode;
    }

    public void setWardCode(String wardCode) {
        this.wardCode = wardCode;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }
}
