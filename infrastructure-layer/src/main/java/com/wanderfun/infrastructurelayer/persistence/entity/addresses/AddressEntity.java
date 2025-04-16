package com.wanderfun.infrastructurelayer.persistence.entity.addresses;

import jakarta.persistence.*;

@Entity
@Table(name = "addresses")
public class AddressEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(optional = false, fetch = FetchType.EAGER)
    @JoinColumn(name = "province_code", referencedColumnName = "code", nullable = false)
    private ProvinceEntity province;

    @ManyToOne(optional = false, fetch = FetchType.EAGER)
    @JoinColumn(name = "district_code", referencedColumnName = "code", nullable = false)
    private DistrictEntity district;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "ward_code", referencedColumnName = "code")
    private WardEntity ward;

    @Column(name = "street", length = 255)
    private String street;

    public AddressEntity() {}

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public ProvinceEntity getProvince() {
        return province;
    }

    public void setProvince(ProvinceEntity province) {
        this.province = province;
    }

    public DistrictEntity getDistrict() {
        return district;
    }

    public void setDistrict(DistrictEntity district) {
        this.district = district;
    }

    public WardEntity getWard() {
        return ward;
    }

    public void setWard(WardEntity ward) {
        this.ward = ward;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }
}

