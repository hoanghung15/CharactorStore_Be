package com.example.repository;

import com.example.model.entity.UserModel;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends UserBaseRepository<UserModel, String> {
    boolean existsByEmail(String email);

    Optional<UserModel> findByEmail(String email);
}
