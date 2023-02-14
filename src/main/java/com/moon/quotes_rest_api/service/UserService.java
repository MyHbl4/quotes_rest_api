package com.moon.quotes_rest_api.service;

import com.moon.quotes_rest_api.controller.dto.UserUpdateDTO;
import com.moon.quotes_rest_api.entity.User;
import java.security.Principal;
import java.util.List;

public interface UserService {

    User update(Principal principal, UserUpdateDTO user);

    User findById(long id);

    void deleteById(long id);

    List<User> findAll();
}
