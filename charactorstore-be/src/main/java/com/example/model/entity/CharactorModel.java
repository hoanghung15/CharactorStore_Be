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
@Entity(name = "charactor")
public class CharactorModel extends CharactorBaseModel {
    @OneToMany(mappedBy = "charactor")
    private List<InteractionCharactorModel> interactionCharactorModels;

    @OneToMany(mappedBy = "charactor")
    private List<CharactorGenreModel> charactorGenreModels;
}
