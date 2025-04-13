package com.example.model.entity;

import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.MappedSuperclass;
import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;
import org.hibernate.annotations.GenericGenerator;

@Data
@MappedSuperclass
@FieldDefaults(level = AccessLevel.PRIVATE)
public class GenreBaseModel extends BaseModel{
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "id", columnDefinition = "VARCHAR(64)")
    private String id;

    @Column(name = "name", columnDefinition = "VARCHAR(64)")
    private String name;

    @Column(name = "image", columnDefinition = "VARCHAR(100)")
    private String image;

    @Column(name = "url_slug", columnDefinition = "VARCHAR(100)")
    private String urlSlug;
}
