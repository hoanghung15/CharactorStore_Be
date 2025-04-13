package com.example.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.NoRepositoryBean;

@NoRepositoryBean
public interface InteractionCharactorBaseRepository<T, ID> extends JpaRepository<T, ID> {
}
