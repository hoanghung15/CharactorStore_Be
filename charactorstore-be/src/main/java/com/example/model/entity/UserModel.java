package com.example.model.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@Entity(name = "user")
public class UserModel extends UserBaseModel {
    @OneToMany(mappedBy = "user")
    private List<InteractionCharactorModel> interactionCharactorModels;
}
