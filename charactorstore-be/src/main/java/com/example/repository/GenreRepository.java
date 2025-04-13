package com.example.repository;

import com.example.model.entity.GenreModel;
import org.springframework.stereotype.Repository;

@Repository
public interface GenreRepository extends GenreBaseRepository<GenreModel, String> {
}
