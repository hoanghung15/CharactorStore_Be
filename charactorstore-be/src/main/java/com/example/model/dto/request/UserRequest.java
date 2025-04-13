package com.example.model.dto.request;

import com.example.annotation.IsEmailCustom;
import jakarta.validation.constraints.NotNull;
import lombok.AccessLevel;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class UserRequest {
    @NotNull
    String firstName;

    @NotNull
    String lastName;

    String phone;

    @IsEmailCustom
    String email;

    @NotNull
    LocalDate birthday;

    @NotNull
    String password;
}
