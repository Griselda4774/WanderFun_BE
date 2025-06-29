package com.wanderfun.domainlayer.repository;

import java.util.List;
import java.util.Optional;

public interface BaseRepository<Model, ID> {
    Long count();
    Model save (Model model);
    List<Model> saveAll(List<Model> models);
    List<Model> findAll();
    Optional<Model> findById(ID id);
    void deleteById(ID id);
    void deleteAll();
}
