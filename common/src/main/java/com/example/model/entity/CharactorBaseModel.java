package com.example.model.entity;

import com.example.enums.FileTypeEnum;
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
public class CharactorBaseModel extends BaseModel {
    @Id
   @GeneratedValue(strategy = GenerationType.UUID)
    String id;

    @Column(name = "name", columnDefinition = "VARCHAR(255)")
    String name;

    @Column(name = "url_slug", columnDefinition = "VARCHAR(255)")
    String urlSlug;

    @Column(name = "image", columnDefinition = "VARCHAR(255)")
    String image;

    @Column(name = "size", columnDefinition = "DOUBLE")
    Double size;

    @Column(name = "type_file", columnDefinition = "TINYINT(4)")
    FileTypeEnum fileType;
}
