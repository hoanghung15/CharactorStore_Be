package com.example.repository;

import com.example.model.entity.InvalidatedTokenModel;
import org.springframework.stereotype.Repository;

@Repository
public interface InvalidateRepository extends InvalidatedTokenBaseRepository<InvalidatedTokenModel, String> {
}
