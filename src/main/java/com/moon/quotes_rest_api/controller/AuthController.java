package com.moon.quotes_rest_api.controller;

import com.moon.quotes_rest_api.controller.dto.AuthRequest;
import com.moon.quotes_rest_api.controller.dto.AuthResponse;
import com.moon.quotes_rest_api.controller.dto.UserDTO;
import com.moon.quotes_rest_api.controller.dto.UserNewDTO;
import com.moon.quotes_rest_api.entity.User;
import com.moon.quotes_rest_api.mapper.UserMapper;
import com.moon.quotes_rest_api.service.impl.AuthService;
import io.swagger.annotations.Api;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/auth")
@Slf4j
@Api(tags = "Authentication")
public class AuthController {

    private final AuthService authService;

    @PutMapping("/register")
    public UserDTO createUser(@RequestBody UserNewDTO user) {
        log.info("createUser - User created with email: {}", user.getEmail());
        User createdUser = authService.userRegistration(
            UserMapper.INSTANCE.toUserFromUserNewDto(user));
        return UserMapper.INSTANCE.toUserDtoFromUser(createdUser);
    }

    @PostMapping("/login")
    public AuthResponse login(@RequestBody AuthRequest requestDto) {
        log.info("login - login user with email: {}", requestDto.getEmail());
        return authService.login(requestDto);
    }
}
