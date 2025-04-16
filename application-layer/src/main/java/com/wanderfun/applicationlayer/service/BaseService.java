package com.wanderfun.applicationlayer.service;

import java.util.List;

public interface BaseService<Model, ID> {
   Model findById(ID id);
    List<Model> findAll();
    void create(Model model);
    void createAll(List<Model> models);
    void updateById(ID id, Model model);
    void updateAll(List<Model> models);
    void deleteById(ID id);
    void deleteAll();
}
