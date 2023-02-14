package com.moon.quotes_rest_api.controller.dto;

import javax.validation.constraints.NotNull;
import lombok.Data;

@Data
public class QuoteUpdateDTO {

    @NotNull
    private Long id;
    @NotNull
    private String description;

}
