package com.example.model.entity;

import com.example.enums.StatusCartEnum;
import jakarta.persistence.Column;
import jakarta.persistence.Id;
import jakarta.persistence.MappedSuperclass;
import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;
import org.hibernate.annotations.UuidGenerator;

@MappedSuperclass
@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
public class CartBaseModel extends BaseModel {
    @Id
    @UuidGenerator
    @Column(name = "id", length = 64, updatable = false, nullable = false)
    String id;

    @Column(name = "status", columnDefinition = "TINYINT(4)")
    StatusCartEnum status;
}
