package com.project2.wanderfun.infrastructure.persistence.jpaRepository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.NoRepositoryBean;

import java.util.Optional;

@NoRepositoryBean
public interface BaseJpaRepository<Entity, Id> extends JpaRepository<Entity, Id> {
}
