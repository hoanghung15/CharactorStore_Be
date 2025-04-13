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
@Entity(name = "genre")
public class GenreModel extends GenreBaseModel {
    @OneToMany(mappedBy = "genre")
    private List<CharactorGenreModel> charactorGenreModels;
}
