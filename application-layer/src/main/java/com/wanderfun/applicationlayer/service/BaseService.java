package com.wanderfun.applicationlayer.service;

import java.util.List;

public interface BaseService<Model, ID> {
 Long count();
 Model findById(ID id);
 List<Model> findAll();
 Model create(Model model);
 List<Model> createAll(List<Model> models);
 Model updateById(ID id, Model model);
 List<Model> updateAll(List<Model> models);
 void deleteById(ID id);
 void deleteAll();
}
