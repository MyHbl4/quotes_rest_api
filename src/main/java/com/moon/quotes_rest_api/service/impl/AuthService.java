package com.moon.quotes_rest_api.service.impl;

import static com.moon.quotes_rest_api.utils.StringConstants.COULD_NOT_SAVED;

import com.moon.quotes_rest_api.controller.dto.AuthRequest;
import com.moon.quotes_rest_api.controller.dto.AuthResponse;
import com.moon.quotes_rest_api.entity.Role;
import com.moon.quotes_rest_api.entity.User;
import com.moon.quotes_rest_api.error.ValidationException;
import com.moon.quotes_rest_api.repository.RoleRepository;
import com.moon.quotes_rest_api.repository.UserRepository;
import com.moon.quotes_rest_api.security.JwtTokenUtil;
import java.util.HashSet;
import java.util.Set;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthService {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private RoleRepository roleRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private JwtTokenUtil jwtTokenUtil;
    @Autowired
    AuthenticationManager authManager;

    public AuthResponse login(AuthRequest request) {
        try {
            Authentication authentication = authManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                    request.getEmail(), request.getPassword())
            );

            User user = (User) authentication.getPrincipal();
            String accessToken = jwtTokenUtil.generateAccessToken(user);
            AuthResponse response = new AuthResponse();
            response.setEmail(request.getEmail());
            response.setAccessToken(accessToken);
            return response;
        } catch (Exception e) {
            throw new ValidationException("Invalid username or password");
        }
    }

    public User userRegistration(User user) {
        User userFromDb = userRepository.findByEmail(user.getEmail()).orElse(null);
        if (userFromDb != null) {
            throw new ValidationException("User already registered");
        }
        Role role = roleRepository.findByName("ROLE_USER");
        Set<Role> roles = new HashSet<>();
        roles.add(role);
        user.setRoles(roles);
        String rawPassword = user.getPassword();
        String encodedPassword = passwordEncoder.encode(rawPassword);
        user.setName(user.getName());
        user.setEmail(user.getEmail());
        user.setPassword(encodedPassword);
        user.setEnabled(true);
        try {
            return userRepository.save(user);
        } catch (Exception e) {
            throw new ValidationException(COULD_NOT_SAVED.value);
        }
    }
}