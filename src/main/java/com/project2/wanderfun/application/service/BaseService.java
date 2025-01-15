package com.project2.wanderfun.application.service;

import java.util.List;

public interface BaseService<Model> {
   Model findById(Long id);
    List<Model> findAll();
    void create(Model model);
    void createAll(List<Model> models);
    void updateById(Long id, Model model);
    void deleteById(Long id);
    void deleteAll();
}
