package com.example.service;

import com.example.enums.RoleEnum;
import com.example.enums.StatusCodeEnum;
import com.example.exception.UserExistedException;
import com.example.model.dto.ResponseBuilder;
import com.example.model.dto.ResponseDto;
import com.example.model.dto.UserDto;
import com.example.model.dto.request.UserRequest;
import com.example.model.entity.UserModel;
import com.example.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;
    private final ModelMapper modelMapper;
    private final LanguageService languageService;

    public ResponseEntity<ResponseDto<Object>> createUserAccount(UserRequest request) {
        try {
            boolean check = userRepository.existsByEmail(request.getEmail());

            if (check) {
                throw new UserExistedException("User already exists");
            }

            UserModel userModel = modelMapper.map(request, UserModel.class);

            String rawPassword = userModel.getPassword();
            PasswordEncoder passwordEncoder = new BCryptPasswordEncoder(10);
            String newPassword = passwordEncoder.encode(rawPassword);

            userModel.setPassword(newPassword);
            userModel.setRole(RoleEnum.END_USER);

            userRepository.save(userModel);
            UserDto dto = modelMapper.map(userModel, UserDto.class);

            return ResponseBuilder.okResponse(
                    languageService.getMessage("user.create.account.successfully"),
                    StatusCodeEnum.USER0000,
                    dto
            );
        } catch (UserExistedException e) {
            return ResponseBuilder.badRequestResponse(
                    languageService.getMessage("user.existed"),
                    StatusCodeEnum.USER0003
            );
        } catch (Exception e) {
            return ResponseBuilder.badRequestResponse(
                    languageService.getMessage("user.create.account.failed"),
                    StatusCodeEnum.USER0001
            );
        }
    }
}
