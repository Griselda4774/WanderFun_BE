package com.wanderfun.infrastructurelayer.service;

import com.wanderfun.applicationlayer.mapper.ObjectMapper;
import com.wanderfun.domainlayer.repository.BaseRepository;
import com.wanderfun.applicationlayer.service.BaseService;
import com.wanderfun.applicationlayer.exception.ObjectNotFoundException;
import jakarta.transaction.Transactional;

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
    @Transactional
    public Model create(Model model) {
        return baseRepository.save(model);
    }

    @Override
    public List<Model> createAll(List<Model> models) {
        return baseRepository.saveAll(models);
    }

    @Override
    @Transactional
    public Model updateById(ID id, Model model) {
        Model existingModel = baseRepository.findById(id)
                .orElseThrow(() -> new ObjectNotFoundException(String.format("%s not found", modelClass.getSimpleName())));

        objectMapper.copyProperties(model, existingModel);
        return baseRepository.save(existingModel);
    }

    @Override
    public List<Model> updateAll(List<Model> models) {
        return baseRepository.saveAll(models);
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
