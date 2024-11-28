package com.project2.wanderfun.application.repository;

import java.util.List;
import java.util.Optional;

public interface BaseRepository<Model, ID> {
    Model save (Model model);
    List<Model> findAll();
    Optional<Model> findById(ID id);
    void deleteById(ID id);
}
