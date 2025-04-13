package com.example.repository;

import org.springframework.data.repository.NoRepositoryBean;

@NoRepositoryBean
public interface CharactorGenreBaseRepository<T, ID> extends CharactorBaseRepository<T, ID> {
}
