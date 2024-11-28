package com.project2.wanderfun.infrastructure.repository;

import com.project2.wanderfun.application.mapper.ObjectMapper;
import com.project2.wanderfun.application.repository.BaseRepository;
import com.project2.wanderfun.infrastructure.persistence.jpaRepository.JpaBaseRepository;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class BaseRepositoryImpl<Model, Entity, ID> implements BaseRepository<Model, ID> {
    private final JpaBaseRepository<Entity, ID> jpaBaseRepository;
    protected final ObjectMapper objectMapper;
    private final Class<Model> modelClass;
    private final  Class<Entity> entityClass;

    public BaseRepositoryImpl(JpaBaseRepository<Entity, ID> jpaBaseRepository, ObjectMapper objectMapper, Class<Model> modelClass, Class<Entity> entityClass) {
        this.jpaBaseRepository = jpaBaseRepository;
        this.objectMapper = objectMapper;
        this.modelClass = modelClass;
        this.entityClass = entityClass;
    }

    @Override
    public Model save(Model model) {
        Entity entity = objectMapper.map(model, entityClass);
        Entity savedEntity = jpaBaseRepository.save(entity);
        return objectMapper.map(savedEntity, modelClass);
    }

    @Override
    public List<Model> findAll() {
        return jpaBaseRepository.findAll().stream()
                .map(entity -> objectMapper.map(entity, modelClass))
                .collect(Collectors.toList());
    }

    @Override
    public Optional<Model> findById(ID id) {
        return jpaBaseRepository.findById(id)
                .map((entity -> objectMapper.map(entity, modelClass)));
    }

    @Override
    public void deleteById(ID id) {
        jpaBaseRepository.deleteById(id);
    }
}
