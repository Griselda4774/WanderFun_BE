package com.wanderfun.infrastructurelayer.persistence.entity.addresses;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "administrative_regions")
public class AdministrativeRegionEntity {
    @Id
    public Integer id;
    public String name;
    public String nameEn;
    public String codeName;
    public String codeNameEn;

    public AdministrativeRegionEntity() {}
}
