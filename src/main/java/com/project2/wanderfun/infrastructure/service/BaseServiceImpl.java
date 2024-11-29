package com.project2.wanderfun.infrastructure.service;

import com.project2.wanderfun.application.mapper.ObjectMapper;
import com.project2.wanderfun.application.repository.BaseRepository;
import com.project2.wanderfun.application.service.BaseService;
import com.project2.wanderfun.presentation.exception.ObjectNotFoundException;

import java.util.List;

public class BaseServiceImpl<Model> implements BaseService<Model> {
    private final Class<Model> modelClass;
    private final BaseRepository<Model, Long> baseRepository;
    protected final ObjectMapper objectMapper;

    public BaseServiceImpl(BaseRepository<Model, Long> baseRepository, ObjectMapper objectMapper, Class<Model> modelClass) {
        this.modelClass = modelClass;
        this.baseRepository = baseRepository;
        this.objectMapper = objectMapper;
    }

    @Override
    public Model findById(Long id) {
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
    public void updateById(Long id, Model model) {
        Model existingModel = baseRepository.findById(id)
                .orElseThrow(() -> new ObjectNotFoundException(String.format("%s not found", modelClass.getSimpleName())));

        objectMapper.copyProperties(model, existingModel);
        baseRepository.save(existingModel);
    }

    @Override
    public void deleteById(Long id) {
        Model existingModel = baseRepository.findById(id).orElse(null);
        if (existingModel != null) {
            baseRepository.deleteById(id);
        }
    }

    @Override
    public void deleteAll() {
        baseRepository.deleteAll();
    }
}
