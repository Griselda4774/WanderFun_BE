package com.project2.wanderfun.application.service;

import java.util.List;

public interface BaseService<Model> {
    public Model findById(Long id);
    public List<Model> findAll();
    public void create(Model model);
    public void createAll(List<Model> models);
    public void updateById(Long id, Model model);
    public void deleteById(Long id);
    public void deleteAll();
}
