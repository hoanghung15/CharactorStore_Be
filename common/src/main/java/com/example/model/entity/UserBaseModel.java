package com.example.model.entity;

import com.example.enums.CountryEnum;
import com.example.enums.GenderEnum;
import com.example.enums.ProviderEnum;
import com.example.enums.RoleEnum;
import com.example.enums.UserStatusEnum;
import jakarta.persistence.Column;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.MappedSuperclass;
import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;
import org.hibernate.annotations.GenericGenerator;

import java.time.LocalDate;

@Data
@MappedSuperclass
@FieldDefaults(level = AccessLevel.PRIVATE)
public class UserBaseModel extends BaseModel {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    String id;

    @Column(name = "email", columnDefinition = "VARCHAR(100)")
    String email;

    @Column(name = "first_name", columnDefinition = "VARCHAR(255)")
    String firstName;

    @Column(name = "password", columnDefinition = "VARCHAR(255)")
    String password;

    @Column(name = "last_name", columnDefinition = "VARCHAR(255)")
    String lastName;

    @Column(name = "phone", columnDefinition = "VARCHAR(255)")
    String phone;

    @Column(name = "avatar", columnDefinition = "VARCHAR(255)")
    String avatar;

    @Column(name = "gender", columnDefinition = "TINYINT(4)")
    private GenderEnum gender;

    @Column(name = "status", columnDefinition = "TINYINT(4)")
    private UserStatusEnum status;

    @Column(name = "birthday", columnDefinition = "DATETIME")
    private LocalDate birthday;

    @Column(name = "role", columnDefinition = "TINYINT(4)")
    private RoleEnum role;

    @Column(name = "language", columnDefinition = "VARCHAR(25)")
    private String language;

    @Column(name = "provider", columnDefinition = "VARCHAR(40)")
    @Enumerated(EnumType.STRING)
    private ProviderEnum provider;

    @Column(name = "country", columnDefinition = "VARCHAR(50)")
    @Enumerated(EnumType.STRING)
    private CountryEnum country;
}
