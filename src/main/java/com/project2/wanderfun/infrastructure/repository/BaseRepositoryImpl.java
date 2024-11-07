package com.project2.wanderfun.infrastructure.repository;

import com.project2.wanderfun.application.mapper.ObjectMapper;
import com.project2.wanderfun.application.repository.BaseRepository;
import com.project2.wanderfun.infrastructure.persistence.jpaRepository.BaseJpaRepository;
import jakarta.validation.constraints.Email;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Repository
public class BaseRepositoryImpl<Model, Entity, ID> implements BaseRepository<Model, ID> {
    private final BaseJpaRepository<Entity, ID> baseJpaRepository;
    protected final ObjectMapper objectMapper;
    private final Class<Model> modelClass;
    private final Class<Entity> entityClass;

    @Autowired
    public BaseRepositoryImpl(BaseJpaRepository<Entity, ID> baseJpaRepository, ObjectMapper objectMapper, Class<Model> modelClass, Class<Entity> entityClass) {
        this.baseJpaRepository = baseJpaRepository;
        this.objectMapper = objectMapper;
        this.modelClass = modelClass;
        this.entityClass = entityClass;
    }

    @Override
    public Model save(Model model) {
        Entity entity = (Entity) objectMapper.map(model, entityClass);
        Entity savedEntity = baseJpaRepository.save(entity);
        return (Model) objectMapper.map(savedEntity, modelClass);
    }

    @Override
    public List<Model> findAll() {
        return baseJpaRepository.findAll().stream()
                .map(entity -> (Model) objectMapper.map(entity, modelClass))
                .collect(Collectors.toList());
    }

    @Override
    public Optional<Model> findById(ID id) {
        return baseJpaRepository.findById(id)
                .map((entity -> (Model) objectMapper.map(entity, modelClass)));
    }

    @Override
    public void deleteById(ID id) {
        baseJpaRepository.deleteById(id);
    }
}
