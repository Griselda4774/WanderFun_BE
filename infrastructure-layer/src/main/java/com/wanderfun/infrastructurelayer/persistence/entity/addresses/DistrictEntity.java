package com.wanderfun.infrastructurelayer.persistence.entity.addresses;

import jakarta.persistence.*;

@Entity
@Table(name = "districts")
public class DistrictEntity {

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
    @JoinColumn(name = "province_code", referencedColumnName = "code")
    private ProvinceEntity province;

    @ManyToOne
    @JoinColumn(name = "administrative_unit_id")
    private AdministrativeUnitEntity administrativeUnitEntity;

    public DistrictEntity() {};

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

    public ProvinceEntity getProvince() {
        return province;
    }

    public void setProvince(ProvinceEntity province) {
        this.province = province;
    }

    public AdministrativeUnitEntity getAdministrativeUnitEntity() {
        return administrativeUnitEntity;
    }

    public void setAdministrativeUnitEntity(AdministrativeUnitEntity administrativeUnitEntity) {
        this.administrativeUnitEntity = administrativeUnitEntity;
    }
}

