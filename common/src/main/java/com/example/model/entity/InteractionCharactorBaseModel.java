package com.example.model.entity;

import com.example.enums.InteractionTypeEnum;
import jakarta.persistence.Column;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Id;
import jakarta.persistence.MappedSuperclass;
import lombok.Data;
import org.hibernate.annotations.UuidGenerator;

@Data
@MappedSuperclass
public class InteractionCharactorBaseModel extends BaseModel{
    @Id
    @UuidGenerator
    @Column(name = "id", length = 64, updatable = false, nullable = false)
    private String id;

    @Column(name = "interaction_count",  columnDefinition = "int default 0")
    private int interactionCount;

    @Column(name = "action_type")
    @Enumerated(EnumType.ORDINAL)
    private InteractionTypeEnum actionType;
}
