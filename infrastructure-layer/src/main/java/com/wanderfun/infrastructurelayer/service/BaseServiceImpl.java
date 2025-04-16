package com.wanderfun.infrastructurelayer.service;

import com.wanderfun.applicationlayer.mapper.ObjectMapper;
import com.wanderfun.domainlayer.repository.BaseRepository;
import com.wanderfun.applicationlayer.service.BaseService;
import com.wanderfun.applicationlayer.exception.ObjectNotFoundException;

import java.util.List;

public class BaseServiceImpl<Model, ID> implements BaseService<Model, ID> {
    private final Class<Model> modelClass;
    private final BaseRepository<Model, ID> baseRepository;
    protected final ObjectMapper objectMapper;

    public BaseServiceImpl(BaseRepository<Model, ID> baseRepository, ObjectMapper objectMapper, Class<Model> modelClass) {
        this.modelClass = modelClass;
        this.baseRepository = baseRepository;
        this.objectMapper = objectMapper;
    }

    @Override
    public Model findById(ID id) {
        return baseRepository.findById(id).orElseThrow(() -> new ObjectNotFoundException(String.format("%s not found", modelClass.getSimpleName())));
    }

    @Override
    public List<Model> findAll() {
        return baseRepository.findAll();
    }

    @Override
    public void create(Model model) {
        baseRepository.save(model);
    }

    @Override
    public void createAll(List<Model> models) {
        baseRepository.saveAll(models);
    }

    @Override
    public void updateById(ID id, Model model) {
        Model existingModel = baseRepository.findById(id)
                .orElseThrow(() -> new ObjectNotFoundException(String.format("%s not found", modelClass.getSimpleName())));

        objectMapper.copyProperties(model, existingModel);
        baseRepository.save(existingModel);
    }

    @Override
    public void updateAll(List<Model> models) {
        baseRepository.saveAll(models);
    }

    @Override
    public void deleteById(ID id) {
        baseRepository.deleteById(id);
    }

    @Override
    public void deleteAll() {
        baseRepository.deleteAll();
    }
}
