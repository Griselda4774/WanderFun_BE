package com.project2.wanderfun.application.service;

public interface BaseService<Model> {
    public Model findById(Long id);
    public void create(Model model);
    public void updateById(Long id, Model model);
    public void deleteById(Long id);
}
