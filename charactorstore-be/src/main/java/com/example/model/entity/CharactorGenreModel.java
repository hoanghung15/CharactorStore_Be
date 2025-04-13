package com.example.model.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@Entity(name = "charactor_genre")
public class CharactorGenreModel extends CharactorGenreBaseModel {
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "genre_id", nullable = false)
    private GenreModel genre;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "charactor_id", nullable = false)
    private CharactorModel charactor;
}
