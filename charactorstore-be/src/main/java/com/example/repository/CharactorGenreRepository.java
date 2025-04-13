package com.example.repository;

import com.example.model.entity.CharactorGenreModel;
import org.springframework.stereotype.Repository;

@Repository
public interface CharactorGenreRepository extends CharactorGenreBaseRepository<CharactorGenreModel, String> {
}
