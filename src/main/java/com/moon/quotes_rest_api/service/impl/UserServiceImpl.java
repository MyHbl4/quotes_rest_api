package com.moon.quotes_rest_api.service.impl;

import static com.moon.quotes_rest_api.utils.StringConstants.COULD_NOT_UPDATED;
import static com.moon.quotes_rest_api.utils.StringConstants.NF;

import com.moon.quotes_rest_api.controller.dto.UserUpdateDTO;
import com.moon.quotes_rest_api.entity.User;
import com.moon.quotes_rest_api.error.NotFoundException;
import com.moon.quotes_rest_api.error.ValidationException;
import com.moon.quotes_rest_api.repository.UserRepository;
import com.moon.quotes_rest_api.service.UserService;
import java.security.Principal;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    @Override
    public List<User> findAll() {
        return userRepository.findAll();
    }

    @Override
    public User update(Principal principal, UserUpdateDTO user) {

        User oldUser = userRepository.findByEmail(principal.getName())
            .orElseThrow(() -> new NotFoundException(NF.value));
        oldUser.setName(user.getName());
        try {
            return userRepository.save(oldUser);
        } catch (Exception e) {
            throw new ValidationException(COULD_NOT_UPDATED.value);
        }

    }

    @Override
    public User findById(long id) {
        return userRepository.findById(id).orElseThrow(() -> new NotFoundException(NF.value));
    }

    @Override
    public void deleteById(long id) {
        userRepository.deleteById(id);
    }
}
