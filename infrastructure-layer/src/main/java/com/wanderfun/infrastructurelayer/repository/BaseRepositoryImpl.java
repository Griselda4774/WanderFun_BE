package com.wanderfun.infrastructurelayer.repository;

import com.wanderfun.applicationlayer.mapper.ObjectMapper;
import com.wanderfun.domainlayer.repository.BaseRepository;
import com.wanderfun.infrastructurelayer.persistence.jpaRepository.JpaBaseRepository;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class BaseRepositoryImpl<Model, Entity, ID> implements BaseRepository<Model, ID> {
    private final JpaBaseRepository<Entity, ID> jpaBaseRepository;
    protected final ObjectMapper objectMapper;
    private final Class<Model> modelClass;
    private final Class<Entity> entityClass;

    public BaseRepositoryImpl(JpaBaseRepository<Entity, ID> jpaBaseRepository, ObjectMapper objectMapper, Class<Model> modelClass, Class<Entity> entityClass) {
        this.jpaBaseRepository = jpaBaseRepository;
        this.objectMapper = objectMapper;
        this.modelClass = modelClass;
        this.entityClass = entityClass;
    }

    @Override
    public Long count() {
        return jpaBaseRepository.count();
    }

    @Override
    public Model save(Model model) {
        Entity entity = objectMapper.map(model, entityClass);
        Entity savedEntity = jpaBaseRepository.save(entity);
        return objectMapper.map(savedEntity, modelClass);
    }

    @Override
    public List<Model> saveAll(List<Model> models) {
        List<Entity> entities = models.stream()
                .map(model -> objectMapper.map(model, entityClass))
                .collect(Collectors.toList());
        List<Entity> savedEntities = jpaBaseRepository.saveAll(entities);
        return savedEntities.stream()
                .map(entity -> objectMapper.map(entity, modelClass))
                .collect(Collectors.toList());
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
        Optional<Entity> entity = jpaBaseRepository.findById(id);
        entity.ifPresent(jpaBaseRepository::delete);
    }

    @Override
    public void deleteAll() {
        jpaBaseRepository.deleteAll();
    }
}
