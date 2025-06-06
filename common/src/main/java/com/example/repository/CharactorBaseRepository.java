package com.example.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.NoRepositoryBean;

@NoRepositoryBean
public interface CharactorBaseRepository<T, ID> extends JpaRepository<T, ID> {
}
