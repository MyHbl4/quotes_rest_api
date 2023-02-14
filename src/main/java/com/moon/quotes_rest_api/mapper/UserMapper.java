package com.moon.quotes_rest_api.mapper;

import com.moon.quotes_rest_api.controller.dto.UserDTO;
import com.moon.quotes_rest_api.controller.dto.UserNewDTO;
import com.moon.quotes_rest_api.entity.User;
import java.util.List;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface UserMapper {

    UserMapper INSTANCE = Mappers.getMapper(UserMapper.class);

    UserDTO toUserDtoFromUser(User user);

    User toUserFromUserNewDto(UserNewDTO newUserDTO);

    List<UserDTO> toListUserDtoFromListUser(List<User> users);
}
