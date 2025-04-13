package com.example.repository;

import com.example.model.entity.InteractionCharactorModel;
import org.springframework.stereotype.Repository;

@Repository
public interface InteractionCharactorRepository extends InteractionCharactorBaseRepository<InteractionCharactorModel, String> {
}
