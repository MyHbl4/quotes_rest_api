package com.moon.quotes_rest_api.controller.dto;

import javax.validation.constraints.NotNull;
import lombok.Data;
import org.hibernate.validator.constraints.Length;

@Data
public class UserUpdateDTO {

    @NotNull
    @Length(min = 5, max = 50)
    private String name;

}
