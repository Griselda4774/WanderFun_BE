package com.wanderfun.infrastructurelayer.persistence.entity.addresses;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "administrative_units")
public class AdministrativeUnitEntity {
    @Id
    public Integer id;
    public String full_name;
    public String full_name_en;
    public String short_name;
    public String short_name_en;
    public String code_name;
    public String code_name_en;

    public AdministrativeUnitEntity() {}
}
