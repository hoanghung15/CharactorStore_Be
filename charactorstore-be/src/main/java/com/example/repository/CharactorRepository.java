package com.example.repository;

import com.example.model.entity.CharactorModel;
import org.springframework.stereotype.Repository;

@Repository
public interface CharactorRepository extends CharactorBaseRepository<CharactorModel, String> {
}
