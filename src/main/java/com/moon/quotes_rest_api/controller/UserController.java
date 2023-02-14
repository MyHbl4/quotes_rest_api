package com.moon.quotes_rest_api.controller;

import com.moon.quotes_rest_api.controller.dto.QuoteDTO;
import com.moon.quotes_rest_api.controller.dto.UserDTO;
import com.moon.quotes_rest_api.controller.dto.UserUpdateDTO;
import com.moon.quotes_rest_api.mapper.QuoteMapper;
import com.moon.quotes_rest_api.mapper.UserMapper;
import com.moon.quotes_rest_api.service.QuoteService;
import com.moon.quotes_rest_api.service.UserService;
import io.swagger.annotations.Api;
import java.security.Principal;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/users")
@Api(tags = "Users")
public class UserController {

    private final UserService userService;
    private final QuoteService quoteService;

    @GetMapping
    public List<UserDTO> getAll(){
        return UserMapper.INSTANCE.toListUserDtoFromListUser(userService.findAll());
    }
    @GetMapping(path = "/{id}")
    public UserDTO getById(@PathVariable(name = "id") long id) {
        return UserMapper.INSTANCE.toUserDtoFromUser(userService.findById(id));
    }

    @GetMapping(path = "/{id}/quotes")
    public List<QuoteDTO> findAllByUserId(@PathVariable Long id) {
        return QuoteMapper.INSTANCE.toListQuoteDtoFromListQuote(quoteService.findAllByUserId(id));
    }

    @PutMapping
    public UserDTO update(Principal principal, @RequestBody UserUpdateDTO user){
        return UserMapper.INSTANCE.toUserDtoFromUser(userService.update(principal, user));
    }

    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    @DeleteMapping(path = "/{id}")
    void deleteById(@PathVariable(name = "id") long id) {
        userService.deleteById(id);
    }
}
