package com.wanderfun.infrastructurelayer.persistence.entity.addresses;

import jakarta.persistence.*;

@Entity
@Table(name = "wards")
public class WardEntity {

    @Id
    private String code;

    @Column(nullable = false)
    private String name;

    @Column(name = "name_en")
    private String nameEn;

    @Column(name = "full_name", nullable = false)
    private String fullName;

    @Column(name = "full_name_en")
    private String fullNameEn;

    @Column(name = "code_name")
    private String codeName;

    @ManyToOne(optional = false)
    @JoinColumn(name = "district_code", referencedColumnName = "code")
    private DistrictEntity district;

    @ManyToOne
    @JoinColumn(name = "administrative_unit_id")
    private AdministrativeUnitEntity administrativeUnit;

    public WardEntity() {};

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getNameEn() {
        return nameEn;
    }

    public void setNameEn(String nameEn) {
        this.nameEn = nameEn;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getFullNameEn() {
        return fullNameEn;
    }

    public void setFullNameEn(String fullNameEn) {
        this.fullNameEn = fullNameEn;
    }

    public String getCodeName() {
        return codeName;
    }

    public void setCodeName(String codeName) {
        this.codeName = codeName;
    }

    public DistrictEntity getDistrict() {
        return district;
    }

    public void setDistrict(DistrictEntity district) {
        this.district = district;
    }

    public AdministrativeUnitEntity getAdministrativeUnit() {
        return administrativeUnit;
    }

    public void setAdministrativeUnit(AdministrativeUnitEntity administrativeUnit) {
        this.administrativeUnit = administrativeUnit;
    }
}

